package com.example.springproje.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springproje.bean.Comment;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    Comment updateandmodify(Integer id,String content,Integer tid);

    List<CommentDTO> selectCommentbytalkId(Integer tid);

}