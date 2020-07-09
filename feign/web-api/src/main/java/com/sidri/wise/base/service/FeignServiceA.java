package com.sidri.wise.base.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :调用服务feign-service-a
 * @Author : yarm.yang
 * @Date : 2020/3/25 14:15
*/
@FeignClient("feign-service-a")
public interface FeignServiceA {
    @RequestMapping("toA")
    String  toA();
}
