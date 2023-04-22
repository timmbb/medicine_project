package com.example.springproje.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Collections;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.mapper.CollectionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionsService {
    @Resource
    private CollectionsMapper collectionMapper;
    @Resource
    private TalkService talkService;

    public List<CollectionDTO> insert(Integer talkid, Integer userid){
        Collections collection=new Collections();
        collection.setPostId(talkid);
        collection.setUserId(userid);
        collectionMapper.insert(collection);
        return talkService.selectCollectiontalkbyUserid(userid);
    }

    public List<CollectionDTO> remove(Integer talkid, Integer userid){
        QueryWrapper<Collections> collectionQueryWrapper=new QueryWrapper<>();
        collectionQueryWrapper.eq("post_id",talkid);
        collectionQueryWrapper.eq("user_id",userid);
        collectionMapper.delete(collectionQueryWrapper);
        return talkService.selectCollectiontalkbyUserid(userid);
    }


}
