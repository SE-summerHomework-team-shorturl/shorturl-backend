package com.example.sharedentity.entity;

import com.example.sharedentity.util.Base62Encoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "`statistics`")
public class UrlStatistic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Long id;

    @Basic
    @Column(name = "`clicks`")
    private Integer clicks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public UrlStatistic() {
        this.clicks=0;
    }
    public UrlStatistic(Long id) {
        this.id=id;
        this.clicks=0;
    }
}





