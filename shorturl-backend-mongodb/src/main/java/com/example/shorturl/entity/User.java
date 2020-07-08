package com.example.shorturl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Document(collection = "users")
public class User {
    @Id
    private Integer id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("email")
    private String email;

    @Field("admin")
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
