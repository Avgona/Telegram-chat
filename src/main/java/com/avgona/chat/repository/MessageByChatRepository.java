package com.avgona.chat.repository;

import com.avgona.chat.entity.MessageByChat;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageByChatRepository extends CassandraRepository<MessageByChat, MessageByChat.MessageByChatKey> {

    List<MessageByChat> findAllByKeyChatName(String chatName);

}
