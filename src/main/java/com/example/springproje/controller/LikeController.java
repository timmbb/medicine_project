package com.example.springproje.controller;

import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.service.LikeService;
import com.example.springproje.service.TalkService;
import com.example.springproje.service.serviceimpl.LikeServiceImpl;
import com.example.springproje.service.serviceimpl.TalkServiceImpl;
import com.example.springproje.utils.RedisUtils;
import org.springframework.stereotype.Controller;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    private LikeService likeService;

    @DeleteMapping("/remove_like")
    @ResponseBody
    public Object removeLike(@CurrentUserId Integer id,Integer tid) {
//        redisUtils.unLikes(tid,id);
        likeService.remove(tid,id);
        return ResultDTO.okOf(talkService.updatelikecount(tid));
    }

    @PostMapping("/insert_like")
    @ResponseBody
    public Object insertLike(@CurrentUserId Integer id, Integer tid) {
//        redisUtils.likes(tid,id);
        likeService.insert(tid,id);
        return ResultDTO.okOf(talkService.updatelikecount(tid));
    }

    @GetMapping(value = "/selectlikebyuser")
    @ResponseBody
    public ResultDTO selectlikebyuser(@CurrentUserId Integer id){
        return ResultDTO.okOf(talkService.selectlikebyUserid(id));
    }

}
