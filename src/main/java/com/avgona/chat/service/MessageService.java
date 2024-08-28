package com.avgona.chat.service;

import com.avgona.chat.rest.res.MessageByChatRes;
import com.avgona.chat.websocket.event.ChatMessageEvent;

import java.util.List;

public interface MessageService {

    ChatMessageEvent save(String chatName, ChatMessageEvent event);

    List<MessageByChatRes> getAllByChatName(String chatName);

}
