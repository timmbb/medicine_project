package com.example.springproje.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springproje.bean.User;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.springproje.bean.Talk;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

//@Repository
@Mapper
public interface TalkMapper extends BaseMapper<Talk> {

    Integer updateandmodify(Integer creator, String title, String description,String ttype, String images,String introduction);

    List<Talk> Orderbylike();

    List<Talk> Orderbycomment();

    List<TalkDTO> Selectbyword(String typeword,String descripword);

    List<CollectionDTO> selectCollectiontalkbyUserid(Integer id);

    List<LikeInfoDTO> selectlikebyUserid(Integer id);

    TalkDTO selectdetailtalk(Integer tid);

    List<TalkDTO> selectalltalk();

}