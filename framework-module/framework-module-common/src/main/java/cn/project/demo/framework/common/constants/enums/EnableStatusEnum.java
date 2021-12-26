package cn.project.demo.framework.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableStatusEnum {
	STATUS_ENABLE(1, "开启"),
	STATUS_DISABLE(0, "关闭");

	/**
	 * 状态
	 */
	private final Integer status;

	/**
	 * 描述
	 */
	private final String des;
}