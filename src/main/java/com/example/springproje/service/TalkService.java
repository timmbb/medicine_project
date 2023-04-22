package com.example.springproje.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.*;
import com.example.springproje.mapper.CommentMapper;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.TalkMapper;
import com.example.springproje.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


@Service
public class TalkService {
    @Resource
    private TalkMapper talkMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private LikeMapper likeMapper;

    public Talk insert(Integer id,String title, String description,String ttype, String images,String introduction){
        Talk t=new Talk();
        t.setTitle(title);
        t.setDescription(description);
        t.setTtype(ttype);
        t.setImages(images);
        t.setIntroduction(introduction);
        t.setCreator(id);
        t.setGmt_create(System.currentTimeMillis());
        talkMapper.insert(t);
        return t;
    }
    public List<TalkDTO> update(Integer id,Integer tid,String title, String description,String ttype, String images,String introduction){
        Talk talk=talkMapper.selectById(tid);
        talkMapper.updateandmodify(tid, title, description,ttype, images,introduction);
        return talkMapper.selecttalkbyuser(id);
    }

    public boolean delete(Talk talk){
        talkMapper.deleteById(talk.getTid());
        return true;
    }

    public List<Talk> Orderbylike(){
        return talkMapper.Orderbylike();
    }

    public List<Talk> Orderbycomment(){
        return talkMapper.Orderbycomment();
    }

    public List<TalkDTO> Selectbyword(String typeword,String descripword){
        return talkMapper.Selectbyword(typeword, descripword);
    }

    public List<CollectionDTO> selectCollectiontalkbyUserid(Integer id){
        return talkMapper.selectCollectiontalkbyUserid(id);
    }

    public List<LikeInfoDTO> selectlikebyUserid(Integer id){
        return talkMapper.selectlikebyUserid(id);
    }

    public TalkDTO detailtalk(Integer tid){
        return talkMapper.selectdetailtalk(tid);
    }

    public List<TalkDTO> selectalltalk(){return talkMapper.selectalltalk();}

    public TalkDTO updatelikecount(Integer tid){
        Talk talk=talkMapper.selectById(tid);
        talk.setLikecount(likeMapper.CountlikebytalkId(tid));
        talkMapper.updateById(talk);
        return talkMapper.selectdetailtalk(tid);
    }

    /*
    public TalkDTO updatelikecount(Integer tid){
        Talk talk=talkMapper.selectById(tid);
        List<LikeCountDTO> likeCountDTOS=redisUtils.getLikedCountFromRedis();
        for (LikeCountDTO likeCountDTO : likeCountDTOS) {
            if (tid == likeCountDTO.getInfoId()) {
                talk.setLikecount(likeCountDTO.getValue());
                talkMapper.updateById(talk);
                return talkMapper.selectdetailtalk(tid);
            }
        }
        return talkMapper.selectdetailtalk(tid);
    }
*/
    public TalkDTO updatecommentcount(Integer tid){
        Talk talk=talkMapper.selectById(tid);
        List<CommentDTO> commentDTOS=commentMapper.selectCommentbytalkId(tid);
        talk.setCommentcount(commentDTOS.size());
        talkMapper.updateById(talk);
        return talkMapper.selectdetailtalk(tid);
    }

}
