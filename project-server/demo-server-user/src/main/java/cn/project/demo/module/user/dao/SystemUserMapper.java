package cn.project.demo.module.user.dao;

import cn.project.demo.module.user.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
}