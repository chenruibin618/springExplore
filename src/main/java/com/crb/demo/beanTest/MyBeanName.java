package com.crb.demo.beanTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

@Slf4j
public class MyBeanName implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        log.info("setBeanName -> {}", name);
    }

}
