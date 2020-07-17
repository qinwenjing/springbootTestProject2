/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/springBootTest")
public class HelloController {
    @Value("${zone.name}")
    private String zoneName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET})
    public String hello() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        log.info("/hello, host2:" + instance.getHost() + ",  serviceId:" + instance.getServiceId());
        return "hello world, zonename=" + zoneName;
    }
}
