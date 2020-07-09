package com.sidri.wise.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OpneController {
    /**
     * @Description :对外提供服务接口
     * @Author : yarm.yang
     * @Date : 2020/3/25 14:19
     * @Return :
    */
    @RequestMapping("toC")
    public String open(){
        String result = "feign-service-c";
        return result;
    }
}
