package com.silence.web.spring_min.util;

import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 *   
 * MeThodUtil  
 *   
 * silence  
 * silence  
 * 2016年3月19日 下午4:33:52  
 *   
 * @version 1.0.0  
 *
 */
public class MeThodUtil {

	public static Object invokeMethod(String jexlExp,Map<String,Object> params){  
        JexlEngine jexl=new JexlEngine();  
        Expression e = jexl.createExpression(jexlExp);  
        JexlContext jc = new MapContext();  
        for(String key:params.keySet()){  
            jc.set(key, params.get(key));  
        }  
        Object rtn = e.evaluate(jc);
       
        return rtn!=null?rtn:"";  
    }  
}
