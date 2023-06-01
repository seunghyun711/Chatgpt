package com.chatgpt.service;

import com.chatgpt.dto.ChatgptCompletionRequestDto;
import com.chatgpt.dto.ChatgptCompletionResponseDto;
import com.chatgpt.entity.GptAnswer;
import com.chatgpt.entity.GptQuestion;
import com.chatgpt.repository.GptAnswerRepository;
import com.chatgpt.repository.GptQuestionRepository;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatgptService {
    private final OpenAiService openAiService;

    private final GptAnswerRepository gptAnswerRepository;

    private final GptQuestionRepository gptQuestionRepository;

    /**
     * <gpt에 질문하기>
     * 1. 질문 등록
     * 2. 답변
     * 3. 질문 / 답변 저장
     */
    public ChatgptCompletionResponseDto createAsk(ChatgptCompletionRequestDto request){
        // 1. 질문 등록
        CompletionResult result = openAiService.createCompletion(ChatgptCompletionRequestDto.of(request));
        ChatgptCompletionResponseDto response = ChatgptCompletionResponseDto.of(result);

        // 2. 답변
        List<String> messages = response.getMessages().stream()
                .map(ChatgptCompletionResponseDto.Message::getText)
                .collect(Collectors.toList());

        // 3. 질문 / 답변 저장
        GptAnswer answer = saveAnswer(messages);
        saveQuestions(request.getPrompt(), answer);

        return response;
    }

    // 답변 저장 메서드
    private GptAnswer saveAnswer(List<String> messages) {
        String answer = messages.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
        return gptAnswerRepository.save(new GptAnswer(answer));
    }

    // 질문 저장 메서드
    private void saveQuestions(String question, GptAnswer answer) {
        GptQuestion gptQuestion = new GptQuestion(question, answer);
        gptQuestionRepository.save(gptQuestion);
    }
}


