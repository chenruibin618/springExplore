package com.crb.demo;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//@EnableJms
//@Configuration
@Slf4j
public class JMSConfig {

    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("failover:(tcp://test.dfly.sogou:61616)");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setTrustAllPackages(true);
        return connectionFactory;
    }

    public PlatformTransactionManager transactionManager() {
        JmsTransactionManager transactionManager = new JmsTransactionManager();
        transactionManager.setConnectionFactory(connectionFactory());
        return transactionManager;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configure) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configure.configure(factory, connectionFactory());
        factory.setTransactionManager(transactionManager());
        factory.setPubSubDomain(true);
//        factory.setConcurrency("3-10");
        factory.setConnectionFactory(connectionFactory());
        factory.setErrorHandler(t -> log.error("Queue listener factory throw an exception ", t));
        return factory;
    }

}

