package kr.inhatc.spring.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})    // 감시 기능 적용
@MappedSuperclass   //자식 클래스에 매핑 정보만 제공하기 위해 사용
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate                    // 생성되어 저장될 때 시간을 자동으로 저장
    @Column(updatable = false)      // 수정 불가능
    private LocalDateTime regTime;

    @LastModifiedDate               // 값이 변경될 때 시간을 자동으로 저장
    private LocalDateTime updateTime;


}