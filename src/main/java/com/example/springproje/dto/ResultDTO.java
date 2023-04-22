package com.example.springproje.dto;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

@Data
public class ResultDTO {

    private static final Integer SUCCESS_CODE=200;
    private static final Integer ERROR_CODE=500;

    private Integer code;
    private String msg;
    private Object data;

    public ResultDTO(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO(SUCCESS_CODE,"操作成功!",null);
        return resultDTO;
    }

    public static ResultDTO okOf(String message,Object data) {
        ResultDTO resultDTO = new ResultDTO(SUCCESS_CODE,message,data);
        return resultDTO;
    }

    public static ResultDTO okOf(Object data){
        ResultDTO resultDTO=new ResultDTO(SUCCESS_CODE,"操作成功!",data);
        return resultDTO;
    }

    public static ResultDTO okOf(Integer code,String msg,Object data){
        ResultDTO resultDTO=new ResultDTO(code,msg,data);
        return resultDTO;
    }


    public static ResultDTO errorOf() {
        ResultDTO resultDTO = new ResultDTO(ERROR_CODE,"操作失败啦!",null);
        return resultDTO;
    }

    public static ResultDTO errorOf(String message,Object data) {
        ResultDTO resultDTO = new ResultDTO(ERROR_CODE,message,data);
        return resultDTO;
    }

    public static ResultDTO errorOf(Object data){
        ResultDTO resultDTO=new ResultDTO(ERROR_CODE,"操作失败啦！",data);
        return resultDTO;
    }

    public static ResultDTO errorOf(Integer code,String msg,Object data){
        ResultDTO resultDTO=new ResultDTO(code,msg,data);
        return resultDTO;
    }

}
