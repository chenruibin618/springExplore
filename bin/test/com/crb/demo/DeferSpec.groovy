package com.crb.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.test.context.ActiveProfiles
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import spock.lang.Ignore
import spock.lang.Specification

import java.util.concurrent.TimeUnit

@SpringBootTest
@ActiveProfiles("ut")
@Ignore
class DeferSpec extends Specification{

    Logger log = new LoggerFactory().getLogger(DeferSpec.class)

    Scheduler scheduler = Schedulers.parallel();

    def "test defer" () {
        expect:
        Mono.defer({
            TimeUnit.SECONDS.sleep(30)
            log.info("end")
            Mono.empty()
        }).subscribe()
        log.info("start")
        TimeUnit.SECONDS.sleep(30)
    }

    @Scheduled(initialDelay = 10l, fixedRate = 10l)
    def "schedule" () {
        def now = Math.floorDiv(System.currentTimeMillis() , 1000)
//        log.info("schedule ${now}")
        Mono.defer({
            TimeUnit.SECONDS.sleep(5)
            log.info("defer ${now}")
            Mono.empty()
        })
        .subscribeOn(scheduler)
        .subscribe()
    }

}
