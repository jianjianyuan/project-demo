package cn.project.demo.module.auth.service;


import cn.project.demo.module.auth.pojo.SysLoginLogCreateReqDTO;

/**
 * 登录日志 Core Service 接口
 */
public interface SysLoginLogCoreService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(SysLoginLogCreateReqDTO reqDTO);

}
