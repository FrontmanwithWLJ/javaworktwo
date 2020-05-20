package com.sl.web.bean;

import java.sql.Date;

public class BookBean extends BaseBean {
    //作者id
    private String authorName;
    //出版商
    private String publisherName;
    //书名
    private String bookName;
    //价格
    private float price;
    //publish date
    private Date date;
    public BookBean(){
        this(0,"","","",0f,null);
    }
    public BookBean(int id,String bookName,String authorName,
                    String publisherName,float price,Date date){
        setID(id);
        setAuthorName(authorName);
        setPublisherName(publisherName);
        setBookName(bookName);
        setPrice(price);
        setDate(date);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName.trim();
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName.trim();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
