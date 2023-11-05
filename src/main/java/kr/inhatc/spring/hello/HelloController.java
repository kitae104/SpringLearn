package kr.inhatc.spring.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody   // @RestController로 대체 가능
    public String start() {
        return "English, 한글, 1234";
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
