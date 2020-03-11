# Spring Cloud 各个组件的使用用例
    [官方文档地址](https://spring.io/projects/spring-cloud-netflix)
### 1. Eureka
    1.1 Eureka第一印象
    Eureka是spring cloud的注册中心，可以理解成spring cloud注册和发现服务的组件，和zookeeper类似，组要是管理服务。
    
    1.2 Eureka主要注解说明
    @EnableEurekaServer // 表示是Eureka的服务，放在启动函数中
    @EnableEurekaClient // 表示是Eureka的客户端，即我们正常启动的springboot服务，放在启动函数中
    
    1.3.1 Eureka的服务端配置文件
    server.port=8761
    eureka.instance.hostname=localhost
    # 当前服务不用向注册中心注册
    eureka.client.register-with-eureka=false
    #表示不去检索其他的服务，因为服务注册中心本身的职责就是维护服务实例，它也不需要去检索其他服务
    eureka.client.fetch-registry=false
    eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
    # 是否开启自我保护模式，默认开启true
    eureka.server.enable-self-preservation=true
    # eureka server清理无效节点的时间间隔，默认60000毫秒，即60秒
    eureka.server.eviction-interval-timer-in-ms=60000
    
    1.3.2Eureka的服务端启动
    ```
    package com.eureka;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
    
    @SpringBootApplication
    @EnableEurekaServer
    public class EurekaServerApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(EurekaServerApplication.class, args);
        }
    
    }
    ```   
    