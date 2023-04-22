package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("likes")
@Data
public class Likes {
    @TableId()
    private Integer lid;

    private Integer infoId;

    private long createTime;

    private Integer likeUserId;

    private long updateTime;

    private Integer status;
}
