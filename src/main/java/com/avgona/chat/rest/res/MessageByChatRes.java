package com.avgona.chat.rest.res;


import java.time.LocalDateTime;

public record MessageByChatRes(String messageId, String author, String content, LocalDateTime sentAt) {}
