package com.neo.mapper;


import com.neo.entity.Lesson;

public interface LessonMapper {
    int deleteByPrimaryKey(String lessonId);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String lessonId);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
}