package com.example.springproje;

import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;
import com.example.springproje.mapper.LikeMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.PythonService;
import com.example.springproje.service.TalkService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class SpringprojeApplicationTests {


    @Resource
//    private TalkMapper talkMapper;
    //    private CommentMapper commentMapper;
    private UserMapper userMapper;

    @Resource
    private PythonService pythonService;
    @Resource
    private LikeMapper likeMapper;
//    private UserService userService;
    @Resource
    private TalkService talkService;

    @Test
    public void test(){
//        redisUtils.likes(1,2);
//        redisUtils.likes(2,1);
//        System.out.println(talkService.updatelikecount(1).getLikecount());
    }

    @Test
    void contextLoads() throws IOException {
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
//        List<LikeInfoDTO> like_talk=talkService.selectlikebyUserid(1);
//        List<CollectionDTO> collect_talk=talkService.selectCollectiontalkbyUserid(1);
//        List<TalkDTO> all_talk=talkService.selectalltalk();
//        System.out.println(like_talk);
//        System.out.println(collect_talk);
//        System.out.println(all_talk);
//        System.out.println(pythonService.modelpredict(like_talk,collect_talk,all_talk));
//        char i='1';
//        System.out.println(Character.getNumericValue(i));
//        System.out.println(talkService.Orderbypredict(1));
    }

}
