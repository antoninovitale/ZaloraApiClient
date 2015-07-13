package com.example.zalora.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metadata {
private String product_count;
private String category_ids;
private List<Result> results = new ArrayList<Result>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getProduct_count() {
return product_count;
}

public void setProduct_count(String product_count) {
this.product_count = product_count;
}

public String getCategory_ids() {
return category_ids;
}

public void setCategory_ids(String category_ids) {
this.category_ids = category_ids;
}

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperties(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
