package com.example.springproje.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springproje.bean.Likes;
import com.example.springproje.dto.CommentDTO;
import com.example.springproje.dto.LikeInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper extends BaseMapper<Likes> {
    Integer CountlikebytalkId(Integer tid);

}
