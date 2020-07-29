package com.example.shorturl.entity;

import com.example.shorturl.util.Base62Encoder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`short_urls`")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Long id;

    @Basic
    @Column(name = "`url`")
    private String url;

    @Basic
    @Column(name = "`user`")
    private Integer userId;

    public ShortUrl() {}

    public ShortUrl(String url, Integer userId) {
        this.url = url;
        this.userId = userId;
    }

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
