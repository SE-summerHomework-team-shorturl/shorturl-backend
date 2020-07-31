package com.example.shorturl.shortener.dao;

public interface SequenceDao {
    long addAndGetByName(String name);
}
