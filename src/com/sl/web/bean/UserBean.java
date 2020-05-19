package com.sl.web.bean;

public class UserBean extends BaseBean {
    /**
     * 登录信息
     */
    private String userName;
    private String password;
    public UserBean(){
        this("","");
    }
    public UserBean(String name,String password){
        this(-1,name,password);
    }

    public UserBean(int id,String name,String password){
        setID(id);
        setUserName(name);
        setPassword(password);
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
}
