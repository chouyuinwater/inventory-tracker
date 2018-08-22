package com.jd.inventory.tracker.domain.vo;

import java.util.List;

public class Page {

    private String sort;
    private String order;
    private int page;
    private int pages;
    private int total;
    public int pageSize = 10;
    public List rows;

    public Page() {
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        if (this.rows != null && this.rows.size() == 1) {
            Object obj = this.rows.get(0);
            if (obj instanceof String) {
                this.pageSize = Integer.parseInt(this.rows.get(0).toString());
            }
        }

        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0 && pageSize < 10000000) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 10;
        }

    }

    public List getRows() {
        return this.rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPages() {
        return this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Page{" +
                "sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", page=" + page +
                ", pages=" + pages +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", rows=" + rows +
                '}';
    }
}
