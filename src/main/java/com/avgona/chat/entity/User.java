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
@Table("users")
@NoArgsConstructor
public class User {


    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String username;

    @Column("created_at")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate createdAt;


    public User(String username, LocalDate createdAt) {
        this.username = username;
        this.createdAt = createdAt;
    }
}
