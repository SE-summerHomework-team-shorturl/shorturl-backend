package com.example.sharedentity.entity;

import com.example.sharedentity.util.Base62Encoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "`short_urls`")
public class ShortUrl implements Serializable {
    static private final long serialVersionUID = -1L;

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
    private Long userId;

    @Transient
    private ShortUrlStat shortUrlStat;

    public ShortUrl() {}

    public ShortUrl(String url, Long userId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ShortUrlStat getShortUrlStat() {
        return shortUrlStat;
    }

    public void setShortUrlStat(ShortUrlStat shortUrlStat) {
        this.shortUrlStat = shortUrlStat;
    }

    public String getToken() throws Exception {
        return Base62Encoder.encode(id);
    }

    public ShortUrl(Long id, String url, Long userId) {
        this.id = id;
        this.url = url;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(id, shortUrl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
