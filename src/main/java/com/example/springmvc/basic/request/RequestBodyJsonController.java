package com.example.springmvc.basic.request;


import com.example.springmvc.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper(); // JSON일 경우 ObjectMapper 가 필요하다

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);  // Json형태로 바꾸는 과정
        log.info("username={},age={}", helloData.getUsername(),helloData.getAge());
        response.getWriter().write("ok");
    }


    @ResponseBody
    @PostMapping("/request-body-json-v2")  // RequsetBody -> MessageConverter의 기능을 사용
    // RequestBody를 생략하면 ModelAttribute가 자동으로 붙는다
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}",messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username={}, age={}, email={}",data.getUsername(),data.getAge(),data.getEmail());
        return "ok";
    }

    // ResponseBody는 자바 객체를 json 기반의 HTTP Body로 변환해주는 역할을 한다
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    // RequestBody는 json 기반의 HTTP Body를 자바 객체로 변환해주는 역할을 한다
    public String requestBodyJsonV3(@RequestBody HelloData data) {
        log.info("username={},age={},email={}",data.getUsername(),data.getAge(),data.getEmail());
        return "ok";
    }

    // 리턴값을 HelloData로 지정한다
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public HelloData requestBodyJsonV4(@RequestBody HelloData data) throws IOException {
        log.info("username={},age={},eamil={}",data.getUsername(),data.getAge(),data.getEmail());
        return data;
        // HelloData가 HttpMessageConverter에 들어가서 Json형태로 바뀌고 출력이 된다

        // RequestBody : JSON으로 입력 -> HTTP메시지 컨버터 -> 객체
        // ResponseBody : 객체 -> HTTP메시지 컨버터 -> JSON 응답

        // Json으로 입력된 값이 객체로 변경이 되고 출력될때 다시 Json으로 변형되서 출력이 된다
    }
}
