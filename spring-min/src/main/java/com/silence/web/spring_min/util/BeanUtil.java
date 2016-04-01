package com.silence.web.spring_min.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * (bean工具类)
 * @author tanxingyi 
 * 2015年8月13日 下午2:43:30
 */
public class BeanUtil {

	public static <T> T MapToBean(Class<T> to, Map<String, Object> map) {
		try {
			if (map != null) {
				T t = to.newInstance();
				BeanUtils.populate(t, map);
				return t;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> ListMapToBean(Class<T> to, List<Map<String, Object>> maps) {
		try {
			List<T> temp = new ArrayList<T>();
			if (maps != null) {

				for (int i = 0; i < maps.size(); i++) {
					T t = to.newInstance();
					BeanUtils.populate(t, maps.get(i));
					temp.add(t);
				}
				return temp;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
