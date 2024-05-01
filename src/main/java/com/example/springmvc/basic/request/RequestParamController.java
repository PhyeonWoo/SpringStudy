package com.example.springmvc.basic.request;

import com.example.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        log.info("username={}, age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int Memberage) {
        log.info("username={}, age={}",username,Memberage);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,  // 동일시 하면 생략가능하다
            @RequestParam int age) {
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username,  // username 필수X
            @RequestParam(required = true) Integer age) // age 필수O (int는 null이 안되기때문에 Integer로 받아야 한다
    {
        log.info("username={},age={}",username,age);
        return "ok";
    }



    /**
     * String, int, Integer와 같은 단순 타입 => @RequestParam
     * 나머지 @ModelAttribute를 사용한다
     *
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /**
         * @ModelAttribute가 객체를 생성해주고
         * 객체의 프로퍼티를 찾는다 (setUsername, setAge)
         * 해당 프로퍼티의 setter를 호출해서 파라밑의 값을 입력한다
         */
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
        // 바인딩 오류는 age=abc 처럼 숫자가 들어갈 곳에 문자가 들어가면 BindException이 발생한다
    }


    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
    }

}
