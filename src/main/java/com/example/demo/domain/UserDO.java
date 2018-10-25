package com.example.demo.domain;

import java.io.Serializable;

public class UserDO implements Serializable {
    private String username;
    private String password;
    private String phone;
    private String email;

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
}
