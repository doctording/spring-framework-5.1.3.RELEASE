package com.mb.factory;

/**
 * @Author mubi
 * @Date 2020/7/4 13:00
 */
public enum GoEnum {
	BIKE("bike"),
	CAR("car");

	String type;

	GoEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
