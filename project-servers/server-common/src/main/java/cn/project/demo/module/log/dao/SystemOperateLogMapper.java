package cn.project.demo.module.log.dao;

import cn.project.demo.module.log.entity.SystemOperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemOperateLogMapper extends BaseMapper<SystemOperateLog> {
}