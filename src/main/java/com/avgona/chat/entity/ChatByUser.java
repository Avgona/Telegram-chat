package com.avgona.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table("chats_by_user")
@NoArgsConstructor
public class ChatByUser {

    @PrimaryKey
    private ChatByUser.ChatByUserKey key;

    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

    @Column("joint_at")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate jointAt;


    public ChatByUser(ChatByUserKey key, String description, LocalDate jointAt) {
        this.key = key;
        this.description = description;
        this.jointAt = jointAt;
    }

    @PrimaryKeyClass
    @Data
    @NoArgsConstructor
    public static class ChatByUserKey implements Serializable {
        @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        @CassandraType(type = CassandraType.Name.TEXT)
        private String username;

        @PrimaryKeyColumn(name = "chat_name", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        @CassandraType(type = CassandraType.Name.TEXT)
        private String chatName;

        public ChatByUserKey(String username) {
            this.username = username;
        }

        public ChatByUserKey(String username, String chatName) {
            this.username = username;
            this.chatName = chatName;
        }
    }
}
