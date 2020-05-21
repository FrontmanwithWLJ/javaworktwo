package com.sl.web.bean;

import com.sl.web.response.SqlResponse;

import java.util.ArrayList;

public class SearchResult<T> {
    //查询页数
    private int page;
    //每每页的数量
    private int count;
    private int totalPage;
    //最后要吧结果全部列出来，不需要随机访问
    private ArrayList<T> list;

    private SqlResponse response;

    public SearchResult(){
        this(0,0,null);
    }
    public SearchResult(int page,int count,ArrayList<T> list){
        this.page = page;
        this.count = count;
        this.list = list;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
