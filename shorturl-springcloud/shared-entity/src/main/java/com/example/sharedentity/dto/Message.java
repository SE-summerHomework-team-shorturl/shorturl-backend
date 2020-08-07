package com.example.sharedentity.dto;

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
}
