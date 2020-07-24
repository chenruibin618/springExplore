package com.crb.demo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class FooTest {

    Logger log = LoggerFactory.getLogger(FooTest.class);

    @Test
    public void mathTest() {
        assert Math.floorDiv(10, 3) == 3;
    }

    @Test
    public void testNull() {
        Map<String, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> args1 = new HashMap<>();
        Map<String, Object> args2 = new HashMap<>();
        Map<String, Object> args3 = new HashMap<>();

        Assert.assertFalse((boolean) Optional.ofNullable(map.get("args1"))
            .filter(Objects::nonNull)
            .map(args -> args.get("isFired"))
            .filter(Objects::nonNull)
            .orElse(false));

        map.put("args2", args2);
        args2.put("isFired", true);

        Assert.assertTrue((boolean) Optional.ofNullable(map.get("args2"))
            .filter(Objects::nonNull)
            .map(args -> args.get("isFired"))
            .filter(Objects::nonNull)
            .orElse(false));

        map.put("args3", args3);
        args3.put("isFired", false);

        Assert.assertFalse((boolean) Optional.ofNullable(map.get("args3"))
            .filter(Objects::nonNull)
            .map(args -> args.get("isFired"))
            .filter(Objects::nonNull)
            .orElse(false));
    }

    @Test
    public void testLongNull() {
        Long count = 0l;
        log.info("count : {}", count);
        Assert.assertNull(count);
    }

    @Test
    public void testParseJson() {
        String jsonStr = "{\"taskExecution\":12}";
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(jsonStr).getAsJsonObject();
        log.info("object -> {}", object);

        Gson gson = new Gson();

        Date obj = new Date();
        // 1. Java object to JSON file
        String jsonString = gson.toJson(obj);
        log.info("jsonString  -> {}", jsonString);
    }

    @Test
    public void uuid() {
        UUID uuid = UUID.randomUUID();
        log.info("uuid --> {}", uuid);
    }

    @Test
    public void pattern() {
        String triggerEvent = "TASK://crb_entrance_03/28aafe2c-0b75-424d-99aa-494b13131f23/actionPath/taskdisp/d3/893df3bc-ede2-4c84-89fd-4c6925fdf295/phases/1/0";
        Pattern pattern = Pattern.compile("^TASK://.*/actionPath(/.*)");

        Matcher matcher = pattern.matcher(triggerEvent);
        matcher.find();
        log.info("matcher : {}", matcher);
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        String a = "crb_entrance_04-default-cb08a45b-11ae-43b1-9a3e-c5d0be8f1fa3";
        log.info("a: {}", a);

        Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9_\\-]{1,32}$");
        log.info("lengh : {}", a.length());
        log.info("match : {}", a.matches(pattern1.pattern()) );

        log.info("pattern`s pattern : {}", pattern1.pattern());
    }

    @Test
    public void testMatch () {
        String x = "CRON://SYSTEM";
        String y = "ADHOC://DFLY";
        String z = "TASK://SYSTEM/SYSTEM/module_task/46717f17-e85b-48e7-bf78-c5ea6b22a0e4/actionPath/taskdisp/d3/0749e225-a6c1-4f80-a869-096b6ec36277/phases/1/0";

        Pattern pattern = Pattern.compile("^/project/SYSTEM/(task/[-A-Z0-9+&@#\\/%=~_|$?!:,.]*)");

        for(int i = 0; i < 100; i++) {
            log.info("x {}",  700l + System.nanoTime() % 11 * 500);
        }

    }

    @Test
    public void testRedisson () throws InterruptedException {
        Config config = new Config();
        config.useSingleServer()
            .setPassword("SogouTED2020")
            .setDatabase(8)
            .setAddress("redis://127.0.0.1:6379")
            .setConnectionMinimumIdleSize(3)
            .setConnectionPoolSize(5);

        RedissonClient client = Redisson.create(config);

        RAtomicLong myLong = client.getAtomicLong("myLong");
        myLong.set(0);

        IntStream.range(0, 100)
            .forEach(i -> {
                new Thread(()-> {
                    RLock lock = client.getLock("lock");
                    lock.lock(15, TimeUnit.SECONDS);
                    if(!lock.isLocked()) {
                        lock.lock();
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                        }
                        IntStream.range(0, 10).forEach(j -> {
                            myLong.getAndAdd(1);
                        });
                        lock.unlock();
                    }
                }).start();
            });

        TimeUnit.SECONDS.sleep(10);

        log.info("myLong -> {}", myLong.get());
    }


    @Test
    public void testMath() {
        long now = System.currentTimeMillis();
        long lastTick = Long.parseLong(Long.valueOf(now - 60 * 10 * 1000).toString());
        long heartbeatGap = Math.floorDiv((now - lastTick), 1000l);

        Assert.assertEquals(60 * 1000, heartbeatGap);
    }


}
