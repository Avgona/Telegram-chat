package com.avgona.chat.service;

import com.avgona.chat.entity.Chat;
import com.avgona.chat.rest.req.ChatCreateReq;
import com.avgona.chat.rest.res.ChatByUserRes;
import com.avgona.chat.rest.req.ChatJoinReq;

import java.util.List;

public interface ChatService {

    void create(ChatCreateReq req);

    void joinChat(ChatJoinReq req);

    List<Chat> getAllByNameStartsWith(String searchTerm);

    List<ChatByUserRes> getAllByUsername(String username);
}
