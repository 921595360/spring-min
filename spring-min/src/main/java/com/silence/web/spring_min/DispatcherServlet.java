package com.silence.web.spring_min;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.silence.web.spring_min.bean.factory.BeanFactory;
import com.silence.web.spring_min.util.JSONUtil;
import com.silence.web.spring_min.util.MeThodUtil;
import com.silence.web.spring_min.util.ParameterNameUtils;


/**
 * 
 * 主要用于控制流程  
 * DispatcherServlet  
 *   
 * silence  
 * silence  
 * 2016年3月19日 上午8:59:32  
 *   
 * @version 1.0.0  
 *
 */
public class DispatcherServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(DispatcherServlet.class);
	
	private static final long serialVersionUID = 1L;
	
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mapping(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mapping(request, response);
	}
	
	/**
	 * 
	 * mapping(映射请求)  
	 * @param request request对象
	 * @param response response对象
	 * @since  1.0.0
	 */
	@SuppressWarnings("null")
	public void mapping(HttpServletRequest request, HttpServletResponse response){
		String contextPath=request.getServletContext().getContextPath();
		String path=request.getRequestURL().substring(request.getRequestURL().indexOf(contextPath)+contextPath.length());
		Map<String, Method> requestMappings = ContextLoaderListener.getApplicationContext().getRequestMappings();
		Method method = requestMappings.get(path);
		
		setEncoding(request, response);
			try {
				if(method==null) return;
				AnnotatedType[] annotatedParameterTypes = method.getAnnotatedParameterTypes();
				
				Parameter[] parameters = method.getParameters();
				
				Map<String,Object> paramsMap=new HashMap<String,Object>(); 
				paramsMap.put("method", method);
				
				String paramString="method.invoke(targetObj";
				Object targetObj = BeanFactory.getInstance().getBean(method.getDeclaringClass());
				paramsMap.put("targetObj", targetObj);
				//获取参数名称
				String[] parameterNames = ParameterNameUtils.getMethodParameterNames(method);
				
				for (int i = 0; i < parameters.length; i++) {
					
					Object value=null;
					
					switch (parameters[i].getType().getName()) {
					
					case "javax.servlet.http.HttpServletRequest":
						value=request;
						break;
						
					case "javax.servlet.http.HttpServletResponse":
						value=response;
						break;
						
					case "java.lang.String":
						value=request.getParameter(parameterNames[i]);
						break;
						
					case "int":
						value=Integer.valueOf(request.getParameter(parameterNames[i]));
						break;
					
					case "java.lang.Integer":
						value=Integer.valueOf(request.getParameter(parameterNames[i]));
						break;
						
					case "float":
						value=Float.valueOf(request.getParameter(parameterNames[i]));
						break;
						
					case "java.lang.Float":
						value=Float.valueOf(request.getParameter(parameterNames[i]));
						break;
					
					case "double":
						value=Double.valueOf(request.getParameter(parameterNames[i]));
						break;
						
					case "java.lang.Double":
						value=Double.valueOf(request.getParameter(parameterNames[i]));
						break;

					default:
						String valueString=request.getParameter(parameterNames[i]);
						value = parameters[i].getType().newInstance();
						value=JSONUtil.toJavaBean(value, JSONUtil.toMap(valueString));
						break;
					}
					
					paramString+=","+parameters[i].getName();
					
					paramsMap.put(parameters[i].getName(),value);
					
				}
				paramString+=")";
				
				Object result = MeThodUtil.invokeMethod(paramString, paramsMap);
				
				boolean isJson=false;
				
				switch (result.getClass().getName()) {
				
				case "java.lang.String":
					break;
					
				case "int":
					break;
				
				case "java.lang.Integer":
					break;
					
				case "float":
					break;
					
				case "java.lang.Float":
					break;
				
				case "double":
					break;
					
				case "java.lang.Double":
					break;
					
				case "java.util.ArrayList":
					String resultStr="[";
					List<Object> tmp=(List<Object>) result;
					for (int i=0;i<tmp.size();i++) {
						resultStr+=JSONUtil.toJSON(tmp.get(i)).toString();
						if(i!=tmp.size()-1){
							resultStr+=",";
						}
					}
					
					resultStr+="]";
					
					result=resultStr;
					
					break;	
					
				default:
					isJson=true;
					break;
				}
				if(isJson){
					response.getWriter().write(JSONUtil.toJSON(result).toString());
				}else{
					response.getWriter().write(result.toString());
				} 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * setEncoding (设置编码格式,目前未处理url中文乱码问题)  
	 * @param request request对象
	 * @param response response对象
	 */
	public void setEncoding(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
