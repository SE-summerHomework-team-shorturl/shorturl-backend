package com.example.shorturl.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "short_urls")
public class ShortUrl {
    @Id
    private String id;

    @Field("url")
    private String url;

    @Field("userId")
    private String userId;

    public ShortUrl() {}

    public ShortUrl(String url, String userId) {
        this.url = url;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() throws Exception {
        return id;
    }
}
