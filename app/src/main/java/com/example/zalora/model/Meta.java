package com.example.zalora.model;

import java.util.HashMap;
import java.util.Map;


public class Meta {

    private String sku;
    private String price;
    private String caching_hash;
    private String shipment_cost_item;
    private String shipment_cost_order;
    private String special_price;
    private String tax_percent;
    private String quantity;
    private String size_position;
    private String estimated_delivery;
    private String estimated_delivery_position;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCaching_hash() {
        return caching_hash;
    }

    public void setCaching_hash(String caching_hash) {
        this.caching_hash = caching_hash;
    }

    public String getShipment_cost_item() {
        return shipment_cost_item;
    }

    public void setShipment_cost_item(String shipment_cost_item) {
        this.shipment_cost_item = shipment_cost_item;
    }

    public String getShipment_cost_order() {
        return shipment_cost_order;
    }

    public void setShipment_cost_order(String shipment_cost_order) {
        this.shipment_cost_order = shipment_cost_order;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(String tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize_position() {
        return size_position;
    }

    public void setSize_position(String size_position) {
        this.size_position = size_position;
    }

    public String getEstimated_delivery() {
        return estimated_delivery;
    }

    public void setEstimated_delivery(String estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
    }

    public String getEstimated_delivery_position() {
        return estimated_delivery_position;
    }

    public void setEstimated_delivery_position(String estimated_delivery_position) {
        this.estimated_delivery_position = estimated_delivery_position;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
