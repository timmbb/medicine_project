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

    Integer updateandmodify(Integer tid, String title, String description,String ttype,String introduction);

    List<Talk> Orderbylike();

    List<Talk> Orderbycomment();

    /**
     * 根据类型和内容关键词来寻找话题
     * @param typeword
     * @param descripword
     * @return
     */
    List<TalkDTO> Selectbyword(String typeword,String descripword);

    /**
     * 根据用户id查找收藏话题一些信息
     * @param id
     * @return
     */
    List<CollectionDTO> selectCollectiontalkbyUserid(Integer id);

    /**
     * 根据用户id查找点赞话题一些信息
     * @param id
     * @return
     */
    List<LikeInfoDTO> selectlikebyUserid(Integer id);

    TalkDTO selectdetailtalk(Integer tid);

    List<TalkDTO> selectalltalk();

    /**
     * 根据目前用户寻找自己发的话题
     * @param id
     * @return
     */
    List<TalkDTO> selecttalkbyuser(Integer id);

}