package com.example.springproje.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Collections;
import com.example.springproje.bean.Likes;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.mapper.LikeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeService {
    @Resource
    private TalkService talkService;
    @Resource
    private LikeMapper likeMapper;

    public List<LikeInfoDTO> insert(Integer talkid, Integer userid){
        Likes likes=new Likes();
        likes.setInfoId(talkid);
        likes.setLikeUserId(userid);
        likes.setStatus(1);
        likes.setCreateTime(System.currentTimeMillis());
        likeMapper.insert(likes);
        return talkService.selectlikebyUserid(userid);
    }

    public List<LikeInfoDTO> remove(Integer talkid, Integer userid) {
        QueryWrapper<Likes> likesQueryWrapper = new QueryWrapper<>();
        likesQueryWrapper.eq("info_id", talkid);
        likesQueryWrapper.eq("like_user_id", userid);
        likeMapper.delete(likesQueryWrapper);
        return talkService.selectlikebyUserid(userid);
    }
}
