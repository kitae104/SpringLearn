package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;        // TodoRepository 객체를 주입받음
    private final ModelMapper modelMapper;              // ModelMapper 객체를 주입받음

    public List<TodoDto> findByUsername(String username) {
        List<Todo> todoList = todoRepository.findByUsername(username);
        List<TodoDto> todoDtoList = Todo.toDtoList(todoList);
        return todoDtoList;
    }

    public void add(TodoDto todoDto) {
        // TodoDto 객체를 Todo 객체로 변환
//        Todo todo = Todo.builder()
//                .username(todoDto.getUsername())
//                .description(todoDto.getDescription())
//                .targetDate(todoDto.getTargetDate())
//                .done(todoDto.isDone())
//                .build();

        Todo todo = modelMapper.map(todoDto, Todo.class);
        todoRepository.save(todo);
    }
}
