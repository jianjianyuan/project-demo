package cn.project.demo.framework.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举类
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    ALL(1), // 全部数据权限
    DEPT_CUSTOM(2), // 指定部门数据权限
    DEPT_ONLY(3), // 部门数据权限
    DEPT_AND_CHILD(4), // 部门及以下数据权限
    DEPT_SELF(5); // 仅本人数据权限

    private final Integer scope;
}