package cn.project.demo.framework.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局用户类型枚举
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum {

    MEMBER(1, "用户"),
    ADMIN(2, "管理员");

    /**
     * 类型
     */
    private final Integer symbol;
    /**
     * 类型名
     */
    private final String name;
}