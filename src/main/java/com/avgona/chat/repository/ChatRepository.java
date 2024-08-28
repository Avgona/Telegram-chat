package com.avgona.chat.repository;

import com.avgona.chat.entity.Chat;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends CassandraRepository<Chat, UUID> {


}
