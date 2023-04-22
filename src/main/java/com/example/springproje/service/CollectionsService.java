package com.example.springproje.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Collections;
import com.example.springproje.mapper.CollectionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionsService {
    @Resource
    private CollectionsMapper collectionMapper;

    public Collections insert(Integer talkid, Integer userid){
        Collections collection=new Collections();
        collection.setPostId(talkid);
        collection.setUserId(userid);
        collectionMapper.insert(collection);
        return collection;
    }

    public int remove(Integer talkid,Integer userid){
        QueryWrapper<Collections> collectionQueryWrapper=new QueryWrapper<>();
        collectionQueryWrapper.eq("post_id",talkid);
        collectionQueryWrapper.eq("user_id",userid);

        return collectionMapper.delete(collectionQueryWrapper);
    }


}
