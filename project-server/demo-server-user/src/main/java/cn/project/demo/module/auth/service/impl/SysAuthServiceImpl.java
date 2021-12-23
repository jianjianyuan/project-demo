package cn.project.demo.module.auth.service.impl;

import cn.hutool.core.lang.Assert;
import cn.project.demo.framework.common.constants.enums.PlainOrdinaryStatusEnum;
import cn.project.demo.framework.common.constants.enums.UserTypeEnum;
import cn.project.demo.framework.common.exception.util.ServiceExceptionUtil;
import cn.project.demo.framework.common.util.monitor.TracerUtils;
import cn.project.demo.framework.common.util.servlet.ServletUtils;
import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.constants.SysErrorCodeConstants;
import cn.project.demo.module.auth.converter.SysAuthConvert;
import cn.project.demo.module.auth.pojo.SysAuthLoginReqVO;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReqDTO;
import cn.project.demo.module.auth.service.SysAuthService;
import cn.project.demo.module.auth.service.SysLoginLogCoreService;
import cn.project.demo.module.auth.service.SysUserSessionCoreService;
import cn.project.demo.module.sys.constants.enums.logger.SysLoginLogTypeEnum;
import cn.project.demo.module.sys.constants.enums.logger.SysLoginResultEnum;
import cn.project.demo.module.user.entity.SystemUser;
import cn.project.demo.module.user.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * Auth Service 实现类
 */
@Service
@Slf4j
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    @Lazy // 延迟加载，因为存在相互依赖的问题
    private AuthenticationManager authenticationManager;

    @Resource
    private SystemUserService systemUserService;

    @Resource
    private SysLoginLogCoreService loginLogCoreService;

    @Resource
    private SysUserSessionCoreService userSessionCoreService;


    private static final UserTypeEnum userTypeEnum = UserTypeEnum.MEMBER;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        // 获取 username 对应的 SysUserDO
        SystemUser user = systemUserService.getUserByMobile(mobile);
        if (user == null) {
            throw new UsernameNotFoundException(mobile);
        }
        // 创建 LoginUser 对象
        return SysAuthConvert.INSTANCE.convert(user);
    }

    @Override
    public String login(SysAuthLoginReqVO reqVO, String userIp, String userAgent) {
        // 使用手机 + 密码，进行登录。
        LoginUser loginUser = this.login0(reqVO.getMobile(), reqVO.getPassword());

        // 缓存登录用户到 Redis 中，返回 sessionId 编号
        return userSessionCoreService.createUserSession(loginUser, userIp, userAgent);
    }

    private LoginUser login0(String username, String password) {
        final SysLoginLogTypeEnum logTypeEnum = SysLoginLogTypeEnum.LOGIN_USERNAME;
        // 用户验证
        Authentication authentication;
        try {
            // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
            // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException badCredentialsException) {
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.BAD_CREDENTIALS);
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS);
        } catch (DisabledException disabledException) {
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.USER_DISABLED);
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.AUTH_LOGIN_USER_DISABLED);
        } catch (AuthenticationException authenticationException) {
            log.error("[login0][username({}) 发生未知异常]", username, authenticationException);
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.UNKNOWN_ERROR);
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.AUTH_LOGIN_FAIL_UNKNOWN);
        }
        // 登录成功的日志
        Assert.notNull(authentication.getPrincipal(), "Principal 不会为空");
        this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.SUCCESS);
        return (LoginUser) authentication.getPrincipal();
    }

    private void createLoginLog(String mobile, SysLoginLogTypeEnum logTypeEnum, SysLoginResultEnum loginResult) {
        // 获得用户
        SystemUser user = systemUserService.getUserByMobile(mobile);
        // 插入登录日志
        SysLoginLogCreateReqDTO reqDTO = new SysLoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        if (user != null) {
            reqDTO.setUserId(user.getId());
        }
        reqDTO.setUsername(mobile);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogCoreService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (user != null && Objects.equals(SysLoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            systemUserService.updateUserLogin(user.getId(), ServletUtils.getClientIP());
        }
    }

    @Override
    public LoginUser verifyTokenAndRefresh(String token) {
        // 获得 LoginUser
        LoginUser loginUser = userSessionCoreService.getLoginUser(token);
        if (loginUser == null) {
            return null;
        }
        // 刷新 LoginUser 缓存
        this.refreshLoginUserCache(token, loginUser);
        return loginUser;
    }

    private void refreshLoginUserCache(String token, LoginUser loginUser) {
        // 每 1/3 的 Session 超时时间，刷新 LoginUser 缓存
        if (System.currentTimeMillis() - loginUser.getUpdateTime().getTime() <
                userSessionCoreService.getSessionTimeoutMillis() / 3) {
            return;
        }

        // 重新加载 MbrUserDO 信息
        SystemUser user = systemUserService.getUser(loginUser.getId());
        if (user == null || PlainOrdinaryStatusEnum.STATUS_ENUM_DISABLE.getSymbol().equals(user.getStatus())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.AUTH_TOKEN_EXPIRED); // 校验 token 时，用户被禁用的情况下，也认为 token 过期，方便前端跳转到登录界面
        }

        // 刷新 LoginUser 缓存
        userSessionCoreService.refreshUserSession(token, loginUser);
    }

    @Override
    public LoginUser mockLogin(Long userId) {
        // 获取用户编号对应的 MbrUserDO
        SystemUser user = systemUserService.getUser(userId);
        if (user == null) {
            throw new UsernameNotFoundException(String.valueOf(userId));
        }

        // 执行登陆
        this.createLoginLog(user.getMobile(), SysLoginLogTypeEnum.LOGIN_MOCK, SysLoginResultEnum.SUCCESS);

        // 创建 LoginUser 对象
        return SysAuthConvert.INSTANCE.convert(user);
    }

    @Override
    public void logout(String token) {
        // 查询用户信息
        LoginUser loginUser = userSessionCoreService.getLoginUser(token);
        if (loginUser == null) {
            return;
        }
        // 删除 session
        userSessionCoreService.deleteUserSession(token);
        // 记录登出日志
        this.createLogoutLog(loginUser.getId(), loginUser.getUsername());
    }

    private void createLogoutLog(Long userId, String username) {
        SysLoginLogCreateReqDTO reqDTO = new SysLoginLogCreateReqDTO();
        reqDTO.setLogType(SysLoginLogTypeEnum.LOGOUT_SELF.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userTypeEnum.getSymbol());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(SysLoginResultEnum.SUCCESS.getResult());
        loginLogCoreService.createLoginLog(reqDTO);
    }

}
