package com.neo.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.service.ExcelImportService;
import com.neo.service.StudyTaskService;
import com.neo.vo.StudyTaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author moxianbin on 2019/3/1.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private StudyTaskService studyTaskService;

    @Autowired
    private ExcelImportService excelImportService;

    @RequestMapping(value = "/studyByExcelPath", method = RequestMethod.POST)
    public String studyByExcelPath(@RequestParam(name = "userPath")String userPath,
                                   @RequestParam(name = "lessonPath")String lessPath){

        excelImportService.batchBmAndStudyByExcel(userPath, lessPath);

        return "SUCCESS";
    }


    @RequestMapping(value = "/studyBmReq", method = RequestMethod.POST)
    public String studyBm(@RequestBody String req){

        //https://yzyw.cpoc.cn/CPOCV2/modclasslearn/classbm_xuanYuanBm?classid=c201911381&stuid=2000455058&txzh=2019lcjl1

        return "SUCCESS";
    }

    @RequestMapping(value = "/studyTaskReq", method = RequestMethod.POST)
    @ResponseBody
    public String studyTask(@RequestBody String req){
        log.info("接收到学习任务请求:{}", req);

        StudyTaskRequest request = JSONObject.parseObject(req, StudyTaskRequest.class);
        List<User> users = request.getUsers();
        List<Lesson> lessonVoList = request.getLessons();

        List<User> emptyIdUsers = studyTaskService.cleanAndUpdateUsers(users);
        Map<User, Lesson> failStudyMap = studyTaskService.execTaskAndGetFailMap(users, lessonVoList);

        if (!failStudyMap.isEmpty()){
            return "空studyIdUsers:"+JSONObject.toJSONString(emptyIdUsers)+";请求失败的用户课程Map:"+JSONObject.toJSONString(failStudyMap);
        }

        return "SUCCESS";
    }


}
