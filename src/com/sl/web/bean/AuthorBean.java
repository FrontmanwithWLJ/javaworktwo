package com.sl.web.bean;

public class AuthorBean extends BaseBean {
    //作者名
    private String authorName;

    public AuthorBean(){
        this(0,"");
    }
    public AuthorBean(int id,String name){
        setID(id);
        setAuthorName(name);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName.trim();
    }
}
