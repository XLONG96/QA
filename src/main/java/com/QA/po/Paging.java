package com.QA.po;

import java.util.List;

public class Paging<Object> {
    private int totalPage;
    private int totalNum;
    private int currentPage;
    private int perNum;
    private List<Object> pageContent;
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerNum() {
        return perNum;
    }

    public void setPerNum(int perNum) {
        this.perNum = perNum;
    }

    public List<Object> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<Object> pageContent) {
        this.pageContent = pageContent;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "totalPage=" + totalPage +
                ", totalNum=" + totalNum +
                ", currentPage=" + currentPage +
                ", perNum=" + perNum +
                ", pageContent=" + pageContent +
                '}';
    }
}
