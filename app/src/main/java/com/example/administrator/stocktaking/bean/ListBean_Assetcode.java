package com.example.administrator.stocktaking.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 */

public class ListBean_Assetcode {

    public ListBean_Assetcode() {
        Marks=new ArrayList<>();
    }
    public ListBean_Assetcode(String code) {
        Code=code;
        Marks=new ArrayList<>();
    }
    private int id ;
    private String Code;
    private String Mark;

    public ListBean_Assetcode(String code,String mark) {
        Code=code;
        Mark=mark;
    }

    public List<String> getMarks() {
        return Marks;
    }

    public void setMarks(List<String> marks) {
        Marks = marks;
    }

    private List<String> Marks;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
