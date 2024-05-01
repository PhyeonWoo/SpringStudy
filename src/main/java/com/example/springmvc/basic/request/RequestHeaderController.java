package com.example.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RestController  // 응답값을 값 그대로 반환하게 해준다
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie",required = false) String cookie)
    {
        log.info("requeset={}",request);
        log.info("response={}",response);
        log.info("httpMethod={}",httpMethod);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("header host={}",host);
        log.info("myCookie={}",cookie);

        return "ok";
    }
}
