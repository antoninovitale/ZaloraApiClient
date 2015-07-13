package com.example.zalora.model;

import java.util.HashMap;
import java.util.Map;

public class Response {

private boolean success;
private Messages messages;
private Session session;
private Metadata metadata;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public boolean isSuccess() {
return success;
}

public void setSuccess(boolean success) {
this.success = success;
}

public Messages getMessages() {
return messages;
}

public void setMessages(Messages messages) {
this.messages = messages;
}

public Session getSession() {
return session;
}

public void setSession(Session session) {
this.session = session;
}

public Metadata getMetadata() {
return metadata;
}

public void setMetadata(Metadata metadata) {
this.metadata = metadata;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperties(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
