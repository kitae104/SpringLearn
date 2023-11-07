package kr.inhatc.spring.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "설명은 필수 항목입니다. 내용을 입력해주세요.")
    @Size(min = 10, message = "최소 10자 이상 입력해주세요.")
    private String description;         // 할 일 내용
    private LocalDate targetDate;       // 목표 날짜
    private boolean done;               // 완료 여부
}
