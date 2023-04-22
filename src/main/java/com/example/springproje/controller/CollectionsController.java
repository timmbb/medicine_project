package com.example.springproje.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.bean.Collections;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.CollectionsMapper;
import com.example.springproje.service.CollectionsService;
import com.example.springproje.service.TalkService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class CollectionsController {
    @Resource
    private CollectionsMapper collectionsMapper;

    @Resource
    private CollectionsService collectionsService;

    @Resource
    private TalkService talkService;

    @PostMapping(value = "/addCollection")
    @ResponseBody
    public ResultDTO addcollection(@CurrentUserId Integer id,Integer talkid){
        return ResultDTO.okOf(collectionsService.insert(talkid,id));
    }

    @DeleteMapping(value = "/removecollection")
    @ResponseBody
    public ResultDTO removecollection(@CurrentUserId Integer id,Integer talkid){
        return ResultDTO.okOf(collectionsService.remove(talkid,id));
    }

    /**
     * 查询用户的收藏
     * @param id
     * @return
     */
    @GetMapping(value = "/selectcollectionbyuser")
    @ResponseBody
    public ResultDTO selectcollectionbyuser(@CurrentUserId Integer id){
        return ResultDTO.okOf(talkService.selectCollectiontalkbyUserid(id));
    }

    /**
     * 查询帖子的收藏
     * @param tid
     * @return
     */
    @GetMapping(value = "/selectcollectionbytalk")
    @ResponseBody
    public ResultDTO selectcollectionbytalk(Integer tid){
        QueryWrapper<Collections> collectionsQueryWrapper=new QueryWrapper<>();
        collectionsQueryWrapper.eq("post_id",tid);
        return ResultDTO.okOf(collectionsMapper.selectList(collectionsQueryWrapper));
    }
}
