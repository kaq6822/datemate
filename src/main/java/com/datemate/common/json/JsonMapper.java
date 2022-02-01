package com.datemate.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;


/** 
 * JSON을 이용하여 객체 바인딩을 사용할 떄 상속하는 클래스 이다.
 * 
 * 이 클래스는 jackson 라이버리를 이용해서 ObjectMapping을 수행한다.
 * 
 * @author jungyeop
 *
 */
public class JsonMapper {

	static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public JsonMapper() {
	}

	public JsonMapper(String jsonString) {
		readValues(jsonString);
	}

	protected void readValues(String jsonString) {
		readValues(this, jsonString);
	}

	public <T> T readValues(T object, String jsonString) {
		try {
			mapper.readerForUpdating(object).readValue(jsonString);
			
			return object;
		} catch (Exception e) {
		    IllegalArgumentException ex =new IllegalArgumentException("JSON Parsing Failed.");
		    ex.initCause(e);
		    throw ex;
		}
	}

	/**
	 * 내부 변수이름과 데이터를 [변수이름=데이터,]형식의 문자열로 반환하는 함수. 객체 디버깅에 사용된다.
	 */
	public String toString() {
		return toBeanContent(this);
	}

	public String toJsonString() {
		return toJsonString(this);
	}

	public static String toJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	static String toBeanContent(Object vo) {
		try {
			Class<? extends Object> clazz = vo.getClass();
			Class<? extends Object> superClazz = clazz.getSuperclass();

			Method[] methods = clazz.getDeclaredMethods();
			Method[] superMethods = superClazz.getDeclaredMethods();
			StringBuilder sb = new StringBuilder(clazz.getName() + "\n");

			Method m = null;
			String methodName = null;

			if (superMethods != null && superMethods.length > 0) {
				for (int i = 0; i < superMethods.length; i++) {
					m = superMethods[i];
					methodName = m.getName();
					if (methodName.startsWith("get")) {
						sb.append(methodName.substring(3) + " : " + m.invoke(vo, (Object) null) + "\n");
					}
				}
			}
			if (methods != null && methods.length > 0) {

				for (int i = 0; i < methods.length; i++) {
					m = methods[i];
					methodName = m.getName();
					if (methodName.startsWith("get")) {
						sb.append(methodName.substring(3) + " : " + m.invoke(vo, (Object) null) + "\n");
					}
				}
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
