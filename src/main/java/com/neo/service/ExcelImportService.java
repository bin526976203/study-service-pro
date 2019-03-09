package com.neo.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.neo.entity.Course;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.entity.excel.CourseInfo;
import com.neo.entity.excel.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author moxianbin
 * @date 2019/3/2.
 */
@Service
public class ExcelImportService {

    private final static Logger log = LoggerFactory.getLogger(ExcelImportService.class);

    @Autowired
    private StudyTaskService studyTaskService;

    public void batchBmAndStudyByExcel(String userExcelPath, String lessonExcelPath){

        List<User> users = getUsersByExcelPath(userExcelPath);
        log.info("excelsUser:{}", JSONObject.toJSONString(users));

        List<Lesson> lessons = getLessonsByExcelPath(lessonExcelPath);
        log.info("excelLesson:{}", JSONObject.toJSONString(lessons));
        studyTaskService.batchBmAndStudy(users, lessons);
    }

    /**
     * 根据模板获取出UserInfo信息
     * @param path
     * @return
     */
    public List<User> getUsersByExcelPath(String path){
        List<UserInfo> userInfoList = ExcelImportUtil.importExcel(new File(path),
                UserInfo.class, new ImportParams());

        //去除空的user
        userInfoList = userInfoList.stream().filter(userInfo -> Objects.nonNull(userInfo.getAccount())).
                collect(Collectors.toList());

        List<User> users = Lists.newArrayList();
        userInfoList.forEach(userInfo -> {
            users.add(User.init(userInfo));
        });

        return users;
    }

    public List<Lesson> getLessonsByExcelPath(String path){
        List<CourseInfo> courseInfoList =ExcelImportUtil.importExcel(new File(path),
                CourseInfo.class, new ImportParams());
        List<Lesson> lessons = Lists.newArrayList();
        //去除空的课程
        courseInfoList = courseInfoList.stream().filter(courseInfo -> Objects.nonNull(courseInfo.getCourseId())).collect(Collectors.toList());

        Map<String, List<CourseInfo>> lessonMap = courseInfoList.stream().collect(Collectors.groupingBy(CourseInfo::getLessonId));
        lessonMap.forEach((lessonId, courseInfos) ->{
            Lesson lesson = new Lesson();
            lesson.setLessonId(lessonId);

            CourseInfo courseInfo1 = courseInfos.get(0);
            if (Objects.nonNull(courseInfo1)){
                lesson.setBmId(courseInfo1.getBmid());
                lesson.setClassId(courseInfo1.getClassId());
                lesson.setLessonName(courseInfo1.getLessonName());
            }

            List<Course> courses = Lists.newArrayList();
            courseInfos.forEach(courseInfo->{
                courses.add(Course.init(courseInfo));
            });

            lesson.setCourses(courses);
            lessons.add(lesson);
        });


        return lessons;
    }

}
