package com.neo.mapper;


import com.neo.entity.Lesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LessonMapper {
    int deleteByPrimaryKey(String lessonId);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String lessonId);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);

    /**
     *
     * @param classId
     * @return
     */
    List<Lesson> selectByClassId(@Param("classId") String classId);
}