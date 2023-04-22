package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
@TableName("comment")
@Data
public class Comment {
    @TableId()
    private Integer cid;
    private Integer parent_id;
    private Integer ctype;
    private Integer commentator;
    private long gmt_create;
    private long gmt_modified;
    private long like_count;
    private String content;
    private Integer comment_count;
}