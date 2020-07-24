package com.crb.demo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
            .setAddress(String.format("redis://%s:%s", host, port))
            .setPassword(password)
            .setDatabase(database)
            .setConnectionMinimumIdleSize(2)
            .setConnectionPoolSize(5);
        return Redisson.create(config);
    }

}
