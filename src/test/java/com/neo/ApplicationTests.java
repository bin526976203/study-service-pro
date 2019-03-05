package com.neo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.neo.entity.Course;
import com.neo.entity.Lesson;
import com.neo.entity.User;
import com.neo.entity.excel.UserInfo;
import com.neo.mapper.CourseMapper;
import com.neo.service.ExcelImportService;
import com.neo.service.StudyTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private final static Logger log = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private ExcelImportService excelImportService;

	@Autowired
	private StudyTaskService studyTaskService;

	@Autowired
	private CourseMapper courseMapper;

	@Test
	public void contextLoads() {
		System.out.println("hello world");
	}


	@Test
	public void test2(){
		List<UserInfo> userInfos = ExcelImportUtil.importExcel(new File("D:/g工作/study-service-pro-master/src/main/resources/3.5听课(1).xlsx"),
				UserInfo.class, new ImportParams());

		log.info("{}", JSONObject.toJSONString(userInfos));

		List<User> users = Lists.newArrayList();
		userInfos.forEach(userInfo -> {
			User user = User.init(userInfo);
			users.add(user);
		});

		List<Lesson> lessonVos = Lists.newArrayList();
		Lesson lesson = new Lesson();
		lesson.setLessonId("8a8a834067159f62016716591ff101ec");
		lesson.setBmId("2019lcjl1");
		lesson.setClassId("c201911381");
		lesson.setLessonName("2019年中国邮政储蓄银行理财产品销售从业人员资格第一期考试远程培训");
		List<Course> courses = Lists.newArrayList();
		courses.add(new Course("159dea8fd0154238bafefb3c3ba24efb", lesson.getLessonId(), 0, 16, 0));
		courses.add(new Course("b07db79c91e44f2ebe65c61de91e0a94", lesson.getLessonId(), 0, 17, 0));
		courses.add(new Course("32ef3849c6c6437b9f106f834f5c636e", lesson.getLessonId(), 0, 17, 0));
		courses.add(new Course("4a0b083455b44da08104686aba4e8fb3", lesson.getLessonId(), 0, 22, 0));
		courses.add(new Course("6f6fafe6dfdf498b95567c8ac9379122", lesson.getLessonId(), 0, 22, 0));
		courses.add(new Course("812df88f3d2e407fa156e011cccf2850", lesson.getLessonId(), 0, 16, 0));
		courses.add(new Course("77703d3582724698be7077249bf95871", lesson.getLessonId(), 0, 19, 0));
		courses.add(new Course("8ad30b284e6248e0866425feb7dd18cd", lesson.getLessonId(), 0, 20, 0));
		lesson.setCourses(courses);
		lessonVos.add(lesson);

		studyTaskService.batchBmAndStudy(users, lessonVos);

		try {
			Thread.sleep(3600*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectCourseByLessonIdTest(){
		List<Course> courses = courseMapper.selectByLessonId("8a8a834067159f62016716591ff101ec");
		log.info("{}", JSONObject.toJSONString(courses));
	}
}
