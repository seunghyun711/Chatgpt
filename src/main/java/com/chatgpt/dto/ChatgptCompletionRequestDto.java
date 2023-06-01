package com.chatgpt.dto;

import com.theokanning.openai.completion.CompletionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class ChatgptCompletionRequestDto { // Completion 요청 dto
    private String model; // 사용하려는 api 모델
    private String prompt; // 질문 내용
    private Integer maxToken; // completion에서 생성할 최대 토큰 수 -> 응답 컨텍스트 길이

    public static CompletionRequest of(ChatgptCompletionRequestDto request) {
        return CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(request.getPrompt())
                .maxTokens(request.getMaxToken())
                .build();
    }
}
