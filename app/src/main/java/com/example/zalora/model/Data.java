package com.example.zalora.model;

import java.util.HashMap;
import java.util.Map;

public class Data {

	private String sku;
	private String name;
	private boolean new_product;
	private String url;
	private Simples simples;
	private String brand;
	private String max_price;
	private String price;
	private String max_special_price;
	private String special_price;
	private String max_saving_percentage;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNew_product() {
		return new_product;
	}

	public void setNew_product(boolean new_product) {
		this.new_product = new_product;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Simples getSimples() {
		return simples;
	}

	public void setSimples(Simples simples) {
		this.simples = simples;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMax_price() {
		return max_price;
	}

	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMax_special_price() {
		return max_special_price;
	}

	public void setMax_special_price(String max_special_price) {
		this.max_special_price = max_special_price;
	}

	public String getSpecial_price() {
		return special_price;
	}

	public void setSpecial_price(String special_price) {
		this.special_price = special_price;
	}

	public String getMax_saving_percentage() {
		return max_saving_percentage;
	}

	public void setMax_saving_percentage(String max_saving_percentage) {
		this.max_saving_percentage = max_saving_percentage;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}