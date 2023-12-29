package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

	public <T> List<T> toListModel(Class<T> tClass) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, tClass);
			return mapper.readValue(value, type);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return Collections.emptyList(); // Trả về danh sách rỗng nếu có lỗi
	}

	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
