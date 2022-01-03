package cn.project.demo.module.log.service;

import cn.project.demo.framework.web.log.dto.ApiErrorLogCreateReqDTO;
import cn.project.demo.framework.web.log.service.ApiErrorLogFrameworkService;

//桥接到web public interface SystemApiErrorLogService extends IService<SystemApiErrorLog> {
public interface SystemApiErrorLogService extends ApiErrorLogFrameworkService {

    void createApiErrorLogAsync(ApiErrorLogCreateReqDTO errorLogCreateReqDTO);
}
