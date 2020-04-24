package com.leyou.customer;

import com.leyou.client.UserClient;
import com.leyou.pojo.User;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("customer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

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

//    @GetMapping("/{i}")//rest get请求
//    public User queryUserById(@PathVariable("i") Integer id){
//        //userService服务名称（ip:端口号）
//        String url="http://userService/user/"+id;
//        //userService 变成ip+端口
//        //http://127.0.0.1:8081/user/1
//        return restTemplate.getForObject(url,User.class);
//
//    }


    //day0301
    //Hystrix默认1秒
//    @GetMapping("/{i}")//rest get请求
//    @HystrixCommand(fallbackMethod = "queryByIdFallback")
//    public String queryUserById(@PathVariable("i") Integer id){
//        //userService服务名称（ip:端口号）
//        String url="http://userService/user/"+id;
//        //userService 变成ip+端口
//        //http://127.0.0.1:8081/user/1
//        String s=restTemplate.getForObject(url,String.class);
//        return s;
//
//    }

//    public String queryByIdFallback(Integer id){
//        return "人数太多，请稍后再试";
//
//    }


    //day0302
//    @GetMapping("/{i}")//rest get请求
//    //设置超时时间
//    @HystrixCommand(commandProperties = {
//      @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
//    public String queryUserById(@PathVariable("i") Integer id){
//        //userService服务名称（ip:端口号）
//        String url="http://userService/user/"+id;
//        //userService 变成ip+端口
//        //http://127.0.0.1:8081/user/1
//        String s=restTemplate.getForObject(url,String.class);
//        return s;
//
//    }


    //day0303
//    @GetMapping("/{i}")//rest get请求
//    //设置超时时间
//    @HystrixCommand
//    public String queryUserById(@PathVariable("i") Integer id){
//        //userService服务名称（ip:端口号）
//        String url="http://userService/user/"+id;
//        //userService 变成ip+端口
//        //http://127.0.0.1:8081/user/1
//        String s=restTemplate.getForObject(url,String.class);
//        return s;
//
//    }


    //服务降级
    //http://localhost:8088/customer/1
    //http://localhost:8088/customer/2
    //day0304
//    @GetMapping("/{i}")//rest get请求
//    //设置超时时间
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//意思是至少有10个请求才进行errorThresholdPercentage错误百分比计算
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//半开试探休眠时间，默认值5000ms。当熔断器开启一段时间之后比如10000ms，会尝试放过去一部分流量进行试探，确定依赖服务是否恢复。
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//设定错误百分比，默认值50%
//    })
//    public String  queryUserById(@PathVariable("i") Long id){
//        if(id % 2==0){
//            throw  new RuntimeException("");
//
//        }
//        //userService服务名称（ip:端口号）
//        String url="http://userService/user/"+id;
//        //userService 变成ip+端口
//        //http://127.0.0.1:8081/user/1
//        String s=restTemplate.getForObject(url,String.class);
//        return s;
//
//    }
//
//    //默认，每个方法不能确保有参数
//    public String defaultFallback(){
//
//        return "人数太多，请稍后再试";
//    }




    @GetMapping("/{i}")//rest get请求
    public User queryUserById(@PathVariable("i") Integer id){
        return userClient.queryById(id);

    }
}
