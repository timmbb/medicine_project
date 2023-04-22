package com.example.springproje.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeCountDTO implements Serializable {

    private long infoId;
    private Integer value;

    public LikeCountDTO(long infoId, Integer value) {
        this.infoId = infoId;
        this.value = value;
    }
}
