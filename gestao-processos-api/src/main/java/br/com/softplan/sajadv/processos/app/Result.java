package br.com.softplan.sajadv.processos.app;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Result {

    private final Map<String, Object> entity;
    private final Map<String, Object> headers;
    private final List<String> messages;

    public Result() {
        entity = new HashMap<>();
        headers = new HashMap<>();
        messages = new ArrayList<>();
    }

    public Result include(final String key, final Object value) {
        entity.put(key, value);
        return this;
    }

    public Result addHeader(final String key, final Object value) {
        headers.put(key, value);
        return this;
    }

    public Result addMessage(final Messages message) {
        messages.add(message.toString());
        return this;
    }

    public Result addMessage(final String message) {
        messages.add(message);
        return this;
    }

    public Result addMessage(final Messages message, final Object... arguments) {
        messages.add(message.format(arguments));
        return this;
    }

    public Response asResponse(final Status status) {
        return Response.status(status).entity(getEntity()).build();
    }

    public Map<String, Object> getEntity() {
        entity.put("messages", getMessages());
        return entity;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public List<String> getMessages() {
        return messages;
    }
}
