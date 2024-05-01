package com.example.springmvc.basic;

import lombok.Data;

@Data
/**
 * Data 애노테이션이 ToString, Getter, Setter등을 포함하고 있다
 */
public class HelloData {
    private String username;
    private int age;
    private String email;
}
