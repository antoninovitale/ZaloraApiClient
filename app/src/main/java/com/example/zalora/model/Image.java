package com.example.zalora.model;
import java.util.HashMap;
import java.util.Map;

public class Image {

private String path;
private String format;
private String width;
private String height;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getPath() {
return path;
}

public void setPath(String path) {
this.path = path;
}

public String getFormat() {
return format;
}

public void setFormat(String format) {
this.format = format;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperties(String name, Object value) {
this.additionalProperties.put(name, value);
}

}