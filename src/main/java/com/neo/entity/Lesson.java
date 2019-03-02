package com.neo.entity;

import java.util.List;

public class Lesson {
    private String lessonId;

    private String courseId;

    private String lessonName;

    private Integer lessonStudyHour;

    private Integer lessonStudyMin;

    private Integer lessonStudySecond;

    private String classId;

    private String bmId;

    private List<Course> courses;

    public Lesson(){}

    public Lesson(String lessonId, String courseId, Integer lessonStudyHour, Integer lessonStudyMin, Integer lessonStudySecond) {
        this.lessonId = lessonId;
        this.courseId = courseId;
        this.lessonStudyHour = lessonStudyHour;
        this.lessonStudyMin = lessonStudyMin;
        this.lessonStudySecond = lessonStudySecond;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId == null ? null : lessonId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName == null ? null : lessonName.trim();
    }

    public Integer getLessonStudyHour() {
        return lessonStudyHour;
    }

    public void setLessonStudyHour(Integer lessonStudyHour) {
        this.lessonStudyHour = lessonStudyHour;
    }

    public Integer getLessonStudyMin() {
        return lessonStudyMin;
    }

    public void setLessonStudyMin(Integer lessonStudyMin) {
        this.lessonStudyMin = lessonStudyMin;
    }

    public Integer getLessonStudySecond() {
        return lessonStudySecond;
    }

    public void setLessonStudySecond(Integer lessonStudySecond) {
        this.lessonStudySecond = lessonStudySecond;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getBmId() {
        return bmId;
    }

    public void setBmId(String bmId) {
        this.bmId = bmId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}