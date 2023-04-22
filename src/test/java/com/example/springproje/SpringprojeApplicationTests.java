package com.example.springproje;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.mapper.CommentMapper;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.TalkMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.LikeService;
import com.example.springproje.service.TalkService;
import com.example.springproje.service.UserService;
import com.example.springproje.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringprojeApplicationTests {


    @Resource
//    private TalkMapper talkMapper;
    //    private CommentMapper commentMapper;
    private UserMapper userMapper;

    @Resource
    private LikeMapper likeMapper;
//    private UserService userService;
//    private TalkService talkService;
    @Resource
    private RedisUtils redisUtils;

    @Test
    public void test(){
//        redisUtils.likes(1,2);
//        redisUtils.likes(2,1);
//        System.out.println(talkService.updatelikecount(1).getLikecount());
    }

    @Test
    void contextLoads() {
//        User user=new User();
//        user.setName("Judy");
//        user.setPassword("000000");
//        user.setGmt_create(System.currentTimeMillis());
//        user.setPhone("002");
//        userMapper.insert(user);
//        User user=new User();
//        user.setId('3');
//        Talk t=talkMapper.selectById("0");
//        List<Talk> l=talkService.Selectbyword("candy");
//        User u=userService.register("Amy","000000","000000");
//        List<Talk> talks=talkService.Selectbyword("运动", "happy");
//        System.out.println(likeMapper.CountlikebytalkId(1));
//        talkService.delete(t);
//        talkService.insert(user,"Sad","I lose my mind","/images/thumbs/image1.png");
//        System.out.println(ta.getGmt_create());
    }

}
