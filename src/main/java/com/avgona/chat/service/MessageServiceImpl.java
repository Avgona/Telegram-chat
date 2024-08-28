package com.avgona.chat.service;

import com.avgona.chat.entity.MessageByChat;
import com.avgona.chat.repository.MessageByChatRepository;
import com.avgona.chat.rest.res.MessageByChatRes;
import com.avgona.chat.websocket.event.ChatMessageEvent;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageByChatRepository repository;


    @Override
    public ChatMessageEvent save(String chatName, ChatMessageEvent event) {
        var messageId = Uuids.timeBased();
        var messageByChat = new MessageByChat(chatName, messageId, event.author(), event.content(), event.sentAt());
        repository.save(messageByChat);
        return new ChatMessageEvent(messageId.toString(), event.author(), event.content(), event.sentAt());
    }

    @Override
    public List<MessageByChatRes> getAllByChatName(String chatName) {
        var result = repository.findAllByKeyChatName(chatName);
        return result.stream()
                .map(it -> new MessageByChatRes(
                                it.getKey().getMessageId().toString(),
                                it.getUsername(),
                                it.getContent(),
                                LocalDateTime.ofInstant(it.getSentAt(), ZoneId.systemDefault())
                        )
                )
                .toList();
    }
}
