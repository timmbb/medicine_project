package com.example.springproje.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer cid;
    private Integer parent_id;
    private Integer commentator;
    @TableField("gmt_create")
    private String gmtcreate;

    @TableField("gmt_modified")
    private String gmtmodified;

    private String content;
    private String name;
}
