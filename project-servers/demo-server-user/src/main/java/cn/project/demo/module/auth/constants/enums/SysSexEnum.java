package cn.project.demo.module.auth.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 */
@Getter
@AllArgsConstructor
public enum SysSexEnum {

    /**
     * 男
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(2),
    /* 未知 */
    UNKNOWN(3);

    /**
     * 性别
     */
    private final Integer sex;

}
