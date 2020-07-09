package com.sidri.wise.base.controller;

import com.sidri.wise.base.service.FeignServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ConsumerController {

    @Autowired
    FeignServiceA feignServiceA;

    @RequestMapping("getLink")
    public String get(){
        String result = "feign-web-api";
        // 调用服务feign-service-a
        String s = feignServiceA.toA();
        return result + "-->" + s;
    }
}
