package com.sl.web.bean;

public class UserBean extends BaseBean {
    /**
     * 登录信息
     */
    private String userName;
    private String password;
    private String phoneNumber;
    public UserBean(){
        this("","");
    }
    public UserBean(String name,String password){
        this(name,password,"0000-0000");
    }

    public UserBean(String name,String password,String phoneNumber){
        this(-1,name,password,phoneNumber);
    }

    public UserBean(int id,String name,String password){
        this(id,name,password,"0000-0000");
    }

    public UserBean(int id,String name,String password,String phoneNumber){
        setID(id);
        setUserName(name);
        setPassword(password);
        setPhoneNumber(phoneNumber);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
