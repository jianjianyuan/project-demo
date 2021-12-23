package cn.project.demo.module.log.service.impl;

import cn.project.demo.framework.apiLog.service.dto.ApiErrorLogCreateReqDTO;
import cn.project.demo.framework.common.constants.enums.ApiErrorLogProcessStatusEnum;
import cn.project.demo.module.log.dao.SystemApiErrorLogMapper;
import cn.project.demo.module.log.entity.SystemApiErrorLog;
import cn.project.demo.module.log.service.SystemApiErrorLogService;
import cn.project.demo.module.util.converter.log.ApiErrorLogCoreConvert;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//桥接到web public class SystemApiErrorLogServiceImpl extends ServiceImpl<SystemApiErrorLogMapper, SystemApiErrorLog> implements SystemApiErrorLogService {
public class SystemApiErrorLogServiceImpl implements SystemApiErrorLogService {

    @Resource
    private SystemApiErrorLogMapper systemApiErrorLogMapper;

    @Override
    @Async
    public void createApiErrorLogAsync(ApiErrorLogCreateReqDTO errorLogCreateReqDTO) {
        SystemApiErrorLog apiErrorLog = ApiErrorLogCoreConvert.INSTANCE.convert(errorLogCreateReqDTO);
        apiErrorLog.setProcessStatus(ApiErrorLogProcessStatusEnum.INIT.getStatus());
        systemApiErrorLogMapper.insert(apiErrorLog);
    }
}