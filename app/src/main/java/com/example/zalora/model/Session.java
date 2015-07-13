package com.example.zalora.model;

import java.util.HashMap;
import java.util.Map;

public class Session {

	private String id;
	private String expire;
	private String YII_CSRF_TOKEN;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getYII_CSRF_TOKEN() {
		return YII_CSRF_TOKEN;
	}

	public void setYII_CSRF_TOKEN(String YII_CSRF_TOKEN) {
		this.YII_CSRF_TOKEN = YII_CSRF_TOKEN;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
