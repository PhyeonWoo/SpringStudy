package com.example.itemservice.domain.item;


import com.example.itemservice.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 상품 저장소
@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    // Item 내역 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    // ID를 찾는 방법
    public Item findById(Long id) {
        return store.get(id);
    }

    // Item의 값을 전부 불러오게 해주는 코드
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // Item 업데이트
    public void update(Long id,Item updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setAmount(updateParam.getAmount());
    }

    // 상점 초기화
    public void clearStore() {
        store.clear();
    }
}
