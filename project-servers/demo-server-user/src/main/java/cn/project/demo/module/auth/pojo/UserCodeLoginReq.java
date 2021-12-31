package cn.project.demo.module.auth.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@ApiModel("用户编码 + 密码登录 Request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCodeLoginReq {
    @ApiModelProperty(value = "用户编号", required = true, example = "00123123")
    @NotEmpty(message = "用户编号不能为空")
    private String userCode;

    @ApiModelProperty(value = "密码", required = true, example = "********")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    private String password;
}