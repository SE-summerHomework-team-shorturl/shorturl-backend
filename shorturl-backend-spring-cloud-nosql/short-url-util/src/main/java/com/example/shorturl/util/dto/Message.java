package com.example.shorturl.util.dto;

public class Message {
    private String status;
    private Object body;

    public Message() {
        this.status = null;
        this.body = null;
    }

    public Message(String status, Object body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
