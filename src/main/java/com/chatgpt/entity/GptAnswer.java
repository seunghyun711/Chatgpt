package com.chatgpt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GptAnswer { // 답변 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 1000, nullable = false)
    private String answer;

    public GptAnswer(String answer) {
        this.answer = answer;
    }
}
