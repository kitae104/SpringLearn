package kr.inhatc.spring.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller                         // 컨트롤러임을 선언
@RequiredArgsConstructor            // final로 선언된 객체를 자동으로 생성해줌
@Slf4j                              // 로그를 위한 어노테이션
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todo/list")
    public String showAllTodoListPage(Model model) {

        List<TodoDto> todoDtoList = todoService.findByUsername(getLoginUserName());
        model.addAttribute("todoDtoList", todoDtoList);
        model.addAttribute("name", getLoginUserName());
        return "todo/todoList";
    }

    private static String getLoginUserName() {
        String name = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            name = authentication.getName();
        }
        return name;
    }

    @GetMapping("/todo/add")
    public String showAddTodoPage(Model model) {
        TodoDto todoDto = new TodoDto();
        todoDto.setUsername(getLoginUserName());
        model.addAttribute("todoDto", todoDto);
        return "todo/todoAdd";
    }

    @PostMapping("/todo/add")
    public String addTodo(@Valid TodoDto todoDto, BindingResult bindingResult,
                          Model model) {

        if(bindingResult.hasErrors()) {
            return "todo/todoAdd";
        }

        try {
            log.info("==================todoDto: {}", todoDto);
            todoService.add(todoDto);
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "todo/todoAdd";
        }
        return "redirect:/todo/list";       // redirect: 를 붙이면 해당 url로 리다이렉트
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        log.info("id: {}", id);
        todoService.deleteById(id);
        return "redirect:/todo/list";
    }

    @GetMapping("/todo/update/{id}")
    public String showUpdateTodoPage(@PathVariable("id") Long id, Model model) {
        log.info("id: {}", id);
        TodoDto todoDto = todoService.findById(id);
        model.addAttribute("todoDto", todoDto);
        return "todo/todoAdd";
    }

    @PostMapping("/todo/update/{id}")
    public String updateTodo(@Valid TodoDto todoDto, BindingResult bindingResult,
                          @ModelAttribute("name")String name, Model model) {

        if(bindingResult.hasErrors()) {
            return "todo/todoAdd";
        }

        try {
            todoService.updateTodo(todoDto);
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "todo/todoAdd";
        }
        return "redirect:/todo/list";       // redirect: 를 붙이면 해당 url로 리다이렉트
    }
}
