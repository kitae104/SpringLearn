package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller                         // 컨트롤러임을 선언
@RequiredArgsConstructor            // final로 선언된 객체를 자동으로 생성해줌
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todo/list")
    public String listAllTodos(@ModelAttribute("name")String name,  Model model) {
        List<TodoDto> todoDtoList = todoService.findByUsername("홍길동");
        model.addAttribute("todoDtoList", todoDtoList);
        model.addAttribute("name", name);
        return "todo/todolist";
    }
}
