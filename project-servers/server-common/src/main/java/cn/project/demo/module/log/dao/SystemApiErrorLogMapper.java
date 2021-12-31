package cn.project.demo.module.log.dao;

import cn.project.demo.module.log.entity.SystemApiErrorLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemApiErrorLogMapper extends BaseMapper<SystemApiErrorLog> {
}