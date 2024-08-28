package com.avgona.chat.repository;

import com.avgona.chat.entity.ChatByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsByUserRepository extends CassandraRepository<ChatByUser, ChatByUser.ChatByUserKey> {

    List<ChatByUser> findAllByKeyUsername(String username);

}
