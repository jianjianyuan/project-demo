package cn.project.demo.module.auth.dao;

import cn.project.demo.module.auth.entity.SystemLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemLoginLogMapper extends BaseMapper<SystemLoginLog> {
}