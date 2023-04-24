package com.example.springproje.service.serviceimpl;

import com.example.springproje.bean.Comment;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CommentDTO;
import com.example.springproje.mapper.CommentMapper;
import com.example.springproje.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    public Comment insert(Integer id,String content,Integer tid){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Comment comment=new Comment();
        comment.setContent(content);
        comment.setCommentator(id);
        comment.setParent_id(tid);
        comment.setGmtcreate(df.format(date));
        comment.setGmtmodified(df.format(date));
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
