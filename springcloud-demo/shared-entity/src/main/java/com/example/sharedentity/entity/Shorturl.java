package com.example.sharedentity.entity;

import com.example.sharedentity.util.Base62Encoder;

import javax.persistence.*;

@Entity
@Table(name = "`shorturls`")
public class Shorturl {
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

    public Shorturl() {}

    public Shorturl(String url, Integer userId) {
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
