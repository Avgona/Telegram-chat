package com.avgona.chat.entity;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;


@Data
@Table("messages_by_chat")
@NoArgsConstructor
public class MessageByChat {

    @PrimaryKey
    private MessageByChatKey key;

    @Column("username")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String username;

    @Column("content")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String content;

    @Column("sent_at")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Instant sentAt;

    public MessageByChat(String chatName, UUID messageId, String username, String content, LocalDateTime sentAt) {
        this.key = new MessageByChatKey(chatName, messageId);
        this.username = username;
        this.content = content;
        this.sentAt = sentAt.atZone(ZoneId.systemDefault()).toInstant();
    }

    @PrimaryKeyClass
    @Data
    @NoArgsConstructor
    public static class MessageByChatKey implements Serializable {

        @PrimaryKeyColumn(name = "chat_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        @CassandraType(type = CassandraType.Name.TEXT)
        private String chatName;

        @PrimaryKeyColumn(name = "message_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        @CassandraType(type = CassandraType.Name.TIMEUUID)
        private UUID messageId;


        public MessageByChatKey(String chatName, UUID messageId) {
            this.chatName = chatName;
            this.messageId = messageId;
        }
    }
}
