package com.example.springproje.service;

import com.example.springproje.bean.User;

public interface UserService {
    public User logIn(String name, String password);

    public User register(String name,String password,String password_r);

    public User insertdetail(Integer id,String name,String email,String phone,String self_introduction);
}
