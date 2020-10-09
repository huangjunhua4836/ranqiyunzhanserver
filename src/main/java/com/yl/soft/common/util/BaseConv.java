package com.yl.soft.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface BaseConv {

	static final Logger logger = LoggerFactory.getLogger(BaseConv.class);

	public static <T, R> R copy( T source,  R target, String... ignores) {
		if (source==null) {
			return null;
		}
		BeanUtils.copyProperties(source, target, ignores);
		return target;
	}

	public static <T, R> R copy( T source,  R target) {
		return copy(source, target, new String[0]);
	}

	public static <T> T setCreate(T t, Integer createby) {
		try {
			Method method = t.getClass().getDeclaredMethod("setDeleted", Boolean.class);
			method.invoke(t, Boolean.FALSE);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignored) {
		}
		try {
			Method method = t.getClass().getDeclaredMethod("setCreateat", LocalDateTime.class);
			method.invoke(t, LocalDateTime.now());
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignored) {
		}
		if (createby != null) {
			try {
				Method method = t.getClass().getDeclaredMethod("setCreateby", Integer.class);
				method.invoke(t, createby);
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException ignored) {
			}
		}
		return setUpdate(t, createby);
	}

	public static <T> T setUpdate(T t, Integer updateby) {
		try {
			Method method = t.getClass().getDeclaredMethod("setupdateat", LocalDateTime.class);
			method.invoke(t, LocalDateTime.now());
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignored) {
		}
		if (updateby != null) {
			try {
				Method method = t.getClass().getDeclaredMethod("setUpdateby", Integer.class);
				method.invoke(t, updateby);
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException ignored) {
			}
		}
		return t;
	}

	public static <T> List<T> jsonList(String value, Class<T> clazz) {
		if (StringUtils.isNotBlank(value)) {
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
			try {
				return objectMapper.readValue(value, javaType);
			} catch (JsonProcessingException e) {
				logger.error("JSON:", e);
				return null;
			}
		} else {
			return Collections.emptyList();
		}
	}

	public static String jsonList(List<?> value) {
		if (value == null || value.isEmpty()) {
			return "[]";
		}
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			logger.error("JSON:", e);
		}
		return null;
	}

	public static <T> T json(String value, Class<T> clazz) {
		if (StringUtils.isNotBlank(value)) {
			try {
				return new ObjectMapper().readValue(value, clazz);
			} catch (JsonProcessingException e) {
				logger.error("JSON:", e);
			}
		}
		return null;
	}

	public static String json(Object value) {
		if (value == null) {
			return "";
		}
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			logger.error("JSON:", e);
		}
		return null;
	}
}
