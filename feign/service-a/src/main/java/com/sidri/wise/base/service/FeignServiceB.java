package com.sidri.wise.base.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :调用服务feign-service-c
 * @Author : yarm.yang
 * @Date : 2020/3/25 14:10
*/
@FeignClient("feign-service-b")
public interface FeignServiceB {
    @RequestMapping("toB")
    String toB();
}
