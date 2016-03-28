package com.silence.web.spring_min;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.silence.web.spring_min.bean.factory.BeanFactory;

/**
 * 应用上下文
 *   
 * ApplicationContext  
 *   
 * silence  
 * silence  
 * 2016年3月19日 上午11:23:03  
 *   
 * @version 1.0.0  
 *
 */
public class ApplicationContext {
	
	private  String basePackage;

	public <T> T getBean(Class<T> requiredType){
		return BeanFactory.getInstance().getBean(requiredType);
	}
	
	private Map<String,Method> requestMappings=new HashMap<>();

	public void addRequestMappings(String path,Method method){
		requestMappings.put(path, method);
	}

	public Map<String, Method> getRequestMappings() {
		return requestMappings;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	
}
