package com.example.sharedentity.entity;

import com.example.sharedentity.util.Base62Encoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "`short_urls`")
public class ShortUrl implements Serializable {
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
    private Integer clicks;

    public ShortUrl() {
        this.clicks = 0;
    }

    public ShortUrl(String url, Long userId) {
        this.url = url;
        this.userId = userId;
        this.clicks = 0;
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

    public String getToken() throws Exception {
        return Base62Encoder.encode(id);
    }

    public ShortUrl(Long id, String url, Long userId) {
        this.id = id;
        this.url = url;
        this.userId = userId;
        this.clicks = 0;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortUrl shortUrl = (ShortUrl) o;

        if (id != null ? !id.equals(shortUrl.id) : shortUrl.id != null) return false;
        if (url != null ? !url.equals(shortUrl.url) : shortUrl.url != null) return false;
        if (userId != null ? !userId.equals(shortUrl.userId) : shortUrl.userId != null) return false;
        return clicks != null ? clicks.equals(shortUrl.clicks) : shortUrl.clicks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (clicks != null ? clicks.hashCode() : 0);
        return result;
    }
}
