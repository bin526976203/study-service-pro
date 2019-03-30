package com.neo.entity;

import com.neo.entity.excel.UserInfo;
import lombok.Data;

import java.util.List;
@Data
public class User {
    private String idcard;

    private String studentId;

    private String name;

    private String phone;

    private String password;

    private String address;

    private String company;

    private String extend;

    /**
     * 这个用户即将需要进行学习的课程
     */
    private List<Course> courses;

    public User(){}

    public static User init(UserInfo userInfoExcel) {
        User user = new User();
        user.setIdcard(userInfoExcel.getAccount());
        user.setPassword(userInfoExcel.getPassword());
        user.setName(userInfoExcel.getName());
        user.setStudentId(userInfoExcel.getStudentid());
        return user;
    }
}