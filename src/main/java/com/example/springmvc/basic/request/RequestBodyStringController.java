/**
 * HTTP 요청 메시지 - 단순 텍스트
 * HTTP message body에 담아서 전달
 */

package com.example.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


/**
 * HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우는
 * @RequestParam, @ModelAttribute를 사용할 수 없다
 *
 * HTTP 메시지 바디의 데이터를 InputStream을 통해 직접 읽을 수 있다
 *
 */
@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String meesageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("meesage Body={}",meesageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String meesageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  // InputStream으로 HTTP요청 메시지 바디의 내용을 직접 조회

        log.info("messageBody={}",meesageBody);
        responseWriter.write("ok");  // OutputStream으로 HTTP응답 메세지 바디에 직접 결과출력
    }

    /**
     * HttpEntity: HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
     * 응답에서도 HttpEntity 사용 가능
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV2(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}",messageBody);
        return new HttpEntity<>("ok");

    }

    /**
     * @RequestBody - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
     * @ResponseBody - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody // 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다.
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody);
        return "ok";  // ResponseBody부분
    }
}
