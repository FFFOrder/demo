package com.example.demo.utils;

/**
 * ajax返回信息
 */
public class R {
    public int code;    //标志，默认为0
    public String msg;  //信息
    public R(){
        this.code = 0;
        this.msg = "";
    }
    public void setCode(int code){
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
