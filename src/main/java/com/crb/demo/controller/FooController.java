package com.crb.demo.controller;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/lock")
    public ResponseEntity<Object> getLock() throws InterruptedException {

        RLock lock = redissonClient.getLock("testLock");
        if(lock != null && lock.isLocked() && lock.isHeldByCurrentThread()){
            log.info(" unlock1 -> {}", Instant.now());
            lock.unlock();
        }
        if (lock.tryLock(0, 1000, TimeUnit.SECONDS)) {

            if(lock != null && lock.isLocked() && lock.isHeldByCurrentThread()){
                log.info(" unlock2 -> {}", Instant.now());
                lock.unlock();
            }
            return ResponseEntity.ok("lock");
        }

        return ResponseEntity.ok(null);
    }

}
