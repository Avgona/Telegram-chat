package com.avgona.chat.rest;

import com.avgona.chat.entity.Chat;
import com.avgona.chat.rest.req.ChatCreateReq;
import com.avgona.chat.rest.res.ChatByUserRes;
import com.avgona.chat.service.ChatService;
import com.avgona.chat.rest.req.ChatJoinReq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final ChatService service;


    @GetMapping
    public List<ChatByUserRes> getAllByUsername(@RequestParam String username) {
        return service.getAllByUsername(username);
    }

    @GetMapping("/search")
    public List<Chat> searchChats(@RequestParam String searchTerm) {
        return service.getAllByNameStartsWith(searchTerm);
    }

    @PostMapping
    public void create(@RequestBody ChatCreateReq req) {
        service.create(req);
    }

    @PostMapping("/join")
    public void join(@RequestBody ChatJoinReq event) {
        service.joinChat(event);
    }

}
