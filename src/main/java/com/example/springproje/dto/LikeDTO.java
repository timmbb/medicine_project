package com.example.springproje.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private Integer infoId;
    private Integer likeUserId;
    private Integer status;
}

