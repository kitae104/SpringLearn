package kr.inhatc.spring.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private void createTodoList() {

        for (int i = 1; i <= 3; i++) {
            Todo todo = Todo.builder()
                    .username("홍길동")
                    .description("테스트" + i)
                    .targetDate(LocalDate.now().plusYears(i))
                    .done(false)
                    .build();
            todoRepository.save(todo);
        }
    }

    @Test
    @DisplayName("findByUsername 테스트")
    public void findByUsername() {
        createTodoList();
        assertEquals(3, todoRepository.findByUsername("홍길동").size());
    }
}