package com.crb.demo;

import com.crb.demo.beanTest.MyBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		MyBeanFactory myBeanFactory = context.getBean(MyBeanFactory.class);
		myBeanFactory.getMyBeanName();
	}

}
