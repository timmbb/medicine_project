package com.example.springproje;

import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(date));
    }

}
