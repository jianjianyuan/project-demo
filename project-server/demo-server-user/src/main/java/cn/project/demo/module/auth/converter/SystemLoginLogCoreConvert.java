package cn.project.demo.module.auth.converter;

import cn.project.demo.module.auth.entity.SystemLoginLog;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLoginLogCoreConvert {

    SystemLoginLogCoreConvert INSTANCE = Mappers.getMapper(SystemLoginLogCoreConvert.class);

    SystemLoginLog convert(SysLoginLogCreateReq bean);
}