package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller                         // 컨트롤러임을 선언
@RequiredArgsConstructor            // final로 선언된 객체를 자동으로 생성해줌
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todo/list")
    public String showAllTodoListPage(@ModelAttribute("name")String name,  Model model) {
        List<TodoDto> todoDtoList = todoService.findByUsername("홍길동");
        model.addAttribute("todoDtoList", todoDtoList);
        model.addAttribute("name", name);
        return "todo/listTodo";
    }

    @GetMapping("/todo/add")
    public String showAddTodoPage() {
        return "todo/addTodo";
    }

    @PostMapping("/todo/add")
    public String addTodo(TodoDto todoDto) {
        todoService.add(todoDto);
        return "redirect:/todo/list";       // redirect: 를 붙이면 해당 url로 리다이렉트
    }
}
