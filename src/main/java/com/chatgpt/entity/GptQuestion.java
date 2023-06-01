package com.chatgpt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GptQuestion { // 질문 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 1000, nullable = false)
    private String question;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private GptAnswer gptAnswer;

    public GptQuestion(String question, GptAnswer gptAnswer) {
        this.question = question;
        this.gptAnswer = gptAnswer;
    }
}
