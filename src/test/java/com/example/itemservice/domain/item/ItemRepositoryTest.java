package com.example.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",1000,10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findById = itemRepository.findById(item.getId());
        assertThat(findById).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",15000,10);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> allItem = itemRepository.findAll();
        //then
        assertThat(allItem.size()).isEqualTo(2); // 총 갯수가 2인지
        assertThat(allItem).contains(item1,item2); // item1과 item2를 포함하고 있는지
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1",15000,10);
        //when
        Item updateParam = itemRepository.save(item);
        itemRepository.update(updateParam.getId(),updateParam);
        //then
        Item updateItem = itemRepository.findById(updateParam.getId());
    }

}