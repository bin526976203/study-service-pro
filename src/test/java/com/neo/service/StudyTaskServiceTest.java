package com.neo.service;

import com.neo.service.StudyTaskService;
import com.neo.util.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * @author moxianbin on 2019/3/1.
 */
public class StudyTaskServiceTest {

    @InjectMocks
    private StudyTaskService studyTaskService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLearnId() throws Exception {

        int length = 32;
        String learnId = Utils.getLearnId(length);
        Assert.assertTrue(learnId.length() == length);

    }

}