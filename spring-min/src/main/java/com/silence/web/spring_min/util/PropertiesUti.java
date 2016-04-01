package com.silence.web.spring_min.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUti {

	public static Properties getProperties(String filePath){
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUti.class.getResourceAsStream(filePath));
			return properties;
		} catch (IOException e) {
			return null;
		}
		
	}
}
