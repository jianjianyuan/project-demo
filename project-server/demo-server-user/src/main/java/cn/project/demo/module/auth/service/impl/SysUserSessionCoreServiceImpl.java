package cn.project.demo.module.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.project.demo.framework.common.util.date.DateUtils;
import cn.project.demo.framework.security.conf.SecurityProperties;
import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.dao.SysLoginUserCoreRedisDAO;
import cn.project.demo.module.auth.dao.SysUserSessionCoreMapper;
import cn.project.demo.module.auth.entity.SysUserSessionDO;
import cn.project.demo.module.auth.service.SysUserSessionCoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;


/**
 * 在线用户 Session Core Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class SysUserSessionCoreServiceImpl implements SysUserSessionCoreService {

    @Resource
    private SysUserSessionCoreMapper userSessionCoreMapper;

    @Resource
    private SysLoginUserCoreRedisDAO loginUserCoreRedisDAO;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public String createUserSession(LoginUser loginUser, String userIp, String userAgent) {
        // 生成 Session 编号
        String sessionId = generateSessionId();
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserCoreRedisDAO.set(sessionId, loginUser);
        // 写入 DB 中
        SysUserSessionDO userSession = SysUserSessionDO.builder().id(sessionId)
                .userId(loginUser.getId()).userType(loginUser.getUserType())
                .userIp(userIp).userAgent(userAgent).username(loginUser.getUsername())
                .sessionTimeout(DateUtils.addTime(Duration.ofMillis(getSessionTimeoutMillis())))
                .build();
        userSessionCoreMapper.insert(userSession);
        // 返回 Session 编号
        return sessionId;
    }

    @Override
    public void refreshUserSession(String sessionId, LoginUser loginUser) {
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserCoreRedisDAO.set(sessionId, loginUser);
        // 更新 DB 中
        SysUserSessionDO updateObj = SysUserSessionDO.builder().id(sessionId).build();
        updateObj.setUsername(loginUser.getUsername());
        updateObj.setUpdateTime(new Date());
        updateObj.setSessionTimeout(DateUtils.addTime(Duration.ofMillis(getSessionTimeoutMillis())));
        userSessionCoreMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserSession(String sessionId) {
        // 删除 Redis 缓存
        loginUserCoreRedisDAO.delete(sessionId);
        // 删除 DB 记录
        userSessionCoreMapper.deleteById(sessionId);
    }

    @Override
    public LoginUser getLoginUser(String sessionId) {
        return loginUserCoreRedisDAO.get(sessionId);
    }

    @Override
    public Long getSessionTimeoutMillis() {
        return securityProperties.getSessionTimeout().toMillis();
    }

    /**
     * 生成 Session 编号，目前采用 UUID 算法
     *
     * @return Session 编号
     */
    private static String generateSessionId() {
        return IdUtil.fastSimpleUUID();
    }

}
