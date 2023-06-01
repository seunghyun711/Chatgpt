package com.chatgpt.controller;

import com.chatgpt.dto.ChatgptCompletionRequestDto;
import com.chatgpt.dto.ChatgptCompletionResponseDto;
import com.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatgptController {
    private final ChatgptService service;

    @PostMapping("/ask")
    public ResponseEntity askToGpt(@RequestBody ChatgptCompletionRequestDto request) {
        ChatgptCompletionResponseDto response = service.createAsk(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
