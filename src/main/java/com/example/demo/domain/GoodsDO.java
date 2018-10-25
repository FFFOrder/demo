package com.example.demo.domain;

import java.io.Serializable;

public class GoodsDO implements Serializable {
    private String id;
    private String name;
    private double price;
    private String info;
    private int number;
    private int sold;

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
    public void setNumber(int number){
        this.number = number;
    }
    public void setSold(int sold){
        this.sold = sold;
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
    public int getNumber(){
        return number;
    }
    public int getSold(){
        return sold;
    }
}
