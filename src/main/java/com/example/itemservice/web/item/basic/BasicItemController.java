package com.example.itemservice.web.item.basic;


import com.example.itemservice.domain.item.Item;
import com.example.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    // 전체조회
    @GetMapping
    public String items(Model model){
        List<Item> itemsList = itemRepository.findAll();
        model.addAttribute("items", itemsList);
        return "basic/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA",15000,10));
        itemRepository.save(new Item("testB",13000,10));
    }
}
