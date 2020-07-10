package com.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealthController {
    @RequestMapping("/")
    public String index(){
        return "it work";
    }
}
