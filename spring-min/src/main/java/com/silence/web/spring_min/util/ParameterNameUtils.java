package com.silence.web.spring_min.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class ParameterNameUtils {
	public static String[] getMethodParameterNames(final Method method) {
		String [] result=null;
		
		ClassPool pool = ClassPool.getDefault();
		
		pool.insertClassPath(new ClassClassPath(ParameterNameUtils.class));

		Class<?> clazz = method.getDeclaringClass();
		CtClass ctClass;
		try {
			ctClass = pool.get(clazz.getName());

			CtMethod cm = ctClass.getDeclaredMethod(method.getName());

			MethodInfo methodInfo = cm.getMethodInfo();

			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);

			String[] paramNames = new String[cm.getParameterTypes().length];

			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;

			for (int j = 0; j < paramNames.length; j++)
				paramNames[j] = attr.variableName(j + pos);

			result=new String[paramNames.length];
			for (int i1 = 0; i1 < paramNames.length; i1++) {
				result[i1]=paramNames[i1];
			}
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
