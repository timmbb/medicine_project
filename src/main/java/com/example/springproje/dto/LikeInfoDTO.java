package com.example.springproje.dto;

import lombok.Data;

@Data
public class LikeInfoDTO {
    private Integer tid;
    private Integer creator;
    private String name;
    private String title;
    private String introduction;
}
