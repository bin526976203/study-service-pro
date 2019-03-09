package com.neo.entity.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author moxianbin
 * @date 2019/3/9.
 */
public class CourseInfo {

    @Excel(name = "lessonId")
    private String lessonId;

    @Excel(name = "lessonName")
    private String lessonName;

    @Excel(name = "classId")
    private String classId;

    @Excel(name = "bmid")
    private String bmid;

    @Excel(name = "courseId")
    private String courseId;

    @Excel(name = "courseHour")
    private Integer courseHour;

    @Excel(name = "courseMin")
    private Integer courseMin;


    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    public Integer getCourseMin() {
        return courseMin;
    }

    public void setCourseMin(Integer courseMin) {
        this.courseMin = courseMin;
    }
}
