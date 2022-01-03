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
 * 操作日志记录
 */
@ApiModel(value = "操作日志记录")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "system_operate_log", autoResultMap = true)
public class SystemOperateLog extends BaseEntity {
    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "日志主键")
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
     * 模块标题
     */
    @TableField(value = "`module`")
    @ApiModelProperty(value = "模块标题")
    private String module;

    /**
     * 操作名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "操作名")
    private String name;

    /**
     * 操作分类
     */
    @TableField(value = "operate_type")
    @ApiModelProperty(value = "操作分类")
    private Long operateType;

    /**
     * 操作内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "操作内容")
    private String content;

    /**
     * 拓展字段
     */
    @TableField(value = "exts")
    @ApiModelProperty(value = "拓展字段")
    private String exts;

    /**
     * 请求方法名
     */
    @TableField(value = "request_method")
    @ApiModelProperty(value = "请求方法名")
    private String requestMethod;

    /**
     * 请求地址
     */
    @TableField(value = "request_url")
    @ApiModelProperty(value = "请求地址")
    private String requestUrl;

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
     * Java 方法名
     */
    @TableField(value = "java_method")
    @ApiModelProperty(value = "Java 方法名")
    private String javaMethod;

    /**
     * Java 方法的参数
     */
    @TableField(value = "java_method_args")
    @ApiModelProperty(value = "Java 方法的参数")
    private String javaMethodArgs;

    /**
     * 操作时间
     */
    @TableField(value = "start_time")
    @ApiModelProperty(value = "操作时间")
    private Date startTime;

    /**
     * 执行时长
     */
    @TableField(value = "duration")
    @ApiModelProperty(value = "执行时长")
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

    /**
     * 结果数据
     */
    @TableField(value = "result_data")
    @ApiModelProperty(value = "结果数据")
    private String resultData;
}