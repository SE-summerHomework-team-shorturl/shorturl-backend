package com.example.sharedentity.entity;

import com.example.sharedentity.util.Base62Encoder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`short_urls`")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(id, shortUrl.id) &&
                Objects.equals(url, shortUrl.url) &&
                Objects.equals(userId, shortUrl.userId);
    }

    public ShortUrl(Integer id, String url, Integer userId) {
        this.id = id;
        this.url = url;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, userId);
    }
}
