package com.avgona.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("chats")
@NoArgsConstructor
public class Chat {

    @PrimaryKeyColumn(name = "chat_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String chatName;

    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

    @Column("created_at")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate createAt;

    public Chat(String chatName, String description, LocalDate createAt) {
        this.chatName = chatName;
        this.description = description;
        this.createAt = createAt;
    }
}
