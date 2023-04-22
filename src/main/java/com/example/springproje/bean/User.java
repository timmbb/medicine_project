package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("users")
@Getter
@Setter
public class User {
    @TableId()
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private String  gmt_create;
    private String avatar_url;
    private String email;
    private String phone;
    private String password;

    @TableField("self_introduction")
    private String selfintroduction;
}

