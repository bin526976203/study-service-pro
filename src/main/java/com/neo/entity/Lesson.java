package com.neo.entity;

import lombok.Data;

import java.util.List;

@Data
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


}