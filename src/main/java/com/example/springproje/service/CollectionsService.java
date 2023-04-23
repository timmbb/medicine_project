package com.example.springproje.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Collections;
import com.example.springproje.dto.CollectionDTO;

import java.util.List;

public interface CollectionsService {
    public List<CollectionDTO> insert(Integer talkid, Integer userid);

    public List<CollectionDTO> remove(Integer talkid, Integer userid);

}
