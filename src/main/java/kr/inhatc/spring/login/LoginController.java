package kr.inhatc.spring.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("name")
public class LoginController {

    private final AuthenticationService authenticationService;
    @GetMapping("/log")
    public void log(@RequestParam("aa") String aa){
        log.debug("Debugging log" + aa);
        log.info("Info log" + aa);
        log.warn("Warning log" + aa);
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String loginPost(LoginDto loginDto, Model model) {
        if(!authenticationService.authenticate(loginDto.getName(), loginDto.getPassword())){
            model.addAttribute("errorMsg", "아이디와 비밀번호를 확인후 다시 시도해 주세요.");
            return "login/login";
        }
        model.addAttribute("loginDto", loginDto);
        model.addAttribute("name", loginDto.getName());
        return "login/welcome";
    }
}
