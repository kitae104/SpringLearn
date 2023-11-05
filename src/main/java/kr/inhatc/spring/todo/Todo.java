package kr.inhatc.spring.todo;

import jakarta.persistence.*;
import kr.inhatc.spring.common.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;                    // 아이디

    private String username;            // 사용자 이름

    private String description;         // 할 일 내용

    private LocalDate targetDate;       // 목표 날짜

    private boolean done;               // 완료 여부

    // 엔티티 리스트를 DTO 리스트로 변환
    public static List<TodoDto> toDtoList(List<Todo> todoList) {
        return todoList.stream()
                .map(todo -> TodoDto.builder()
                        .id(todo.getId())
                        .username(todo.getUsername())
                        .description(todo.getDescription())
                        .targetDate(todo.getTargetDate())
                        .done(todo.isDone())
                        .build())
                .collect(Collectors.toList());
    }
}
