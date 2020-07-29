package com.example.shorturl.manager.entity;

import com.example.shorturl.util.misc.Base62Encoder;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "short_urls")
@AccessType(value = AccessType.Type.FIELD)
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

    public String getToken() {
        Base62Encoder base62Encoder = new Base62Encoder();
        return base62Encoder.encode(id);
    }
}
