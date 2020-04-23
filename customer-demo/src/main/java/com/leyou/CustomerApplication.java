package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient//eureka客户端
@SpringBootApplication
public class CustomerApplication {

    @LoadBalanced//负载均衡
    @Bean
    public RestTemplate restTemplate(){

        return  new RestTemplate();

    }
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
