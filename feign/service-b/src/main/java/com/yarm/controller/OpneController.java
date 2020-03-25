package com.yarm.controller;

import com.yarm.service.FeignServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OpneController {

    @Autowired
    FeignServiceC feignServiceC;

    /**
     * @Description :对外提供服务接口
     * @Author : yarm.yang
     * @Date : 2020/3/25 14:19
     * @Return :
    */
    @RequestMapping("toB")
    public String open(){
        String result = "feign-service-b";
        String s = feignServiceC.toC();
        return result + "-->" + s;
    }
}
