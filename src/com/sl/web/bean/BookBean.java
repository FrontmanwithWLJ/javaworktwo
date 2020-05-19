package com.sl.web.bean;

public class BookBean extends BaseBean {
    //作者id
    private String authorName;
    //出版商
    private String publisherName;
    //publish date
    private String date;
    //书名
    private String bookName;
    //价格
    private float price;
    public BookBean(){
        this(0,"","","",0f);
    }
    public BookBean(int id,String authorName,String publisherName,String bookName,float price){
        setID(id);
        setAuthorName(authorName);
        setPublisherName(publisherName);
        setBookName(bookName);
        setPrice(price);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
