package com.example.springproje.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collection")
public class Collections {
    @TableId()
    private Integer id;
    private Integer postId;

    private Integer userId;
}
