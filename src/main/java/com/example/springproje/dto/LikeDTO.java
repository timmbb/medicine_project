package com.example.springproje.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private long infoId;
    private long likeUserId;
    private Integer status;
}

