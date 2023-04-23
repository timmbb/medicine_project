package com.example.springproje.service;

import com.example.springproje.bean.Comment;
import com.example.springproje.dto.CommentDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface CommentService {
    public Comment insert(Integer id, String content, Integer tid);

    public void delete(Comment comment);

    public CommentDTO update(Integer id, String content, Integer tid);

    public List<CommentDTO> selectCommentbytalkId(Integer tid);

}
