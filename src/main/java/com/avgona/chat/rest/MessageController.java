package com.avgona.chat.rest;

import com.avgona.chat.entity.MessageByChat;
import com.avgona.chat.rest.res.MessageByChatRes;
import com.avgona.chat.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;


    @GetMapping("/getAllByChatName")
    public List<MessageByChatRes> getAllByChatName(@RequestParam String chatName) {
        return service.getAllByChatName(chatName);
    }

    @GetMapping("/getAllByUsername")
    public List<MessageByChat> getAllByUsername(@RequestParam String username) {
        return List.of(); // TODO
    }
}
