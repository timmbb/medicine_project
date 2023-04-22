package com.example.springproje.service;

import com.example.springproje.bean.User;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User logIn(String name, String password) {
        return userMapper.login(name, password);
    }

    public User register(String name,String password,String password_r){
//        String token= UUID.randomUUID().toString();
        User u=new User();
        u.setName(name);
        u.setPassword(password);
        u.setGmt_create(String.valueOf(System.currentTimeMillis()));
        userMapper.insert(u);
        return u;
    }
    public User insertdetail(Integer id,String name,String email,String phone,String self_introduction){
        User user=userMapper.selectById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSelfintroduction(self_introduction);
        userMapper.updateById(user);
        return user;
    }
}
