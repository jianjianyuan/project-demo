package cn.project.demo.framework.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrorLogProcessStatusEnum {
    INIT(0, "未处理"),
    DONE(1, "已处理"),
    IGNORE(2, "已忽略");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String des;
}