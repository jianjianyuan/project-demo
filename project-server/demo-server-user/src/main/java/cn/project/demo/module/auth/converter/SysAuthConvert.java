package cn.project.demo.module.auth.converter;

import cn.project.demo.framework.common.constants.enums.UserTypeEnum;
import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.user.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysAuthConvert {

    SysAuthConvert INSTANCE = Mappers.getMapper(SysAuthConvert.class);

    @Mapping(source = "mobile", target = "username")
    LoginUser convert0(SystemUser bean);

    default LoginUser convert(SystemUser bean) {
        // 目的，为了设置 UserTypeEnum.MEMBER.getValue()
        return convert0(bean).setUserType(UserTypeEnum.MEMBER.getSymbol());
    }
}