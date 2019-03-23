package com.neo.web;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.entity.excel.UserInfo;
import com.neo.mapper.UserMapper;
import com.neo.service.ExcelImportService;
import com.neo.service.StudyTaskService;
import com.neo.vo.StudyTaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/studyByExcelPath", method = RequestMethod.POST)
    public String studyByExcelPath(@RequestParam(name = "userPath")String userPath){

        excelImportService.batchStudyByUserExcel(userPath);
        return "SUCCESS";
    }

    @RequestMapping(value = "/insertLessons", method = RequestMethod.POST)
    public String insertLessons(@RequestParam(name = "lessonPath")String lessonPath){

        List<Lesson> lessons = excelImportService.getLessonsByExcelPath(lessonPath);
        studyTaskService.checkAndSaveLesson(lessons);

        return "SUCCESS";
    }

    @RequestMapping(value = "/getNoIdInfo", method = RequestMethod.POST)
    public String getNoIdInfo(@RequestParam(name = "userPath")String userPath){
        List<UserInfo> userInfoList = ExcelImportUtil.importExcel(new File(userPath),
                UserInfo.class, new ImportParams());

        List<String> idCards = userInfoList.stream().map(UserInfo::getAccount).collect(Collectors.toList());
        List<User> users = userMapper.getUserByIdCards(idCards);
        List<UserInfo> noIdUserInfo = excelImportService.getNoIdUserInfo(userInfoList, users);

        return JSONObject.toJSONString(noIdUserInfo);
    }


}
