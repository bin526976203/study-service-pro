package com.neo.entity;

import com.neo.entity.excel.UserInfo;

public class User {
    private String idcard;

    private String studentId;

    private String name;

    private String phone;

    private String password;

    private String address;

    private String company;

    private String extend;

    public User(){}

    public static User init(UserInfo userInfoExcel) {
        User user = new User();
        user.setIdcard(userInfoExcel.getAccount());
        user.setPassword(userInfoExcel.getPassword());
        user.setName(userInfoExcel.getName());
        user.setStudentId(userInfoExcel.getStudentid());
        return user;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }
}