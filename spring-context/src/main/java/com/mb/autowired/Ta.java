package com.mb.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/18 12:46
 */
@Component
public class Ta {
	@Autowired
	I i1;

	public I getI(){
		return i1;
	}
}
