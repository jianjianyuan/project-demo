package cn.project.demo.module.auth.service;

import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.entity.SystemUserSession;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SystemUserSessionService extends IService<SystemUserSession> {

    /**
     * 创建在线用户 Session
     *
     * @param loginUser 登录用户
     * @param userIp    用户 IP
     * @param userAgent 用户 UA
     * @return Session 编号
     */
    String createUserSession(LoginUser loginUser, String userIp, String userAgent);

    /**
     * 获得 Session 编号对应的在线用户
     *
     * @param sessionId Session 编号
     * @return 在线用户
     */
    LoginUser getLoginUser(String sessionId);

    /**
     * 删除在线用户 Session
     *
     * @param sessionId Session 编号
     */
    void deleteUserSession(String sessionId);

    /**
     * 刷新在线用户 Session 的更新时间
     *
     * @param sessionId Session 编号
     * @param loginUser 登录用户
     */
    void refreshUserSession(String sessionId, LoginUser loginUser);

    /**
     * 获得 Session 超时时间，单位：毫秒
     *
     * @return 配置文件中的session超时时间
     */
    Long getSessionTimeoutMillis();
}