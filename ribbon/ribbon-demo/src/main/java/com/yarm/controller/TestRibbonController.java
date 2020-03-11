package com.yarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestRibbonController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("test")
    public String test(){
        restTemplate.getForObject("http://demo-service/demo/demo", String.class);
        return "ok";
    }
}
