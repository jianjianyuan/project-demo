package cn.project.demo.module.auth.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 登录日志创建 Request DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginLogCreateReq implements Serializable {

    /**
     * 日志类型
     *
     * @see cn.project.demo.module.sys.constants.enums.logger.SysLoginLogTypeEnum
     */
    @NotNull(message = "日志类型不能为空")
    private Integer logType;
    /**
     * 链路追踪编号
     */
    @NotEmpty(message = "链路追踪编号不能为空")
    private String traceId;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String username;

    /**
     * 登录结果
     */
    @NotNull(message = "登录结果不能为空")
    private Integer result;

    /**
     * 用户 IP
     */
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;
    /**
     * 浏览器 UserAgent
     */
    @NotEmpty(message = "浏览器 UserAgent 不能为空")
    private String userAgent;

}
