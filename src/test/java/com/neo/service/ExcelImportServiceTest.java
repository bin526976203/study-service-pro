package com.neo.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.neo.entity.User;
import com.neo.entity.excel.UserInfo;
import com.neo.mapper.CourseMapper;
import com.neo.mapper.LessonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author moxianbin
 * @date 2019/3/23.
 */
public class ExcelImportServiceTest {

    private Logger logger = LoggerFactory.getLogger(ExcelImportServiceTest.class);

    @InjectMocks
    private ExcelImportService excelImportService;

    @Mock
    private StudyTaskService studyTaskService;

    @Mock
    private LessonMapper lessonMapper;

    @Mock
    private CourseMapper courseMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNoIdUserInfo() throws Exception {

        String account1="account1";
        String name1="name1";
        String account2="account2";
        String name2="name2";


        List<UserInfo> userInfos = Lists.newArrayList();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setAccount(account1);
        userInfo1.setName(name1);
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setAccount(account2);
        userInfo2.setName(name2);
        userInfos.add(userInfo1);userInfos.add(userInfo2);

        List<User> users = Lists.newArrayList();
        User user = new User();
        user.setIdcard(account1);
        users.add(user);

        List<UserInfo> noIdUsers = excelImportService.getNoIdUserInfo(userInfos, users);

        logger.info("{}", JSONObject.toJSONString(noIdUsers));
    }

}