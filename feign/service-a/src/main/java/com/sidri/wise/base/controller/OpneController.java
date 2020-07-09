package com.sidri.wise.base.controller;

import com.sidri.wise.base.admin.api.OpenApi;
import com.sidri.wise.base.admin.bo.usercenter.UserInfoBo;
import com.sidri.wise.base.admin.bo.usercenter.UserOrgInfoBo;
import com.sidri.wise.base.admin.bo.usercenter.UserPageInfoBo;
import com.sidri.wise.base.service.FeignServiceB;
import com.sidri.wise.base.service.MyService;
import com.sidri.wise.common.beans.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class OpneController {
    @Autowired
    FeignServiceB feignServiceB;
    @Autowired
    MyService myService;
    @Autowired
    OpenApi openApi;

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

    @RequestMapping("test")
    public Result<UserPageInfoBo> test(){
        Result<List<UserOrgInfoBo>> result = openApi.getUserOrgInfo("COM_SIDRI_PIPE_2020_04");
        Result<UserPageInfoBo> list = openApi.getUserPageInfo("COM_SIDRI_PIPE_2020_04", "", "", 1, 1);
        UserPageInfoBo model = list.getModel();
        List<UserInfoBo> pageInfoList = model.getPageInfoList();

        System.out.println(list);
        return list;
    }

    @RequestMapping("test1")
    public Result<String> test1(){
        Result<String> result = new Result<>();
        return result;
    }
}
