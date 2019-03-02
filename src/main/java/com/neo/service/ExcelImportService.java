package com.neo.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.entity.excel.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author moxianbin
 * @date 2019/3/2.
 */
@Service
public class ExcelImportService {

    private final static Logger log = LoggerFactory.getLogger(ExcelImportService.class);

    @Autowired
    private StudyTaskService studyTaskService;

    public void batchBmAndStudyByExcel(List<User> users, List<Lesson> lessons){

    }

    /**
     * 根据模板获取出UserInfo信息
     * @param path
     * @return
     */
    public List<UserInfo> getUserInfo(String path){
        List<UserInfo> userInfos = ExcelImportUtil.importExcel(new File(path),
                UserInfo.class, new ImportParams());

        return userInfos;
    }

}
