package com.gh.util.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gh.util.JacksonMapper;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.lang.reflect.*;
import java.util.*;

//import org.codehaus.jackson.annotate.JsonMethod;

/**
 * 结果集工具
 * 
 * @author admin
 *
 */
public class ParameterUtil {
	public static HttpEntity<String> getHttpEntity(Map<String, Object> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String paramJson = JacksonMapper.beanToJson(param);
		HttpEntity<String> requestEntity = new HttpEntity<String>(paramJson, headers);
		return requestEntity;
	}

	public static HttpEntity<Map<String, HttpHeaders>> getHttpEntity(Map<String, Object> param, HttpHeaders headers) {
		if (headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		String paramJson = JacksonMapper.beanToJson(param);
		HttpEntity<Map<String, HttpHeaders>> requestEntity = new HttpEntity(paramJson, headers);
		return requestEntity;
	}

	public static <T> HttpEntity<T> getHttpEntity(HttpHeaders headers) {
		if (headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<T> requestEntity = new HttpEntity<T>(null, headers);
		return requestEntity;
	}

	public static <T> T mapToBean(Object vo, Class<T> cls) {
		if (vo == null) {
			return null;
		}
		if (vo instanceof LinkedHashMap || vo instanceof TreeMap || vo instanceof LinkedTreeMap || vo instanceof Map) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			
			T tt = (T) objectMapper.convertValue(vo, cls);
			return tt;
		} else if (vo instanceof String) {

			T tt = (T) JacksonMapper.jsonToBean((String) vo, cls);
			return tt;
		}
		throw new RuntimeException("对象转换失败！" + vo);
	}

	public static Map<String, Object> beanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("map转换失败！" + obj);
			}
		}
		return map;
	}

	public static <T> List<T> mapToList(Object obj, Class<T> cls) {
		return toListBean(obj, cls);
	}

	public static <T> List<T> toListBean(Object vo, Class<T> clz) {
		if (vo == null) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clz);
		T tt = objectMapper.convertValue(vo, javaType);
		// T tt = objectMapper.readValue(vo.toString(), javaType);
		return (List<T>) tt;
	}

	/**
	 * 判断是否为基础类型
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseDataType(Class<?> clazz) {
		return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
				|| clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Float.class)
				|| clazz.equals(Double.class) || clazz.equals(Byte.class) || clazz.equals(Character.class)
				|| clazz.equals(Short.class) || clazz.equals(Date.class) || clazz.equals(byte[].class)
				|| clazz.equals(Byte[].class);
	}

	public static <T> Class getTClass(T t) {
		Type[] params = t.getClass().getGenericInterfaces();
		Type type = params[0];
		Type finalNeedType;
		if (params.length > 1) {
			if (!(type instanceof ParameterizedType))
				throw new IllegalStateException("没有填写泛型参数");
			finalNeedType = ((ParameterizedType) type).getActualTypeArguments()[0];
		} else {
			finalNeedType = type;
		}
		final Class clazz = getClass(finalNeedType, 0);
		return clazz;
	}

	public static Class getClass(Type type, int i) {
		if (type instanceof ParameterizedType) {
			return getGenericClass((ParameterizedType) type, i);
		} else if (type instanceof TypeVariable) {
			return (Class) getClass(((TypeVariable) type).getBounds()[0], 0);
		} else {
			return (Class) type;
		}
	}

	public static Class getGenericClass(ParameterizedType parameterizedType, int i) {
		Object genericClass = parameterizedType.getActualTypeArguments()[i];
		if (genericClass instanceof ParameterizedType) {
			return (Class) ((ParameterizedType) genericClass).getRawType();
		} else if (genericClass instanceof GenericArrayType) {
			return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
		} else if (genericClass instanceof TypeVariable) {
			return (Class) getClass(((TypeVariable) genericClass).getBounds()[0], 0);
		} else {
			return (Class) genericClass;
		}
	}
}
