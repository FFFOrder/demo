package com.example.demo.domain;

public class GoodsDO {
    private String id;
    private String name;
    private double price;
    private String info;

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setInfo(String info){
        this.info = info;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getInfo(){
        return info;
    }
}
