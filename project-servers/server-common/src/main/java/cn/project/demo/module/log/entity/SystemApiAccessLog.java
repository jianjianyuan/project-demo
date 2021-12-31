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
 * API 访问日志
 */
@ApiModel(value = "API 访问日志")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_api_access_log")
public class SystemApiAccessLog extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 链路追踪编号
     */
    @TableField(value = "trace_id")
    @ApiModelProperty(value = "链路追踪编号")
    private String traceId;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户编号")
    private Long userId;

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
     * 开始请求时间
     */
    @TableField(value = "begin_time")
    @ApiModelProperty(value = "开始请求时间")
    private Date beginTime;

    /**
     * 结束请求时间
     */
    @TableField(value = "end_time")
    @ApiModelProperty(value = "结束请求时间")
    private Date endTime;

    /**
     * 执行时长，单位：毫秒
     */
    @TableField(value = "duration")
    @ApiModelProperty(value = "执行时长，单位：毫秒")
    private Integer duration;

    /**
     * 结果码
     */
    @TableField(value = "result_code")
    @ApiModelProperty(value = "结果码")
    private Integer resultCode;

    /**
     * 结果提示
     */
    @TableField(value = "result_msg")
    @ApiModelProperty(value = "结果提示")
    private String resultMsg;
}