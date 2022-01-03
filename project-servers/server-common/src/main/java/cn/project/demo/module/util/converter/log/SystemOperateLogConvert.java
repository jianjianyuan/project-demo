package cn.project.demo.module.util.converter.log;

import cn.project.demo.framework.web.log.dto.OperateLogCreateReqDTO;
import cn.project.demo.module.log.entity.SystemOperateLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SystemOperateLogConvert {

	SystemOperateLogConvert INSTANCE = Mappers.getMapper(SystemOperateLogConvert.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "operateType", source = "type")
	SystemOperateLog convert(OperateLogCreateReqDTO reqVO);
}