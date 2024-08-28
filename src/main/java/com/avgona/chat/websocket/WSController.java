package com.avgona.chat.websocket;

import com.avgona.chat.service.MessageService;
import com.avgona.chat.websocket.event.ChatMessageEvent;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WSController {

    private final MessageService service;

    @MessageMapping("/chat/{chatName}/sendMessage")
    @SendTo("/chat/{chatName}")
    public ChatMessageEvent handleMessage(@DestinationVariable String chatName, @Payload ChatMessageEvent event) {
        return service.save(chatName, event);
    }
}
