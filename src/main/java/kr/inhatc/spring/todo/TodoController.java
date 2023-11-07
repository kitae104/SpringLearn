package kr.inhatc.spring.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller                         // 컨트롤러임을 선언
@RequiredArgsConstructor            // final로 선언된 객체를 자동으로 생성해줌
@SessionAttributes("name")          // name이라는 이름으로 저장된 세션 데이터가 있으면 모델에 자동으로 추가
@Slf4j                              // 로그를 위한 어노테이션
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todo/list")
    public String showAllTodoListPage(@ModelAttribute("name")String name,  Model model) {
        List<TodoDto> todoDtoList = todoService.findByUsername(name);
        model.addAttribute("todoDtoList", todoDtoList);
        model.addAttribute("name", name);
        return "todo/todoList";
    }

    @GetMapping("/todo/add")
    public String showAddTodoPage(Model model) {
        model.addAttribute("todoDto", new TodoDto());
        return "todo/todoAdd";
    }

    @PostMapping("/todo/add")
    public String addTodo(@Valid TodoDto todoDto, BindingResult bindingResult,
                          @ModelAttribute("name")String name, Model model) {

        if(bindingResult.hasErrors()) {
            return "todo/todoAdd";
        }

        try {
            todoDto.setUsername(name);
            todoDto.setTargetDate(LocalDate.now().plusYears(1));
            todoDto.setDone(false);
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
