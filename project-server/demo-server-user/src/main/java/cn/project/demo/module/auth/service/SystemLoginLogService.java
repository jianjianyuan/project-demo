package cn.project.demo.module.auth.service;

import cn.project.demo.module.auth.entity.SystemLoginLog;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReq;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SystemLoginLogService extends IService<SystemLoginLog> {

    void createLoginLog(SysLoginLogCreateReq logCreateReq);
}