package com.crb.demo;

import groovy.util.logging.Slf4j;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import org.junit.Test;

import java.util.UUID;

public class TestFoo {

    @Test
    public void testUUID() throws UnknownHostException {
//        UUID uuid = UUID.fromString("f150e7eb197240e2bc9b268dd142c5b4");
//        System.out.println("f150e7eb197240e2bc9b268dd142c5b4".length());
//        System.out.println(uuid);

//        String s = InetAddress.getLocalHost().getHostName().replaceAll("[^a-zA-Z0-9\\-]+","-");
        InetAddress ia = InetAddress.getLocalHost();
        String s = InetAddress.getLocalHost().getCanonicalHostName();
//        s = ".13cb7148/fd96-437b-ac4f-fdb24a1dd4b6";
//        s.replaceAll("[^a-zA-Z0-9\\-]+","-");
        System.out.println(s);
//        System.out.println(y);
    }

}
