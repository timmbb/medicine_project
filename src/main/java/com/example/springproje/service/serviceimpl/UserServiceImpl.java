package com.example.springproje.service.serviceimpl;

import com.example.springproje.bean.User;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User logIn(String name, String password) {
        return userMapper.login(name, password);
    }

    public User register(String name,String password,String password_r){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        User u=new User();
        u.setName(name);
        u.setPassword(password);
        u.setGmtcreate(df.format(date));
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
