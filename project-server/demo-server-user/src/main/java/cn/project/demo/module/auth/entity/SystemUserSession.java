package cn.project.demo.module.auth.entity;

import cn.project.demo.framework.db.mybatis.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 用户 Session
 */
@ApiModel(value = "用户在线 Session")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_user_session")
public class SystemUserSession extends BaseEntity {
    /**
     * 会话编号sessionId
     */
    @TableId(value = "session_id", type = IdType.INPUT)
    @ApiModelProperty(value = "会话编号sessionId")
    private String sessionId;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户Id")
    private Long userId;

    /**
     * 用户类型
     */
    @TableField(value = "user_type")
    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    /**
     * 用户编码
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value = "用户编码")
    private String userCode;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户账号")
    private String username;

    /**
     * 会话超时时间
     */
    @TableField(value = "session_timeout")
    @ApiModelProperty(value = "会话超时时间")
    private Date sessionTimeout;

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