package com.example.springproje.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.bean.Comment;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CommentDTO;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.CommentMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.CommentService;
import com.example.springproje.service.TalkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TalkService talkService;

    @ResponseBody
    @RequestMapping(value = "/add_comment",method = RequestMethod.POST)
    public Object add_comment(@CurrentUserId Integer id, String content,Integer tid)
    {
        Comment comment=commentService.insert(id,content,tid);
        talkService.updatecommentcount(tid);
        return ResultDTO.okOf(commentMapper.selectCommentbytalkId(tid));
//        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/update_comment",method = RequestMethod.POST)
    public Object update_comment(@CurrentUserId Integer id, String content,Integer tid)
    {
        User user = userMapper.selectById(id);
        return  ResultDTO.okOf(commentService.update(id,content,tid));
    }
/*
    @ResponseBody
    @RequestMapping(value = "/delete_comment",method = RequestMethod.GET)
    public Object delete_comment(@CurrentUserId Integer id,Integer cid)
    {
        Comment comment=commentMapper.selectById(cid);
        commentService.delete(comment);
        QueryWrapper<Comment> commentQueryWrapper=new QueryWrapper<>();
        commentQueryWrapper.eq("commentator",id);
        return ResultDTO.okOf(commentMapper.selectList(commentQueryWrapper));
    }*/

    /**
     * 查询某个话题的所有评论
     * @param tid
     * @return
     */
    @GetMapping(value = "/commentoftalk")
    @ResponseBody
    public ResultDTO selectcommentbytalk(Integer tid)
    {
        List<CommentDTO> comments= commentService.selectCommentbytalkId(tid);
        return ResultDTO.okOf(comments);
    }

    @GetMapping(value = "/commentofuser")
    @ResponseBody
    public ResultDTO selectcommentbyuser(@CurrentUserId Integer id){
        QueryWrapper<Comment> commentQueryWrapper=new QueryWrapper<>();
        commentQueryWrapper.eq("commentator",id);
        List<Comment> commentList=commentMapper.selectList(commentQueryWrapper);
        return ResultDTO.okOf(commentList);
    }
}
