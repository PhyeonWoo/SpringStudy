package com.example.springmvc.basic.requestmapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController  // JSON 스타일로 반환된다
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // 단일 파라미터 조회
    @GetMapping("/mapping/{userId}")
    public String mappingPath1(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}",data);
        return "ok";
    }

    // 다중 파라미터 조회
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath2(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}",userId,orderId);
        return "ok";
    }
}
