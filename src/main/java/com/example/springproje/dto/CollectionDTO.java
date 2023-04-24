package com.example.springproje.dto;

import lombok.Data;

@Data
public class CollectionDTO {
    private Integer tid;
    private Integer creator;
    private String name;
    private String title;
    private String introduction;
    private String ttype;

    @Override
    public String toString(){
        return "{" +
                "'tid'" +":'"+ tid +"',"+
                "'creator'" + ":'"+creator + "',"+
                "'name'" + ":'"+name +"',"+
                "'title'" + ":'"+title + "',"+
                "'introduction'" + ":'"+introduction + "',"+
                "'ttype'" + ":'"+ttype + "',"+
                '}';
    }
}
