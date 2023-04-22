package com.example.springproje.controller;

import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.service.TalkService;
import com.example.springproje.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Likes;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LikeController {
    @Resource
    private LikeMapper likeMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TalkService talkService;

    @DeleteMapping("/remove_like")
    @ResponseBody
    public Object removeLike(@CurrentUserId Integer id,Integer tid) {
        redisUtils.unLikes(tid,id);
        return ResultDTO.okOf(talkService.updatelikecount(tid));
    }

    @PostMapping("/insert_like")
    @ResponseBody
    public Object insertLike(@CurrentUserId Integer id, Integer tid) {
        redisUtils.likes(tid,id);
        return ResultDTO.okOf(talkService.updatelikecount(tid));
    }

    @GetMapping(value = "/selectlikebyuser")
    @ResponseBody
    public ResultDTO selectlikebyuser(@CurrentUserId Integer id){
        return ResultDTO.okOf(talkService.selectlikebyUserid(id));
    }

}
