package com.silence.web.spring_min.bean.factory;

import java.util.ArrayList;
import java.util.List;

public class BeanFactory {

	private BeanFactory(){}
	
	private static BeanFactory beanFactory;
	
	private List<Object> beans=new ArrayList<>();
	
	public static BeanFactory getInstance(){
		if(null==beanFactory) beanFactory=new BeanFactory();
		return beanFactory;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> requiredType){
		
		for (Object bean:beans) {
			if(bean.getClass().equals(requiredType)) return (T) bean;
		}
		
		return null;
	} 
	
	public void setBean(Object bean){
		beans.add(bean);
	}

	public List<Object> getBeans() {
		return beans;
	}
	
	
}
