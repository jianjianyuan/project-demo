package cn.project.demo.module.auth.converter;

import cn.project.demo.module.auth.entity.SysLoginLogDO;
import cn.project.demo.module.auth.pojo.SysLoginLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysLoginLogCoreConvert {

    SysLoginLogCoreConvert INSTANCE = Mappers.getMapper(SysLoginLogCoreConvert.class);

    SysLoginLogDO convert(SysLoginLogCreateReqDTO bean);

}
