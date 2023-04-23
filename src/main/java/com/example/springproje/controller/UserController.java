package com.example.springproje.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springproje.annotation.CurrentUserId;
import com.example.springproje.bean.User;
import com.example.springproje.dto.ResultDTO;
import com.example.springproje.mapper.UserMapper;
import com.example.springproje.service.TokenService;
import com.example.springproje.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenService tokenService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultDTO loginMethod(String username,String password)//
    {
        User user=userService.logIn(username, password);
        if (user!=null)
        {
            String token=tokenService.getToken(user);
            user.setToken(token);
            userMapper.updateById(user);
            return ResultDTO.okOf(user);
        }
        else
            return ResultDTO.errorOf();
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param rpassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultDTO registerMethod(String username, String password, String rpassword)//
    {
        userService.register(username,password,rpassword);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("name",username);

        User user = userMapper.selectOne(userQueryWrapper);
        return ResultDTO.okOf(user);
    }

    /**
     * 获取用户详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/detail")
    public ResultDTO getUserInfoByUserId(@CurrentUserId Integer id) {
        return ResultDTO.okOf(userMapper.selectById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/self_edit",method = RequestMethod.POST)
    public ResultDTO editDetail(@CurrentUserId Integer id,String name,String email,String phone,String self_introduction){
        User user = userService.insertdetail(id, name,email, phone, self_introduction);
        return ResultDTO.okOf(user);
    }

    /**
     * 注销
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping(value = "/log_off")
    public ResultDTO logoff(@CurrentUserId Integer id){
        return ResultDTO.okOf(userMapper.deleteById(id));
    }

}
