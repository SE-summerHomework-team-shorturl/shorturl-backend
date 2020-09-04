package com.example.sharedentity.dto;

import java.util.Objects;

public class Message {
    /* status String */
    static public String Success_Msg="SUCCESS";
    static public String Invalid_Url_Msg="INVALID_URL";
    static public String Dup_User_Msg="DUP_USERNAME";
    static public String No_URL_Msg="NO_SUCH_URL";
    static public String Not_Your_URL_Msg="NOT_YOUR_SHORT_URL";
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Message message = (Message) o;
        return Objects.equals(status, message.status) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, body);
    }
}
