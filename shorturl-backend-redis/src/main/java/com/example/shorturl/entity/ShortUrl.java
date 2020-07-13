package com.example.shorturl.entity;

import com.example.shorturl.util.Base62Encoder;

import java.util.Objects;

public class ShortUrl {
    private Long id;
    private String url;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() throws Exception {
        Base62Encoder encoder = new Base62Encoder();
        return id == null ? "" : encoder.encode(id);
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
