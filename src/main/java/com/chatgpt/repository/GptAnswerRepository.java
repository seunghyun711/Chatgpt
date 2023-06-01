package com.chatgpt.repository;

import com.chatgpt.entity.GptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GptAnswerRepository extends JpaRepository<GptAnswer, Long> {
}
