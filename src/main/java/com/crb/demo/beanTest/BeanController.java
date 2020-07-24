package com.crb.demo.beanTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean")
@Slf4j
public class BeanController {

    @GetMapping("")
    public ResponseEntity<Object> getLock() throws InterruptedException {
        return ResponseEntity.ok(666);
    }

}
