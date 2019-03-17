package com.neo.entity.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author moxianbin
 * @date 2019/3/2.
 */
public class UserInfo {

    @Excel(name = "account")
    private String account;

    @Excel(name = "password")
    private String password;

    @Excel(name = "studentid")
    private String studentid;

    @Excel(name = "bmid")
    private String bmid;

    @Excel(name = "name")
    private String name;

    @Excel(name = "classid")
    private String classId;

    @Excel(name = "lessonname")
    private String lessonName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
