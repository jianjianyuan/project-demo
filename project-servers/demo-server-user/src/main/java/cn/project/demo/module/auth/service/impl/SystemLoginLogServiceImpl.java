package cn.project.demo.module.auth.service.impl;

import cn.project.demo.module.auth.converter.SystemLoginLogCoreConvert;
import cn.project.demo.module.auth.dao.SystemLoginLogMapper;
import cn.project.demo.module.auth.entity.SystemLoginLog;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReq;
import cn.project.demo.module.auth.service.SystemLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SystemLoginLogServiceImpl extends ServiceImpl<SystemLoginLogMapper, SystemLoginLog> implements SystemLoginLogService {
    @Resource
    private SystemLoginLogMapper systemLoginLogMapper;

    @Transactional
    @Override
    public void createLoginLog(SysLoginLogCreateReq logCreateReq) {
        SystemLoginLog loginLog = SystemLoginLogCoreConvert.INSTANCE.convert(logCreateReq);
        systemLoginLogMapper.insert(loginLog);
    }
}