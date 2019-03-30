package com.neo.entity;

import com.neo.entity.excel.CourseInfo;
import lombok.Data;

@Data
public class Course {
    private String courseId;

    private String courseName;

    private Integer courseStudyTimeHour;

    private Integer courseStudyTimeMin;

    private Integer courseStudyTimeSecond;

    private String lessonId;

    private String classId;

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
        course.setClassId(courseInfo.getClassId());

        return course;
    }

}