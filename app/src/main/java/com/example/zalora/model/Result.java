package com.example.zalora.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Result {

private String id;
private Data data;
private List<Image> images = new ArrayList<Image>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public List<Image> getImages() {
return images;
}

public void setImages(List<Image> images) {
this.images = images;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperties(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
