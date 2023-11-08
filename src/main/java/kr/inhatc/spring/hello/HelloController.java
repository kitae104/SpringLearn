package kr.inhatc.spring.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
