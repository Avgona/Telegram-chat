package com.avgona.chat.service;

import com.avgona.chat.entity.User;
import com.avgona.chat.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repository;

    @Override
    public void create(String username) {
        if (repository.existsByUsername(username))
            return;


        var entity = new User(username, LocalDate.now());
        repository.save(entity);
    }
}
