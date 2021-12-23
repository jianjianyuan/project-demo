package cn.project.demo.module.auth.service.impl;

import cn.project.demo.module.auth.converter.SysLoginLogCoreConvert;
import cn.project.demo.module.auth.dao.SysLoginLogCoreMapper;
import cn.project.demo.module.auth.entity.SysLoginLogDO;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReqDTO;
import cn.project.demo.module.auth.service.SysLoginLogCoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录日志 Service Core 实现
 *
 * @author 芋道源码
 */
@Service
public class SysLoginLogCoreServiceImpl implements SysLoginLogCoreService {

    @Resource
    private SysLoginLogCoreMapper loginLogMapper;

    @Override
    public void createLoginLog(SysLoginLogCreateReqDTO reqDTO) {
        SysLoginLogDO loginLog = SysLoginLogCoreConvert.INSTANCE.convert(reqDTO);
        // 插入
        loginLogMapper.insert(loginLog);
    }

}
