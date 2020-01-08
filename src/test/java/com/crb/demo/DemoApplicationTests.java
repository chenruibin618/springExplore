package com.crb.demo;

import domain.Course;
import domain.Student;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Student student = new Student();
		String studentName = "Joe";
		student.setName(studentName);

		Course course = new Course();

		PropertyUtils.setMappedProperty(course, "enrolledStudent(ST-1)", student);

		Assert.assertEquals("Joe", PropertyUtils.getNestedProperty(
				course, "enrolledStudent(ST-1).name"));
	}

}
