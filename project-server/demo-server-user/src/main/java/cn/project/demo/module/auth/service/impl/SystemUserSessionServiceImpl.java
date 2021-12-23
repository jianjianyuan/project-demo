package cn.project.demo.module.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.project.demo.framework.common.util.date.DateUtils;
import cn.project.demo.framework.security.conf.SecurityProperties;
import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.dao.LoginUserRedisDAO;
import cn.project.demo.module.auth.dao.SystemUserSessionMapper;
import cn.project.demo.module.auth.entity.SystemUserSession;
import cn.project.demo.module.auth.service.SystemUserSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;

@Service
public class SystemUserSessionServiceImpl extends ServiceImpl<SystemUserSessionMapper, SystemUserSession> implements SystemUserSessionService {
    @Resource
    private SystemUserSessionMapper systemUserSessionMapper;

    @Resource
    private LoginUserRedisDAO loginUserCoreRedisDAO;

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 生成 Session 编号，目前采用 UUID 算法
     *
     * @return Session 编号
     */
    private static String generateSessionId() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public String createUserSession(LoginUser loginUser, String userIp, String userAgent) {
        // 生成 Session 编号
        String sessionId = generateSessionId();
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserCoreRedisDAO.save(sessionId, loginUser);
        // 写入 DB 中
        systemUserSessionMapper.insert(
                SystemUserSession.builder()
                        .sessionId(sessionId)
                        .userId(loginUser.getId())
                        .userCode(loginUser.getUserCode())
                        .username(loginUser.getUsername())
                        .userType(loginUser.getUserType())
                        .sessionTimeout(
                                DateUtils.addTime(Duration.ofMillis(getSessionTimeoutMillis()))
                        )
                        .build()
        );
        // 返回 Session 编号
        return sessionId;
    }

    @Override
    public LoginUser getLoginUser(String sessionId) {
        return loginUserCoreRedisDAO.getBySessionId(sessionId);
    }

    @Override
    public void deleteUserSession(String sessionId) {
        // 删除 Redis 缓存
        loginUserCoreRedisDAO.delete(sessionId);
        // 删除 DB 记录
        systemUserSessionMapper.deleteById(sessionId);
    }

    @Override
    public void refreshUserSession(String sessionId, LoginUser loginUser) {
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserCoreRedisDAO.save(sessionId, loginUser);
        // 更新 DB 中
        SystemUserSession updateObj = SystemUserSession.builder().sessionId(sessionId).build();
        updateObj.setUsername(loginUser.getUsername());
        updateObj.setUpdateTime(new Date());
        updateObj.setSessionTimeout(DateUtils.addTime(Duration.ofMillis(getSessionTimeoutMillis())));
        systemUserSessionMapper.updateById(updateObj);
    }

    @Override
    public Long getSessionTimeoutMillis() {
        return securityProperties.getSessionTimeout().toMillis();
    }
}