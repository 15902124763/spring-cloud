package com.yarm.controller;

import com.yarm.service.FeignServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OpneController {
    @Autowired
    FeignServiceB feignServiceB;
    /**
     * @Description :对外提供服务接口
     * @Author : yarm.yang
     * @Date : 2020/3/25 14:19
     * @Return :
    */
    @RequestMapping("toA")
    public String open(){
        String result = "feign-service-a";
        String s = feignServiceB.toB();
        return result + "-->" + s;
    }
}
