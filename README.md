# Telegram-chat
The chat application is divided into two parts: back-end and front-end.

## Components description
The primary goal was to learn and practice WebSocket and Cassandra, while also utilizing TypeScript, React, and Redux to create a full-stack Telegram clone.
The most crucial aspects of the project are the WebSocket and Cassandra integration. There are some areas in the front-end that can be improved.
Key features include:
+ Join form - creates a new user record in the DB; otherwise, no changes occur in the DB.
+ Main page - consists of a left sidebar (create new chat, search, and chat list) and a chat area.
+ Search - allows users to find existing chats and join them.

## How to run
1) run docker-compose to create Cassandra database and supportive service, that executed necessary script
2) Run back-end in Intellij idea or your favorite IDE
3) Run front-end by running "npm install" command and then "start" command in "package.json"

#### **Technologies that were used**:
* Java 17
* Spring boot (Core, Rest, WebSocket, Cassandra-Data), Lombok
* Cassandra
* Maven, Docker
* TS, React, Redux

![Image of Maint](https://github.com/Avgona/Telegram-chat/blob/master/images/join-form.png?raw=true)

![Image of Maint](https://github.com/Avgona/Telegram-chat/blob/master/images/telegram-main-1.png?raw=true)

![Image of Maint](https://github.com/Avgona/Telegram-chat/blob/master/images/telegram-main-2.png?raw=true)

![Image of Maint](https://github.com/Avgona/Telegram-chat/blob/master/images/create-chat.png?raw=true)

![Image of Maint](https://github.com/Avgona/Telegram-chat/blob/master/images/search-and-join.png?raw=true)

