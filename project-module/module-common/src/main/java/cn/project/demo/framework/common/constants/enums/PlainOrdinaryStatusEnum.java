package cn.project.demo.framework.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlainOrdinaryStatusEnum {
    STATUS_ENUM_ENABLE(1, "开启"),
    STATUS_ENUM_DISABLE(0, "关闭");

    /**
     * 标识
     */
    private final Integer symbol;

    /**
     * 描述
     */
    private final String des;
}