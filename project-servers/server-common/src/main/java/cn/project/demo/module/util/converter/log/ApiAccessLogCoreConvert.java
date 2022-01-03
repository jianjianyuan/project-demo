package cn.project.demo.module.util.converter.log;

import cn.project.demo.framework.web.log.dto.ApiAccessLogCreateReqDTO;
import cn.project.demo.module.log.entity.SystemApiAccessLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiAccessLogCoreConvert {

    ApiAccessLogCoreConvert INSTANCE = Mappers.getMapper(ApiAccessLogCoreConvert.class);

    SystemApiAccessLog convert(ApiAccessLogCreateReqDTO bean);

}
