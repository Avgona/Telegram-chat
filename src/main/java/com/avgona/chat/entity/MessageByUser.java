//package com.avgona.chat.entity.extention;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
//import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
//import org.springframework.data.cassandra.core.mapping.Table;
//
//import java.util.UUID;
//
//@Data
//@Table("messages_by_user")
//@NoArgsConstructor
//public class MessageByUser {
//
//    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
//
//    private UUID username;
//    private UUID chat_id;
//    private UUID message_id;
//    private String message_text;
//}
