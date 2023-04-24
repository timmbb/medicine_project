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

    @Override
    public String toString(){
        return "{" +
                "'tid'" +":'"+ tid +"',"+
                "'title'" + ":'"+title + "',"+
                "'description'" + ":'"+description + "',"+
                "'ttype'" + ":'"+ttype + "',"+
                "'creator'" + ":'"+creator + "',"+
                "'commentcount'" + ":'"+commentcount + "',"+
                "'likecount'" + ":'"+likecount + "',"+
                "'gmtcreate'" + ":'"+gmtcreate + "',"+
                "'gmtmodified'" + ":'"+gmtmodified + "',"+
                "'introduction'" + ":'"+introduction + "',"+
                "'name'" + ":'"+name +"'"+
                '}';
    }
}
