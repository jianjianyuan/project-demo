package cn.project.demo.module.log.service;

import cn.project.demo.framework.apiLog.service.ApiErrorLogFrameworkService;
import cn.project.demo.framework.apiLog.service.dto.ApiErrorLogCreateReqDTO;

//桥接到web public interface SystemApiErrorLogService extends IService<SystemApiErrorLog> {
public interface SystemApiErrorLogService extends ApiErrorLogFrameworkService {

    void createApiErrorLogAsync(ApiErrorLogCreateReqDTO errorLogCreateReqDTO);
}
