package com.example.springproje.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Likes;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    private TalkServiceImpl talkService;
    @Resource
    private LikeMapper likeMapper;

    public List<LikeInfoDTO> insert(Integer talkid, Integer userid){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Likes likes=new Likes();
        likes.setInfoId(talkid);
        likes.setLikeUserId(userid);
        likes.setStatus(1);
        likes.setCreateTime(df.format(date));
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
