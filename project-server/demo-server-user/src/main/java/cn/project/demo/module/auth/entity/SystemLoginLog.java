package cn.project.demo.module.auth.entity;

import cn.project.demo.framework.db.mybatis.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 登录日志
 */
@ApiModel(value = "登录日志")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_login_log")
public class SystemLoginLog extends BaseEntity {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 日志类型
     */
    @TableField(value = "log_type")
    @ApiModelProperty(value = "日志类型")
    private Long logType;

    /**
     * 链路追踪编号
     */
    @TableField(value = "trace_id")
    @ApiModelProperty(value = "链路追踪编号")
    private String traceId;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户Id")
    private Long userId;

    /**
     * 用户编号
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value = "用户编号")
    private String userCode;

    /**
     * 用户类型
     */
    @TableField(value = "user_type")
    @ApiModelProperty(value = "用户类型")
    private Byte userType;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名称")
    private String username;

    /**
     * 登录结果
     */
    @TableField(value = "`result`")
    @ApiModelProperty(value = "登录结果")
    private Byte result;

    /**
     * 用户 IP
     */
    @TableField(value = "user_ip")
    @ApiModelProperty(value = "用户 IP")
    private String userIp;

    /**
     * 浏览器 UA
     */
    @TableField(value = "user_agent")
    @ApiModelProperty(value = "浏览器 UA")
    private String userAgent;
}