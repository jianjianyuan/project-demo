package cn.project.demo.module.log.service.impl;

import cn.project.demo.module.log.dao.SystemOperateLogMapper;
import cn.project.demo.module.log.entity.SystemOperateLog;
import cn.project.demo.module.log.service.SystemOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemOperateLogServiceImpl extends ServiceImpl<SystemOperateLogMapper, SystemOperateLog> implements SystemOperateLogService {

}
