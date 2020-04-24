package com.leyou.client;

import com.leyou.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userService",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("user/{m}")
    User queryById(@PathVariable("m") Integer id);
    //发出http请求
    //http://userService/user/1
    //http://127.0.0.1:8082/user/1
}
