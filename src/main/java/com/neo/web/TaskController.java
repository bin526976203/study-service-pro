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


}
