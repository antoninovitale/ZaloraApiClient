package com.example.zalora.model;

import java.util.HashMap;
import java.util.Map;

public class Attributes {

	private String size;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}