package com.chatgpt.dto;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatgptCompletionResponseDto {
    private List<Message> messages; // gpt의 응답 내용
    private Usage usage;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message{
        private String text;

        private Integer index;

        private String finishReason;

        public static Message of(CompletionChoice choice) {
            return new Message(
                    choice.getText(),
                    choice.getIndex(),
                    choice.getFinish_reason()
            );
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage{
        private Long promptTokens;

        private Long completionTokens;

        private Long totalTokens;

        public static Usage of(com.theokanning.openai.Usage usage) {
            return new Usage(
                    usage.getPromptTokens(),
                    usage.getCompletionTokens(),
                    usage.getTotalTokens()
            );
        }
    }

    public static List<Message> messageResponse(List<CompletionChoice> choices) {
        return choices.stream()
                .map(Message::of)
                .collect(Collectors.toList());
    }

    public static ChatgptCompletionResponseDto of(CompletionResult result) {
        return new ChatgptCompletionResponseDto(
                messageResponse(result.getChoices()),
                Usage.of(result.getUsage())
        );
    }
}
