package com.example.springproje.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("likes")
@Data
public class Likes {
    @TableId()
    private Integer lid;

    private Integer infoId;

    @TableField("create_time")
    private String createTime;

    private Integer likeUserId;

    private Integer status;
}
