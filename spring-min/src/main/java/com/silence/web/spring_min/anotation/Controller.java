package com.silence.web.spring_min.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 *   
 * Controller  
 *   
 * silence  
 * silence  
 * 2016年3月19日 上午11:09:33  
 *   
 * @version 1.0.0  
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Controller {

}
