package com.example.springproje.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer cid;
    private Integer parent_id;
    private Integer ctype;
    private Integer commentator;
    private long gmt_create;
    private long gmt_modified;
    private long like_count;
    private String content;
    private Integer comment_count;
    private String name;
}
