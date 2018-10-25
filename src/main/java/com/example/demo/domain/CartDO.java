package com.example.demo.domain;

public class CartDO {
    private String id;
    private String username;
    private String goodsid;
    private int goodsnumber;
    private String goodsname;
    private double price;
    private String info;

    public String getId(){
        return  id;
    }
    public String getUsername(){
        return username;
    }
    public String getGoodsid(){
        return goodsid;
    }
    public int getGoodsnumber(){
        return goodsnumber;
    }
    public String getGoodsname() { return goodsname; }
    public double getPrice() { return price; }
    public String getInfo() {
        return info;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setGoodsid(String goodsid){
        this.goodsid = goodsid;
    }
    public void setGoodsnumber(int goodsnumber){
        this.goodsnumber = goodsnumber;
    }
    public void setGoodsname(String goodsname) { this.goodsname = goodsname; }
    public void setPrice(double price) { this.price = price; }
    public void setInfo(String info){
        this.info = info;
    }
}
