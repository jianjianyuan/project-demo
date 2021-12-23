package cn.project.demo.module.user.entity;

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
 * 用户
 */
@ApiModel(value = "用户")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`system_user`")
public class SystemUser extends BaseEntity {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户编码
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value = "用户编码")
    private String userCode;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * avatar
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "avatar")
    private String avatar;

    /**
     * 帐号状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "帐号状态")
    private Integer status;

    /**
     * 手机
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 加密后的密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "加密后的密码")
    private String password;

    /**
     * 注册 IP
     */
    @TableField(value = "register_ip")
    @ApiModelProperty(value = "注册 IP")
    private String registerIp;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;
}