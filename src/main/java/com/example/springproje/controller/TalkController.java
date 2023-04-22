package com.example.springproje.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.bean.Talk;
import com.example.springproje.bean.User;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.dto.TalkDTO;
import com.example.springproje.mapper.TalkMapper;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.TalkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TalkController {
    @Resource
    private TalkService talkService;

    @Resource
    private TalkMapper talkMapper;

    @Resource
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping(value = "/add_talk",method = RequestMethod.POST)
    public ResultDTO add_talk(@CurrentUserId Integer id, String title, String description,String ttype, String images, String introduction, Model model, HttpSession session)//
    {
//        User user = userMapper.selectById(id);
        Talk talk=talkService.insert(id,title,description,ttype,images,introduction);
        return ResultDTO.okOf(talk);
    }

    @ResponseBody
    @RequestMapping(value = "/update_talk",method = RequestMethod.POST)
    public Object update_talk(@CurrentUserId Integer id, Integer tid, String title, String description,String ttype, String images,String introduction)//
    {
        return ResultDTO.okOf(talkService.update(id,tid,title,description,ttype,images,introduction));
    }

    @ResponseBody
    @DeleteMapping(value = "/deletetalk")
    public Object deletetalk(@CurrentUserId Integer id, Integer tid) {
        Talk talk=talkMapper.selectById(tid);
        talkService.delete(talk);
        QueryWrapper<Talk> talkQueryWrapper=new QueryWrapper<>();
        talkQueryWrapper.eq("creator",id);
        return ResultDTO.okOf(talkMapper.selectList(talkQueryWrapper));
    }

    /**
     * 根据话题类型和关键词查询
     * @param typeword
     * @param descripword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectword_talk",method = RequestMethod.GET)
    public ResultDTO selectword_talk(String typeword,String descripword)//
    {
        List<TalkDTO> talks=talkService.Selectbyword(typeword, descripword);
        return ResultDTO.okOf(talks);
    }

    /**
     * 查询id用户发的话题
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list_talk",method = RequestMethod.GET)
    public ResultDTO list_talk(@CurrentUserId Integer id)//
    {
        User user = userMapper.selectById(id);

        QueryWrapper<Talk> talkQueryWrapper=new QueryWrapper<>();
        talkQueryWrapper.eq("creator",user.getId());

        List<Talk> talks=talkMapper.selectList(talkQueryWrapper);
        return ResultDTO.okOf(talks);
    }

    /**
     * 显示所有话题
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/all_talk")
    public ResultDTO alltalk(){
        List<TalkDTO> talks=talkService.selectalltalk();
        return ResultDTO.okOf(talks);
    }

    @GetMapping(value = "/detail_talk")
    @ResponseBody
    public ResultDTO detailoftalk(Integer tid){
        return ResultDTO.okOf(talkMapper.selectdetailtalk(tid));
    }
}
