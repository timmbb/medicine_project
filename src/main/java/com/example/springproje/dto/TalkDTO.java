package com.example.springproje.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TalkDTO {
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
    private String name;
}
