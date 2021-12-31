package cn.project.demo.module.auth.service;


import cn.project.demo.framework.security.service.SecurityAuthFrameworkService;
import cn.project.demo.module.auth.pojo.UserCodeLoginReq;

import javax.validation.Valid;

/**
 * 用户前台的认证 Service 接口
 * <p>
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 */
public interface SysAuthService extends SecurityAuthFrameworkService {

    /**
     * 用户编码 + 密码登录
     *
     * @param reqVO     登录信息
     * @param userIp    用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String userCodeLogin(@Valid UserCodeLoginReq reqVO, String userIp, String userAgent);

}