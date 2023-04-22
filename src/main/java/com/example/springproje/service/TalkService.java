package com.example.springproje.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;
import com.example.springproje.mapper.TalkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


@Service
public class TalkService {
    @Resource
    private TalkMapper talkMapper;

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
    public Integer update(Integer creator,String title, String description,String ttype, String images,String introduction){
        QueryWrapper<Talk> talkQueryWrapper=new QueryWrapper<>();
        talkQueryWrapper.eq("creator",creator);

        Talk talk=talkMapper.selectOne(talkQueryWrapper);
//        talk
        return talkMapper.updateandmodify(creator, title, description,ttype, images,introduction);
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

    public List<TalkDTO> detailtalk(Integer tid){
        return talkMapper.selectdetailtalk(tid);
    }

    public List<TalkDTO> selectalltalk(){return talkMapper.selectalltalk();}
}
