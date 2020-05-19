package com.sl.web.bean;

public class Message extends BaseBean {
    private String userName = "";
    private String newMessage = "";
    public Message(){
        this(-1,"","");
    }
    public Message(int id,String userName,String newMessage){
        setID(id);
        setUserName(userName);
        setNewMessage(newMessage);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
}
