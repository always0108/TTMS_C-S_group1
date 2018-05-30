package model;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private  String password;
    private String sex;

    public User(){

    }

    public User(String username, String sex){
        this.username = username;
        this.sex = sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
