package cn.project.demo.module.auth.entity;

import cn.project.demo.framework.common.constants.enums.UserTypeEnum;
import cn.project.demo.framework.db.mybatis.pojo.BaseEntity;
import cn.project.demo.module.sys.constants.enums.logger.SysLoginResultEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 登录日志表
 * <p>
 * 注意，包括登录和登出两种行为
 *
 * @author 芋道源码
 */
@TableName("sys_login_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysLoginLogDO extends BaseEntity {

    /**
     * 日志主键
     */
    private Long id;
    /**
     * 日志类型
     * <p>
     * 枚举 {@link cn.project.demo.module.sys.constants.enums.logger.SysLoginLogTypeEnum}
     */
    private Integer logType;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 用户账号
     * <p>
     * 冗余，因为账号可以变更
     */
    private String username;
    /**
     * 登录结果
     * <p>
     * 枚举 {@link SysLoginResultEnum}
     */
    private Integer result;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 浏览器 UA
     */
    private String userAgent;

}
