package com.avgona.chat.service;

import com.avgona.chat.entity.Chat;
import com.avgona.chat.entity.ChatByUser;
import com.avgona.chat.repository.ChatRepository;
import com.avgona.chat.repository.ChatsByUserRepository;
import com.avgona.chat.rest.req.ChatCreateReq;
import com.avgona.chat.rest.res.ChatByUserRes;
import com.avgona.chat.rest.req.ChatJoinReq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatsByUserRepository chatsByUserRepository;

    @Override
    public void create(ChatCreateReq req) {
        var chat = new Chat(req.chatName(), req.description(), LocalDate.now());
        chatRepository.save(chat);
        var chatByUser = new ChatByUser(new ChatByUser.ChatByUserKey(req.username(), req.chatName()), req.description(), LocalDate.now());
        chatsByUserRepository.save(chatByUser);
    }

    @Override
    public void joinChat(ChatJoinReq req) {
        var chatByUser = new ChatByUser(new ChatByUser.ChatByUserKey(req.username(), req.chatName()), req.description(), LocalDate.now());
        chatsByUserRepository.save(chatByUser);
    }

    @Override
    public List<Chat> getAllByNameStartsWith(String searchTerm) {
        return chatRepository.findAll()
                .stream()
                .filter(it -> it.getChatName().startsWith(searchTerm))
                .toList();
    }

    @Override
    public List<ChatByUserRes> getAllByUsername(String username) {
        var result = chatsByUserRepository.findAllByKeyUsername(username);
        System.out.println(result);
        return result.stream()
                .map(it -> new ChatByUserRes(
                        it.getKey().getUsername(),
                        it.getKey().getChatName(),
                        it.getDescription())
                )
                .toList();
    }
}
