package com.example.springproje.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TalkDTO {
    private Integer tid;
    private String title;
    private String description;
    private String ttype;
    private Integer creator;

    @TableField("comment_count")
    private Integer commentcount;

    @TableField("like_count")
    private Integer likecount;
    @TableField("gmt_create")
    private String gmtcreate;

    @TableField("gmt_modified")
    private String gmtmodified;

    private String introduction;
    private String name;
}
