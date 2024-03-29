package com.neo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.neo.entity.Course;
import com.neo.entity.Lesson;
import com.neo.util.DateUtils;
import com.neo.util.HttpClient;
import com.neo.util.ProxyUtils;
import com.neo.util.Utils;
import com.neo.util.proxy.ProxyItem;
import com.neo.util.proxy.ProxyList;
import com.neo.vo.reqYz.Constant;
import com.neo.vo.reqYz.ReqYzParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author moxianbin on 2019/3/5.
 */
public class RequestStudyThread implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(RequestStudyThread.class);

    private String stuid;
    private List<Course> courses;

    private final static String SUCCESS_MSG = "succeeded";

    public RequestStudyThread(String stuid, List<Course> courses) {
        this.stuid = stuid;
        this.courses = courses;
    }

    /**
     * 一个人的所有课程学习
     */

    @Override
    public void run() {
        ProxyList proxyList = ProxyUtils.getProxyList();
        courses.forEach(course -> {
            int times = course.getCourseStudyTimeHour() * 60 + course.getCourseStudyTimeMin();
            for (int i=0;i<times;i++){
                userStudyLesson(proxyList.getProxies(), course, course.getLessonId(), stuid);
            }
        });
    }

    public void userStudyLesson(List<ProxyItem> proxyItems, Course course, String lessonId, String stuid){
        String learnId = Utils.getLearnId(32);

        LocalDateTime now = LocalDateTime.now();
        int hour = course.getCourseStudyTimeHour();
        int min = course.getCourseStudyTimeMin();
        int sec = course.getCourseStudyTimeSecond();
        LocalDateTime changeNow = DateUtils.addMinutes(now, -1);
        int studyTime = 60 * 1000;
        String courseId = course.getCourseId();
        String classId = course.getClassId();

        String initUrl = "https://lrs.cpoc.cn/logsvc/op_report.ashx?cmd=2&stuid=" + stuid +
                "&courseid=" + courseId + "&callbackparam=jQuery1112029704261820561495_1551286014800&param=";

        ReqYzParam initReqYzParam = new ReqYzParam(stuid,classId,courseId,lessonId,learnId,
                Constant.STUDY_TASK_STATUS_START, 0, 0, Utils.getUnixTime(changeNow), Utils.getUnixTime(changeNow),
                Constant.STUDY_TASK_PASS_FAIL);
        //http 请求要进行ip转换
        String firstUrl = initUrl + JSONObject.toJSONString(initReqYzParam);
        String result1 = HttpClient.get(firstUrl);
        log.info("邮政学习-first,result:{},firstUrl:{}", firstUrl, result1);
        if (!result1.contains(SUCCESS_MSG)){
            log.info("邮政学习-first-fail,result:{},firstUrl:{}", firstUrl, result1);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReqYzParam succReqYzParam = new ReqYzParam(stuid,classId,courseId,lessonId,learnId,
                Constant.STUDY_TASK_STATUS_SUCCSS, studyTime, studyTime,
                Utils.getUnixTime(changeNow),
                Utils.getUnixTime(LocalDateTime.now()),
                Constant.STUDY_TASK_PASS_SUCCESS);
        //http 请求要进行ip转换
        String successUrl = initUrl + JSONObject.toJSONString(succReqYzParam);
        String result2 = HttpClient.get(successUrl);
        log.info("邮政学习-sec,result:{},successUrl:{}", successUrl, result2);
        if (!result2.contains(SUCCESS_MSG)){
            log.info("邮政学习-sec-fail,result:{},successUrl:{}", successUrl, result2);
        }
    }
}
