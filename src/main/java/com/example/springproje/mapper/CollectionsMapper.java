package com.example.springproje.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springproje.bean.Collections;
import com.example.springproje.bean.Talk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionsMapper extends BaseMapper<Collections> {
}
