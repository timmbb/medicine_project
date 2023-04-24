package com.example.springproje.service.serviceimpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.*;
import com.example.springproje.mapper.CommentMapper;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.TalkMapper;
import com.example.springproje.service.PythonService;
import com.example.springproje.service.TalkService;
import com.example.springproje.utils.RedisUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TalkServiceImpl implements TalkService {
    @Resource
    private TalkMapper talkMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private LikeMapper likeMapper;
    @Resource
    private PythonService pythonService;

    public Talk insert(Integer id,String title, String description,String ttype,String introduction){
        Talk t=new Talk();
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        t.setTitle(title);
        t.setDescription(description);
        t.setTtype(ttype);
        t.setIntroduction(introduction);
        t.setCreator(id);
        t.setGmtcreate(df.format(date));
        t.setGmtmodified(df.format(date));
        talkMapper.insert(t);
        return t;
    }
    public List<TalkDTO> update(Integer id,Integer tid,String title, String description,String ttype,String introduction){
        Talk talk=talkMapper.selectById(tid);
        talkMapper.updateandmodify(tid, title, description,ttype, introduction);
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

    /**
     * 根据用户id的按照收藏和点赞进行推荐排序
     * @param id
     * @return
     * @throws IOException
     */
    public List<TalkDTO> Orderbypredict(Integer id) throws IOException {
        List<LikeInfoDTO> like_talk=talkMapper.selectlikebyUserid(id);
        List<CollectionDTO> collect_talk=talkMapper.selectCollectiontalkbyUserid(id);
        List<TalkDTO> all_talk=talkMapper.selectalltalk();

        String orderstr=pythonService.modelpredict(like_talk,collect_talk,all_talk);
        char[] orderlist=orderstr.toCharArray();

        for (int i=0;i<orderlist.length;i++) {
            all_talk.set(i,talkMapper.selectdetailtalk(Character.getNumericValue(orderlist[i])));
        }
        return all_talk;
    }
}
