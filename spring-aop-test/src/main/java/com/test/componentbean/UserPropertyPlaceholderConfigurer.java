package com.test.componentbean;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author dingqi on 2022/5/14
 * @since 1.0.0
 */
@Component
public class UserPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		props.put("key1", "value1");
	}
}