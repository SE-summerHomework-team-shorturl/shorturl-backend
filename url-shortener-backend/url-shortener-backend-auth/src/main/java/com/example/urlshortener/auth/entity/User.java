package com.example.urlshortener.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`users`")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

    @Basic
    @Column(name = "`username`")
    private String username;

    @Basic
    @Column(name = "`password`")
    private String password;

    @Basic
    @Column(name = "`email`")
    private String email;

    @Basic
    @Column(name = "`admin`")
    private Boolean admin;

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public Boolean getAdmin() {
        return admin;
    }

    @JsonIgnore
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
