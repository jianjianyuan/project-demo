package cn.project.demo.module.log.entity;

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
 * API 异常日志
 */
@ApiModel(value = "API 异常日志")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_api_error_log")
public class SystemApiErrorLog extends BaseEntity {
    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "Id")
    private Integer id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    /**
     * 链路追踪编号
     */
    @TableField(value = "trace_id")
    @ApiModelProperty(value = "链路追踪编号")
    private String traceId;

    /**
     * 用户类型
     */
    @TableField(value = "user_type")
    @ApiModelProperty(value = "用户类型")
    private Byte userType;

    /**
     * 应用名
     */
    @TableField(value = "application_name")
    @ApiModelProperty(value = "应用名")
    private String applicationName;

    /**
     * 请求方法名
     */
    @TableField(value = "request_method")
    @ApiModelProperty(value = "请求方法名")
    private String requestMethod;

    /**
     * 访问地址
     */
    @TableField(value = "request_url")
    @ApiModelProperty(value = "访问地址")
    private String requestUrl;

    /**
     * 请求参数
     */
    @TableField(value = "request_params")
    @ApiModelProperty(value = "请求参数")
    private String requestParams;

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

    /**
     * 异常发生时间
     */
    @TableField(value = "exception_time")
    @ApiModelProperty(value = "异常发生时间")
    private Date exceptionTime;

    /**
     * 异常名
     */
    @TableField(value = "exception_name")
    @ApiModelProperty(value = "异常名")
    private String exceptionName;

    /**
     * 异常导致的消息
     */
    @TableField(value = "exception_message")
    @ApiModelProperty(value = "异常导致的消息")
    private String exceptionMessage;

    /**
     * 异常导致的根消息
     */
    @TableField(value = "exception_root_cause_message")
    @ApiModelProperty(value = "异常导致的根消息")
    private String exceptionRootCauseMessage;

    /**
     * 异常的栈轨迹
     */
    @TableField(value = "exception_stack_trace")
    @ApiModelProperty(value = "异常的栈轨迹")
    private String exceptionStackTrace;

    /**
     * 异常发生的类全名
     */
    @TableField(value = "exception_class_name")
    @ApiModelProperty(value = "异常发生的类全名")
    private String exceptionClassName;

    /**
     * 异常发生的类文件
     */
    @TableField(value = "exception_file_name")
    @ApiModelProperty(value = "异常发生的类文件")
    private String exceptionFileName;

    /**
     * 异常发生的方法名
     */
    @TableField(value = "exception_method_name")
    @ApiModelProperty(value = "异常发生的方法名")
    private String exceptionMethodName;

    /**
     * 异常发生的方法所在行
     */
    @TableField(value = "exception_line_number")
    @ApiModelProperty(value = "异常发生的方法所在行")
    private Integer exceptionLineNumber;

    /**
     * 处理状态
     */
    @TableField(value = "process_status")
    @ApiModelProperty(value = "处理状态")
    private Integer processStatus;

    /**
     * 处理时间
     */
    @TableField(value = "process_time")
    @ApiModelProperty(value = "处理时间")
    private Date processTime;

    /**
     * 处理用户编号
     */
    @TableField(value = "process_user_id")
    @ApiModelProperty(value = "处理用户编号")
    private Integer processUserId;
}