package cn.project.demo.module.log.service;

import cn.project.demo.framework.web.log.service.ApiAccessLogFrameworkService;
import cn.project.demo.framework.web.log.service.dto.ApiAccessLogCreateReqDTO;

//桥接到web public interface SystemApiAccessLogService extends IService<SystemApiAccessLog>{
public interface SystemApiAccessLogService extends ApiAccessLogFrameworkService {

    void createApiAccessLogAsync(ApiAccessLogCreateReqDTO accessLogCreateReqDTO);
}
