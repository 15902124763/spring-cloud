# Spring Cloud 各个组件的使用用例
[官方文档地址](https://spring.io/projects/spring-cloud-netflix/)  
    SpringBoot版本：2.2.5.RELEASE
    Spring Cloud版本：Hoxton.SR1
## 1. Eureka
    1.1 Eureka第一印象
    Eureka是spring cloud的注册中心，可以理解成spring cloud注册和发现服务的组件，和zookeeper类似，组要是管理服务。
    其中代码在eureka目录下
    
    1.2 Eureka主要注解说明
    @EnableEurekaServer // 表示是Eureka的服务，放在启动函数中
    @EnableEurekaClient // 表示是Eureka的客户端，即我们正常启动的springboot服务，放在启动函数中
    
    1.3 Eureka服务的用例说明
    1.3.1 Eureka依赖
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
```
    
    1.3.2 Eureka的服务端配置文件
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
    
    1.3.3 Eureka的服务端启动
```java
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
   
    1.4 Eureka客户端用例说明
    1.4.1 Eureka依赖
```xml
   <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
   </dependency>
```
    
    1.4.2 Eureka客户端配置文件说明
```    
    server.port=8000
    spring.application.name=service-eureka-client
    eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
    # 开启健康检查
    eureka.client.healthcheck.enabled=true
    # eureka client发送心跳给server端的频率。如果在leaseExpirationDurationInSeconds后，server端没有收到client的心跳，则将摘除该instance
    eureka.instance.lease-renewal-interval-in-seconds=3
    # eureka server至上一次收到client的心跳之后，等待下一次心跳的超时时间，在这个时间内若没收到下一次心跳，则将移除该instance
    eureka.instance.lease-expiration-duration-in-seconds=6
```
    
    1.4.3 Eureka客户端启动代码
```java
package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
```  
    
    
## 2. Ribbon
    2.1 Ribbon的第一印象
    spring Cloud Ribbon 是一个客户端的负载均衡器，它提供对大量的HTTP和TCP客户端的访问控制。
    
    客户端负载均衡即是当浏览器向后台发出请求的时候，客户端会向 Eureka Server 读取注册到服务
    器的可用服务信息列表，然后根据设定的负载均衡策略（没有设置即用默认的），抉择出向哪台服务
    器发送请求。
    
    注意：当前项目中Ribbon是依赖于Eureka的，所以在启动Ribbon前，需启动Eureka
    其中代码在ribbon目录下
    
    2.2 主要依赖
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
```
    2.3 配置文件
```properties
server.port=6000
spring.application.name=demo-service

# eureka服务地址
eureka.client.serviceUrl.defaultZone=http://127.0.0.1:8761/eureka/
# 开启健康检查
eureka.client.healthcheck.enabled=true
# eureka client发送心跳给server端的频率。如果在leaseExpirationDurationInSeconds后，server端没有收到client的心跳，则将摘除该instance
eureka.instance.lease-renewal-interval-in-seconds=3
# eureka server至上一次收到client的心跳之后，等待下一次心跳的超时时间，在这个时间内若没收到下一次心跳，则将移除该instance
eureka.instance.lease-expiration-duration-in-seconds=6
```
    
    2.4 Ribbon代码配置
```java
package com.yarm.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 配置随机策略
    @Bean
    public IRule ribbonRandomRule() {
        return new RandomRule();
    }
}
```
    注:这里Bean注入了两种均衡策略，轮询和随机策略
    
    2.5 随机策略发送请求demo
```java
package com.yarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestRibbonController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("test")
    public String test(){
        restTemplate.getForObject("http://demo-service/demo/demo", String.class);
        return "ok";
    }
}
```
    注：http://demo-service/demo/demo表示请求的地址
    
    2.6 测试随机策略说明
    在测试Ribbon的均衡或随机策略时，如果在本地测试，可以通过分配不同端口来启动多个服务
    第一步、打包
```$xslt
    mvn clean package
```
    第二步、进入target目录下启动多个服务(如启动6000端口和6001端口)
```$xslt
    java -jar ribbon-demo.jar --server.port=6000
    java -jar ribbon-demo.jar --server.port=6001
```
    第三步、多次触发接口如：http://127.0.0.1:6000/test/test，会看到请求到不同服务如下图
    
![Ribbon 负载演示](https://img-blog.csdnimg.cn/20200311105253907.png)
    
    
       
    
    
    
    
    
    

    