package com.huatec.domain;

import java.io.Serializable;

public class Admin implements Serializable {

     public static final long serialVersionUID = 1L;

     private Integer id; //管理员编号
     private String username;
     private String password;
     private String role_name;//管理员角色


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
