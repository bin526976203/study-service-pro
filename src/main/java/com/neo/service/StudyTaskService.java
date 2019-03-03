package com.neo.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.neo.entity.Course;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.mapper.CourseMapper;
import com.neo.mapper.LessonMapper;
import com.neo.mapper.UserMapper;
import com.neo.util.DateUtils;
import com.neo.util.HttpClient;
import com.neo.util.Utils;
import com.neo.vo.reqYz.Constant;
import com.neo.vo.reqYz.ReqYzParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author moxianbin on 2019/3/1.
 */
@Service
public class StudyTaskService {

    private final static Logger log = LoggerFactory.getLogger(StudyTaskService.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *jQuery1112046735020497820434_1551485970076({"errcode":-1,
     * "errmsg":"The record is succeeded added!","servertime":1551486052836,
     * "key":"","result":0})
     */
    private final static String SUCCESS_MSG = "succeeded";

    /**
     * result:1
     */
    private final static String BM_SUCCESS_MSG = "1";

    public void batchBmAndStudy(List<User> users, List<Lesson> lessons){
        //批量保存user与返回studyId 并返回空id的User
        List<User> emptyIdUsers = cleanAndUpdateUsers(users);
        //回填补充课程信息，已有课程信息
        List<Lesson> fillLesson = fillLesson(lessons);

        Map<User, Lesson> failBmMaps = batchBmReq(users, fillLesson);
        Map<User, Lesson> failStudyMaps = execTaskAndGetFailMap(users, fillLesson);

        log.info("空ID的users:{}", JSONObject.toJSONString(emptyIdUsers));
        log.info("报名失败的maps:{}", JSONObject.toJSONString(failBmMaps));
        log.info("学习失败的maps:{}", JSONObject.toJSONString(failStudyMaps));
    }

    /**
     * 批量执行报名
     * @param users
     * @param lessons
     * @return
     */
    public Map<User, Lesson> batchBmReq(List<User> users, List<Lesson> lessons) {
        Map<User, Lesson> failBmMap = Maps.newHashMap();
        users.forEach(user -> {
            lessons.forEach(lesson -> {
                boolean bmResult = requestBm(user.getStudentId(), lesson.getClassId(), lesson.getBmId());
                if (!bmResult){
                    failBmMap.put(user, lesson);
                }
            });
        });

        return failBmMap;
    }

    /**
     * 批量执行学习任务
     * @param users
     * @param lessonList
     * @return
     */
    public Map<User, Lesson> execTaskAndGetFailMap(List<User> users, List<Lesson> lessonList) {
        checkAndSaveLesson(lessonList);

        Map<User, Lesson> failStudyMap = Maps.newHashMap();
        users.forEach(user -> {

            lessonList.forEach(lesson -> {
                lesson.getCourses().forEach(course -> {
                    boolean success = requestStudy(user.getStudentId(), course.getCourseId(), course.getLessonId(),
                            course.getCourseStudyTimeHour(), course.getCourseStudyTimeMin(), course.getCourseStudyTimeSecond());
                    if (!success) {
                        failStudyMap.put(user, lesson);
                    }
                });
            });
        });
        return failStudyMap;
    }

    private boolean requestBm(String stuid, String classid, String bmId){
        //https://yzyw.cpoc.cn/CPOCV2/modclasslearn/classbm_xuanYuanBm?
        //classid=c201911381&stuid=2000455058&txzh=2019lcjl1

        String url = "https://yzyw.cpoc.cn/CPOCV2/modclasslearn/classbm_xuanYuanBm?1=1";

        url = url + "&classid=" + classid + "&stuid=" + stuid + "&bmId=" + bmId;

        String result = HttpClient.get(url);

        if (!result.contains(BM_SUCCESS_MSG)) {
            log.info("报名请求失败,url:{},result:{}", url, result);
            return false;
        }

        return true;
    }

    /**
     * 核心进行请求
     * @param stuid
     * @param courseId
     * @param lessonid
     * @param hour
     * @param min
     * @param sec
     */
    private boolean requestStudy(String stuid, String courseId, String lessonid, int hour, int min, int sec){
        String learnId = Utils.getLearnId(32);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime changeNow = DateUtils.addMinutes(DateUtils.addHours(now, -(hour+1)), -min);
        int studyTime = ( hour*3600 + min*60 + sec ) * 1000;

        String initUrl = "https://lrs.cpoc.cn/logsvc/op_report.ashx?cmd=2&stuid=" + stuid +
                "&courseid=" + courseId + "&callbackparam=jQuery1112029704261820561495_1551286014800&param=";

        ReqYzParam initReqYzParam = new ReqYzParam(stuid,courseId,lessonid,learnId,
                Constant.STUDY_TASK_STATUS_START, 0, 0, Utils.getUnixTime(changeNow), Utils.getUnixTime(changeNow),
                Constant.STUDY_TASK_PASS_FAIL);
        //http 请求要进行ip转换
        String firstUrl = initUrl + JSONObject.toJSONString(initReqYzParam);
        String result1 = HttpClient.get(firstUrl);
        if (!result1.contains(SUCCESS_MSG)){
            log.info("邮政学习-first-fail,result:{},firstUrl:{}", firstUrl, result1);
            return false;
        }

        ReqYzParam succReqYzParam = new ReqYzParam(stuid,courseId,lessonid,learnId,
                Constant.STUDY_TASK_STATUS_SUCCSS, studyTime, studyTime,
                Utils.getUnixTime(changeNow),
                Utils.getUnixTime(LocalDateTime.now()),
                Constant.STUDY_TASK_PASS_SUCCESS);
        //http 请求要进行ip转换
        String successUrl = initUrl + JSONObject.toJSONString(succReqYzParam);
        String result2 = HttpClient.get(successUrl);
        if (!result2.contains(SUCCESS_MSG)){
            log.info("邮政学习-sec-fail,result:{},successUrl:{}", successUrl, result2);
            return false;
        }

        return true;
    }

    public List<User> cleanAndUpdateUsers(List<User> users) {
        checkAndUpdateUsers(users);
        List<User> emptyIdUsers = removeAndGetEmptyIdUser(users);
        return emptyIdUsers;
    }

    public List<Lesson> fillLesson(List<Lesson> inputLessonList){
        List<Lesson> fillLesson = Lists.newArrayList();
        inputLessonList.forEach(inputLesson->{
            Lesson selectLesson = lessonMapper.selectByPrimaryKey(inputLesson.getLessonId());
            if (Objects.nonNull(selectLesson)) {
                List<Course> courses = courseMapper.selectByLessonId(selectLesson.getLessonId());
                selectLesson.setCourses(courses);
                fillLesson.add(selectLesson);
            } else {
                //数据库中不存在，只能使用传入进来的课程
                fillLesson.add(inputLesson);
            }
        });

        return fillLesson;
    }

    public void checkAndUpdateUsers(List<User> users){
        for (User user: users) {
            if (StringUtils.isEmpty(user.getIdcard())) {
                continue;
            }

            User getUser = userMapper.selectByPrimaryKey(user.getIdcard());
            if (Objects.isNull(getUser)) {
                userMapper.insertSelective(user);
            } else {
                if (Strings.isNullOrEmpty(getUser.getStudentId())) {
                    userMapper.updateByPrimaryKeySelective(user);
                } else {
                    user.setStudentId(getUser.getStudentId());
                }
            }

        }
    }

    /**
     * 删除空ID的用户并将集合返回
     * @param users
     * @return
     */
    public List<User> removeAndGetEmptyIdUser(List<User> users){
        List<User> emptyIdUsers = Lists.newArrayList();
        Iterator<User> iterable = users.iterator();
        while (iterable.hasNext()){
            User user = iterable.next();

            if (Strings.isNullOrEmpty(user.getStudentId())) {
                emptyIdUsers.add(user);
                iterable.remove();
            }
        }

        return emptyIdUsers;
    }

    public void checkAndSaveLesson(List<Lesson> lessons){
        for (Lesson lesson : lessons) {
            if (StringUtils.isEmpty(lesson.getCourseId())){
                Lesson lessonVo = lessonMapper.selectByPrimaryKey(lesson.getLessonId());
                if (Objects.isNull(lessonVo)) {
                    lessonMapper.insertSelective(lesson);
                    lesson.getCourses().forEach(course -> {
                        courseMapper.insertSelective(course);
                    });
                }
            }
        }
    }


}
