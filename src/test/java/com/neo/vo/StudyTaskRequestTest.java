package com.neo.vo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.neo.entity.Course;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author moxianbin on 2019/3/1.
 */
public class StudyTaskRequestTest {
    private final static Logger log = LoggerFactory.getLogger(StudyTaskRequestTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test(){

        List<User> users = Lists.newArrayList();
        User user2 = new User();
        user2.setIdcard("511028198807110029");
        users.add(user2);

        List<Lesson> lessonVos = Lists.newArrayList();
        Lesson lesson = new Lesson();
        lesson.setLessonId("8a8a834067159f62016716591ff101ec");
        lesson.setBmId("2019lcjl1");
        lesson.setClassId("c201911381");
        List<Course> courses = Lists.newArrayList();
        courses.add(new Course("159dea8fd0154238bafefb3c3ba24efb", lesson.getLessonId(), 0, 16, 0));
        courses.add(new Course("b07db79c91e44f2ebe65c61de91e0a94", lesson.getLessonId(), 0, 17, 0));
        courses.add(new Course("32ef3849c6c6437b9f106f834f5c636e", lesson.getLessonId(), 0, 17, 0));
        courses.add(new Course("4a0b083455b44da08104686aba4e8fb3", lesson.getLessonId(), 0, 22, 0));
        courses.add(new Course("6f6fafe6dfdf498b95567c8ac9379122", lesson.getLessonId(), 0, 22, 0));
        courses.add(new Course("812df88f3d2e407fa156e011cccf2850", lesson.getLessonId(), 0, 16, 0));
        courses.add(new Course("77703d3582724698be7077249bf95871", lesson.getLessonId(), 0, 19, 0));
        courses.add(new Course("8ad30b284e6248e0866425feb7dd18cd", lesson.getLessonId(), 0, 20, 0));
        lesson.setCourses(courses);
        lessonVos.add(lesson);

        StudyTaskRequest request = new StudyTaskRequest();
        request.setUsers(users);
        request.setLessons(lessonVos);

        log.info("{}", JSONObject.toJSONString(request));
    }



    @Test
    public void testStringsIsEmpty(){

        System.err.print(Strings.isNullOrEmpty(null));
    }
}