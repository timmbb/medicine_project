package com.example.springproje.dto;

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
    private Integer comment_count;
    private Integer like_count;
    private long gmt_create;
    private long gmt_modified;
    private String introduction;
    private String name;
}
