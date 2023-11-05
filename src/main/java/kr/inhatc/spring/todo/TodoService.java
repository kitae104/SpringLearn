package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoDto> findByUsername(String username) {
        List<Todo> todoList = todoRepository.findByUsername(username);
        List<TodoDto> todoDtoList = Todo.toDtoList(todoList);
        return todoDtoList;
    }


}
