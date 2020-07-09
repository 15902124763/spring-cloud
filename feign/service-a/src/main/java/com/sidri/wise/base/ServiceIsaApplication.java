package com.sidri.wise.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.sidri.wise.base.admin.api","com.sidri.wise.base.service"})
@EnableEurekaClient
public class ServiceIsaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceIsaApplication.class, args);
    }
}
