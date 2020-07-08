package com.example.shorturl.entity;

import com.example.shorturl.util.Base62Encoder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Document(collection = "short_urls")
public class ShortUrl {
    @Id
    private Integer id;

    @Field("url")
    private String url;

    @Field("userId")
    private Integer userId;

    public ShortUrl() {}

    public ShortUrl(Integer id, String url, Integer userId) {
        this.id = id;
        this.url = url;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() throws Exception {
        return Base62Encoder.encode(id);
    }
}
