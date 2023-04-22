package com.example.springproje.service;

import com.example.springproje.bean.Comment;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CommentDTO;
import com.example.springproje.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public Comment insert(Integer id,String content,Integer tid){
        Comment comment=new Comment();
        comment.setContent(content);
        comment.setCommentator(id);
        comment.setParent_id(tid);
        comment.setGmt_create(System.currentTimeMillis());
        commentMapper.insert(comment);
        return comment;
    }

    public void delete(Comment comment){
        commentMapper.deleteById(comment.getCid());
    }

    public CommentDTO update(Integer id,String content,Integer tid){
        return commentMapper.updateandmodify(id, content,tid);
    }

    public List<CommentDTO> selectCommentbytalkId(Integer tid){
        return commentMapper.selectCommentbytalkId(tid);
    }

}
