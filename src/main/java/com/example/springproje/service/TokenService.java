package com.example.springproje.service;

import com.example.springproje.bean.User;

public interface TokenService {
    public String getToken(User user);
}
