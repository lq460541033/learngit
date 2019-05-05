package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/getUser")
    public User getUser() {
    	User user=userRepository.findByUserName("bb");
    	System.out.println(user);
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return user;
    }
    
    @RequestMapping("/getUsers")
    public List<User> getUsers() {
    	List<User> users=userRepository.findAll();
    	System.out.println(users.size());
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return users;
    }

}
