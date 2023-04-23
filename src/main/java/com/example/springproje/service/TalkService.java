package com.example.springproje.service;

import com.example.springproje.bean.Talk;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;

import java.util.List;

public interface TalkService {
    public Talk insert(Integer id, String title, String description, String ttype, String images, String introduction);

    public List<TalkDTO> update(Integer id, Integer tid, String title, String description, String ttype, String images, String introduction);

    public boolean delete(Talk talk);

    public List<Talk> Orderbylike();

    public List<Talk> Orderbycomment();

    public List<TalkDTO> Selectbyword(String typeword,String descripword);

    public List<CollectionDTO> selectCollectiontalkbyUserid(Integer id);

    public List<LikeInfoDTO> selectlikebyUserid(Integer id);

    public TalkDTO detailtalk(Integer tid);

    public List<TalkDTO> selectalltalk();

    public TalkDTO updatelikecount(Integer tid);

    public TalkDTO updatecommentcount(Integer tid);

    }
