package com.crb.demo;


import javax.jms.JMSException;
import javax.jms.TopicConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TestTopicListener {

//    @JmsListener(id= "testId", destination = "test", subscription = "test", containerFactory = "topicListenerFactory")
    @Transactional
    public void handleTaskNotification(ActiveMQTextMessage event) {
        log.info("handleTaskNotification-------------");
    }

    public void test () throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        TopicConnection connection = connectionFactory.createTopicConnection();
    }
}
