package com.sidri.wise.base.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :调用服务feign-service-b
 * @Author : yarm.yang
 * @Date : 2020/3/25 14:10
*/
@FeignClient("feign-service-c")
public interface FeignServiceC {
    @RequestMapping("toC")
    String toC();
}
