package com.sl.web.bean;

public class PublisherBean extends BaseBean {
    //出版商名
    private String publisherName;
    public PublisherBean(){
        this(0,"");
    }
    public PublisherBean(int id,String name){
        setID(id);
        setPublisherName(name);
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName.trim();
    }
}
