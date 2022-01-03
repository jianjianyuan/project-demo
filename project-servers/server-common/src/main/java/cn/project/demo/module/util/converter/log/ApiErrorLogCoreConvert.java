package cn.project.demo.module.util.converter.log;

import cn.project.demo.framework.web.log.dto.ApiErrorLogCreateReqDTO;
import cn.project.demo.module.log.entity.SystemApiErrorLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiErrorLogCoreConvert {

    ApiErrorLogCoreConvert INSTANCE = Mappers.getMapper(ApiErrorLogCoreConvert.class);

    SystemApiErrorLog convert(ApiErrorLogCreateReqDTO bean);

}
