package com.example.shorturl.util.dto;

import java.util.List;

public class SimplePage<T> {
    private int number;
    private int size;
    private long totalElements;
    private List<T> content;

    public SimplePage(int number, int size, long totalElements, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
