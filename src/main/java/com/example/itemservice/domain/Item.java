package com.example.itemservice.domain;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer amount;

    // Item을 호출해서 사용하기 위해
    public Item() {

    }

    public Item(String itemName,Integer price, Integer amount){
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }
}
