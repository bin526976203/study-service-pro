package com.neo.web;

import com.neo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUserCount")
    public long getUserCount(){
        return userMapper.getUserCount();
    }
    
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}