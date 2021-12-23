package cn.project.demo.module.log.service.impl;

import cn.project.demo.framework.apiLog.service.dto.ApiAccessLogCreateReqDTO;
import cn.project.demo.module.log.dao.SystemApiAccessLogMapper;
import cn.project.demo.module.log.entity.SystemApiAccessLog;
import cn.project.demo.module.log.service.SystemApiAccessLogService;
import cn.project.demo.module.util.converter.log.ApiAccessLogCoreConvert;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//桥接到web public class SystemApiAccessLogServiceImpl extends ServiceImpl<SystemApiAccessLogMapper, SystemApiAccessLog> implements SystemApiAccessLogService {
public class SystemApiAccessLogServiceImpl implements SystemApiAccessLogService {

    @Resource
    private SystemApiAccessLogMapper systemApiAccessLogMapper;

    @Override
    @Async
    public void createApiAccessLogAsync(ApiAccessLogCreateReqDTO accessLogCreateReqDTO) {
        SystemApiAccessLog apiAccessLog = ApiAccessLogCoreConvert.INSTANCE.convert(accessLogCreateReqDTO);
        systemApiAccessLogMapper.insert(apiAccessLog);
    }
}