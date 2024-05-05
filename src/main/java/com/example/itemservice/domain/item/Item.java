package com.example.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

// @Data 대신 Getter,Setter만 쓰는게 좋다
@Getter
@Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer amount;

    // Item을 호출해서 사용하기 위해
    public Item() {
    }


    // 생성자
    public Item(String itemName, Integer price, Integer amount) {
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }
}
