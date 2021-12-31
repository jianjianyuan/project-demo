package cn.project.demo.module.auth.converter;

import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.constants.enums.UserTypeEnum;
import cn.project.demo.module.user.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysAuthConvert {

    SysAuthConvert INSTANCE = Mappers.getMapper(SysAuthConvert.class);
    @Mapping(source = "nickname", target = "username")
    LoginUser convert0(SystemUser bean);

    default LoginUser convertToLoginUser(SystemUser bean) {
        // 目的，为了设置 UserTypeEnum.MEMBER.getValue()
        LoginUser loginUser = convert0(bean);
        loginUser.setUserType(UserTypeEnum.MEMBER.getSymbol());
        return loginUser;
    }
}