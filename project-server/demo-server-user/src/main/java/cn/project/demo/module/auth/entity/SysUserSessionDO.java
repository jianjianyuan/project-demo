package cn.project.demo.module.auth.entity;

import cn.project.demo.framework.db.mybatis.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 在线用户表
 * <p>
 * 我们已经将 {@link cn.project.demo.framework.security.pojo.LoginUser} 缓存在 Redis 当中。
 * 这里额外存储在线用户到 MySQL 中，目的是为了方便管理界面可以灵活查询。
 * 同时，通过定时轮询 SysUserSessionDO 表，可以主动删除 Redis 的缓存，因为 Redis 的过期删除是延迟的。
 *
 * @author 芋道源码
 */
@TableName(value = "sys_user_session", autoResultMap = true)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SysUserSessionDO extends BaseEntity {

    /**
     * 会话编号, 即 sessionId
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 用户编号
     * <p>
     * 关联 SysUserDO.id 或者 MbrUserDO.id
     */
    private Long userId;
    /**
     * 用户类型
     * <p>
     * 枚举 {@link cn.project.demo.framework.common.constants.enums.UserTypeEnum}
     */
    private Integer userType;

    /**
     * 用户账号
     * <p>
     * 冗余，因为账号可以变更
     */
    private String username;

    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 浏览器 UA
     */
    private String userAgent;
    /**
     * 会话超时时间
     */
    private Date sessionTimeout;

}
