package com.example.springproje.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Likes;
import com.example.springproje.dto.LikeInfoDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface LikeService {
    public List<LikeInfoDTO> insert(Integer talkid, Integer userid);

    public List<LikeInfoDTO> remove(Integer talkid, Integer userid) ;

}
