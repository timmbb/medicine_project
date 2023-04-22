package com.example.springproje.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springproje.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    User login(String name,String password);

}