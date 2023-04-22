package com.example.springproje.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springproje.bean.Likes;
import com.example.springproje.dto.LikeCountDTO;
import com.example.springproje.dto.LikeDTO;
import com.example.springproje.enums.LikeStatusEnum;
import com.example.springproje.mapper.LikeMapper;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private LikeMapper likeMapper;

    public void likes(Integer infoId, Integer likeUserId) {
        String likedKey = RedisKeyUtils.getLikedKey(infoId, likeUserId);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, infoId, 1);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, likedKey, LikeStatusEnum.LIKE.getCode());
        Likes likes=new Likes();
        likes.setInfoId(infoId);
        likes.setLikeUserId(likeUserId);
        likes.setStatus(1);
        likes.setCreateTime(System.currentTimeMillis());
        likeMapper.insert(likes);
    }


    public void unLikes(Integer infoId, Integer likeUserId) {
        String likedKey = RedisKeyUtils.getLikedKey(infoId, likeUserId);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, infoId, -1);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, likedKey);
        UpdateWrapper<Likes> likesUpdateWrapper=new UpdateWrapper<>();
        likesUpdateWrapper.eq("info_id",infoId);
        likesUpdateWrapper.eq("like_user_id",likeUserId);
//        likesUpdateWrapper.set("status",0);
//        likesUpdateWrapper.set("update_time",System.currentTimeMillis());
        likeMapper.delete(likesUpdateWrapper);
    }

    public Object likeStatus(Integer infoId, Integer likeUserId) {
        if (redisTemplate.opsForHash().hasKey(RedisKeyUtils.MAP_KEY_USER_LIKED, RedisKeyUtils.getLikedKey(infoId, likeUserId))) {
            String o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_LIKED, RedisKeyUtils.getLikedKey(infoId, likeUserId)).toString();
            if ("1".equals(o)) {
                unLikes(infoId, likeUserId);
                return LikeStatusEnum.UNLIKE;
            }
            if ("0".equals(o)) {
                likes(infoId, likeUserId);
                return LikeStatusEnum.LIKE;
            }
        }
        Likes userLikes = likeMapper.selectOne(new QueryWrapper<Likes>().eq("info_id", infoId).eq("like_user_id", likeUserId));
        if (userLikes == null) {
            Likes userLikes1 = new Likes();
            userLikes1.setInfoId(infoId);
            userLikes1.setLikeUserId(likeUserId);
            likeMapper.insert(userLikes1);
            likes(infoId, likeUserId);
            return LikeStatusEnum.LIKE;
        }
        if (userLikes.getStatus() == 1) {
            unLikes(infoId, likeUserId);
            return LikeStatusEnum.UNLIKE;
        }

        if (userLikes.getStatus() == 0) {
            likes(infoId, likeUserId);
            return LikeStatusEnum.LIKE;
        }
        return "";
    }
    public List<LikeDTO> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<LikeDTO> list = new ArrayList<>();
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            String key = (String) entry.getKey();
            String[] split = key.split("::");
            Integer infoId = Integer.valueOf(split[0]);
            Integer likeUserId = Integer.valueOf(split[1]);
            Integer value = (Integer) entry.getValue();
            //组装成 UserLike 对象
            LikeDTO userLikeDetail = new LikeDTO(infoId, likeUserId, value);
            list.add(userLikeDetail);
            //存到 list 后从 Redis 中删除
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }
        return list;
    }
    public List<LikeCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikeCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            Integer key = (Integer) map.getKey();
            Integer value = (Integer) map.getValue();
            LikeCountDTO userLikCountDTO = new LikeCountDTO(key, value);
            list.add(userLikCountDTO);
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }
}
