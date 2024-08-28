import React, { useEffect, useRef, useState } from 'react';
import { backend_rest, backend_ws } from "../../constants/ConstantsAPI";
import { MessageItem } from "../../types/MessageTypes";
import { Client } from "@stomp/stompjs";
import { ChatJoinRequest, ChatRoom } from "../../types/ChatTypes";
import { Fetch } from "../services/service";
import { useReduxDispatch, useReduxSelector } from "../../redux/ReduxHooks";
import { refreshChatList } from "../../redux/ChatSlice";

import './chat-area.css';
import './join-button.css';
import './messages.css';

interface Props {
    username: string
}

const ChatArea: React.FC<Props> = ({ username }) => {
    const dispatch = useReduxDispatch();
    const clientRef = useRef<Client | null>(null);
    const chat = useReduxSelector(state => state.chat.selectedChat);
    const [messages, setMessages] = useState<MessageItem[]>([]);
    const [newMessageContent, setNewMessageContent] = useState('');

    useEffect(() => {
        if (chat?.chatName) {
            fetch(`${backend_rest}/messages/getAllByChatName?chatName=${chat.chatName}`)
                .then(res => res.json())
                .then(data => setMessages(data));
            connectToChat(chat)
        }
    }, [chat]);


    const connectToChat = (chat: ChatRoom) => {
        const client = new Client({
            brokerURL: backend_ws,
            onConnect: () => {
                client.subscribe(`/chat/${chat.chatName}`, (payload) => {
                    const newMessage = JSON.parse(payload.body);
                    setMessages(prevMessages => [...prevMessages, newMessage]);
                });
            },
            onStompError: (frame) => console.log('Broker reported error: ' + frame.headers['message'])
        });
        client.activate();
        clientRef.current = client;
    }

    const handleJoinChat = (chat: ChatRoom) => {
        const request = new ChatJoinRequest(username, chat.chatName, chat.description)
        Fetch(`${backend_rest}/chats/join`, 'POST', 'Join chat', request)
            .then(() => dispatch(refreshChatList()))
    }

    const handleMessageSend = () => {
        if (!clientRef.current || !clientRef.current.active) {
            console.error('WebSocket connection is not active');
            return;
        }
        const destination = `/app/chat/${chat?.chatName}/sendMessage`;
        const message = new MessageItem(null, username, newMessageContent, new Date())
        clientRef.current.publish({destination: destination, body: JSON.stringify(message)});
        setNewMessageContent('');
    };

    const createMessageItem = (message: MessageItem) => {
        const time = new Date(message.sentAt).toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'})
        const isCurrentUser = message.author === username;

        return (
            <div className={`message-wrapper ${isCurrentUser ? 'current-user' : 'other-user'}`} key={message.messageId}>

                <div className="message-item">
                    {!isCurrentUser && <div className="message-author">{message.author}</div>}
                    <div className="message-content">{message.content}</div>
                    <div className="message-info">
                        <span className="message-time">{time}</span>
                        <span className="message-status">✓✓</span>
                    </div>
                </div>
            </div>
        );
    }

    if (!chat)
        return <div id="chat-area"></div>

    return (
        <div id="chat-area">
            <div className="chat-header">{chat.chatName}</div>
            <div className="chat-messages">
                {messages.map(message => createMessageItem(message))}
            </div>
            {chat.isSubscribed ? (
                <div className="chat-input-container">
                    <input
                        type="text"
                        value={newMessageContent}
                        onChange={(e) => setNewMessageContent(e.target.value)}
                        onKeyDown={(e) => e.key === 'Enter' && handleMessageSend()}
                        placeholder="Write a message..."
                    />
                    <button className="send-button" onClick={handleMessageSend}></button>
                </div>
            ) : (
                <button id="join-button" onClick={() => handleJoinChat(chat)}>Join Channel</button>
            )}
        </div>
    );
}

export default ChatArea;