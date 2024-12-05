package com.example;

import java.util.List;

public class PageResponse {
    private List<String> data;
    private int totalPages;
    private int totalRecords;

    public PageResponse(List<String> data, int totalPages, int totalRecords) {
        this.data = data;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
