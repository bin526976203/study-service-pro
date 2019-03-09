package com.neo.entity;

import com.neo.entity.excel.CourseInfo;

public class Course {
    private String courseId;

    private String courseName;

    private Integer courseStudyTimeHour;

    private Integer courseStudyTimeMin;

    private Integer courseStudyTimeSecond;

    private String lessonId;

    public Course() {}

    public Course(String courseId, String lessonId, Integer courseStudyTimeHour, Integer courseStudyTimeMin, Integer courseStudyTimeSecond) {
        this.courseId = courseId;
        this.courseStudyTimeHour = courseStudyTimeHour;
        this.courseStudyTimeMin = courseStudyTimeMin;
        this.courseStudyTimeSecond = courseStudyTimeSecond;
        this.lessonId = lessonId;
    }

    public static Course init(CourseInfo courseInfo){
        Course course = new Course();
        course.setLessonId(courseInfo.getLessonId());
        course.setCourseId(courseInfo.getCourseId());
        course.setCourseStudyTimeHour(courseInfo.getCourseHour());
        course.setCourseStudyTimeMin(courseInfo.getCourseMin());

        return course;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getCourseStudyTimeHour() {
        return courseStudyTimeHour;
    }

    public void setCourseStudyTimeHour(Integer courseStudyTimeHour) {
        this.courseStudyTimeHour = courseStudyTimeHour;
    }

    public Integer getCourseStudyTimeMin() {
        return courseStudyTimeMin;
    }

    public void setCourseStudyTimeMin(Integer courseStudyTimeMin) {
        this.courseStudyTimeMin = courseStudyTimeMin;
    }

    public Integer getCourseStudyTimeSecond() {
        return courseStudyTimeSecond;
    }

    public void setCourseStudyTimeSecond(Integer courseStudyTimeSecond) {
        this.courseStudyTimeSecond = courseStudyTimeSecond;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }
}