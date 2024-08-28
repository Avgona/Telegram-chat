package com.avgona.chat.repository;

import com.avgona.chat.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CassandraRepository<User, String> {

    boolean existsByUsername(String username);
}
