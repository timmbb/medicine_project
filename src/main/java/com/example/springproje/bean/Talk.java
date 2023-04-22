package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Setter;
import lombok.Getter;

@TableName("talk")
@Getter
@Setter
public class Talk {
    @TableId()
    private Integer tid;
    private String title;
    private String description;
    private String images;
    private String ttype;
    private Integer status;
    private Integer permission;
    private Integer creator;

    @TableField("comment_count")
    private Integer commentcount;

    @TableField("like_count")
    private Integer likecount;
    private long gmt_create;
    private long gmt_modified;
    private String introduction;
}