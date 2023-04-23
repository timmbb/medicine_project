package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("comment")
@Data
public class Comment {
    @TableId()
    private Integer cid;
    private Integer parent_id;
    private Integer commentator;
    @TableField("gmt_create")
    private String gmtcreate;

    @TableField("gmt_modified")
    private String gmtmodified;

    private String content;
}