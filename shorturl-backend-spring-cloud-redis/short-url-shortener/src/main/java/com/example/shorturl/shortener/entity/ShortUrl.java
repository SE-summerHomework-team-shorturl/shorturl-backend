package com.example.shorturl.shortener.entity;

import com.example.shorturl.util.algorithm.Base62Encoder;

import java.util.Objects;

public class ShortUrl {
    private Long id;
    private String url;
    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getToken() {
        Base62Encoder base62Encoder = new Base62Encoder();
        return base62Encoder.encode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return id.equals(shortUrl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
