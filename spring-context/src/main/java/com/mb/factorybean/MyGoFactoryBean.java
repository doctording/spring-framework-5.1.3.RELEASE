package com.mb.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author mubi
 * @Date 2020/7/4 13:01
 */
public class MyGoFactoryBean implements FactoryBean<Go> {

	private String type;

	private Go getDefaultGo(){
		return new Go() {
			@Override
			public void out() {
				System.out.println("just go on foot");
			}
		};
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Go getObject(){
		if (type == null) {
			return getDefaultGo();
		}
		if (type.equalsIgnoreCase(GoEnum.BIKE.getType())) {
			return new BikeGo();
		}
		if (type.equalsIgnoreCase(GoEnum.CAR.getType())) {
			return new CarGo();
		}
		return getDefaultGo();
	}

	@Override
	public Class<Go> getObjectType() { return Go.class ; }

	@Override
	public boolean isSingleton() { return false; }
}