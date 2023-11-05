package kr.inhatc.spring.todo;

import jakarta.persistence.*;
import kr.inhatc.spring.common.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

   private Long id;                    // 아이디

    private String username;            // 사용자 이름

    private String description;         // 할 일 내용

    private LocalDate targetDate;       // 목표 날짜

    private boolean done;               // 완료 여부


}
