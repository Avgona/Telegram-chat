CREATE KEYSPACE IF NOT EXISTS chat
            WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE chat;


CREATE TABLE IF NOT EXISTS chat.chats
(
    chat_name   TEXT,
    description TEXT,
    created_at  DATE,
    PRIMARY KEY (chat_name)
);

CREATE TABLE IF NOT EXISTS chat.chats_by_user
(
    username    TEXT,
    chat_name   TEXT,
    description TEXT,
    joint_at    DATE,
    PRIMARY KEY ((username), chat_name)
);

CREATE TABLE IF NOT EXISTS chat.users
(
    username   TEXT,
    created_at DATE,
    PRIMARY KEY (username)
);


CREATE TABLE IF NOT EXISTS chat.messages_by_user
(
    username   TEXT,
    message_id TIMEUUID,
    chat_name  TEXT,
    content    TEXT,
    sent_at    TIMESTAMP,
    PRIMARY KEY ((username), message_id)
) WITH CLUSTERING ORDER BY (message_id DESC);


CREATE TABLE IF NOT EXISTS chat.messages_by_chat
(
    chat_name  TEXT,
    message_id TIMEUUID,
    username   TEXT,
    content    TEXT,
    sent_at    TIMESTAMP,
    PRIMARY KEY ((chat_name), message_id)
) WITH CLUSTERING ORDER BY (message_id DESC);

