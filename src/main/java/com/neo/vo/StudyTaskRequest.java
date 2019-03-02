package com.neo.vo;

import com.neo.entity.Lesson;
import com.neo.entity.User;

import java.util.List;

/**
 * @author moxianbin on 2019/3/1.
 */
public class StudyTaskRequest {

    private List<User> users;

    private List<Lesson> lessons;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
