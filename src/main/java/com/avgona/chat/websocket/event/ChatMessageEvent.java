package com.avgona.chat.websocket.event;

import java.time.LocalDateTime;

public record ChatMessageEvent(String messageId, String author, String content, LocalDateTime sentAt) {}
