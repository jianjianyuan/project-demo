package cn.project.demo.module.auth.entity;

import cn.project.demo.framework.db.mybatis.handler.JsonLongSetTypeHandler;
import cn.project.demo.framework.db.mybatis.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Set;

/**
 * 管理后台的用户 DO
 *
 * @author 芋道源码
 */
@TableName(value = "sys_user", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDO extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     * <p>
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 岗位编号数组
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户性别
     * <p>
     * 枚举类 {@link cn.project.demo.module.auth.constants.enums.SysSexEnum}
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帐号状态
     * <p>
     * 枚举 {@link cn.project.demo.framework.common.constants.enums.PlainOrdinaryStatusEnum}
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;

}
