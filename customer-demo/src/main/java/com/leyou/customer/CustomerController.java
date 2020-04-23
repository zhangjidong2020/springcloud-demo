package com.leyou.customer;

import com.leyou.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;//eureka客户端，获取服务实例信息

//    @GetMapping("/{i}")//rest get请求
//    public User queryUserById(@PathVariable("i") Integer id){
//
//       return restTemplate.getForObject("http://localhost:8081/user/"+id,User.class);
//
//    }

//    @GetMapping("/{i}")//rest get请求
//    public User queryUserById(@PathVariable("i") Integer id){
//        //根据服务名称获取服务实例
//        List<ServiceInstance> instances = discoveryClient.getInstances("userService");
//
//        ServiceInstance serviceInstance = instances.get(0);
//        //获取ip地址，和端口（乘客获取司机信息）
//        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;
//        //http://127.0.0.1:8081/user/id
//
//        return restTemplate.getForObject(url,User.class);
//        //return restTemplate.getForObject("http://localhost:8081/user/"+id,User.class);
//    }

    @GetMapping("/{i}")//rest get请求
    public User queryUserById(@PathVariable("i") Integer id){
        //userService服务名称（ip:端口号）
        String url="http://userService/user/"+id;
        //userService 变成ip+端口
        //http://127.0.0.1:8081/user/1
        return restTemplate.getForObject(url,User.class);

    }


}
