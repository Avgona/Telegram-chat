import React, {useEffect, useState} from 'react';
import './left-bar.css'
import {Fetch} from "../../services/service";
import {backend_rest} from "../../../constants/ConstantsAPI";

import {ChatRoom} from "../../../types/ChatTypes";
import {useReduxDispatch, useReduxSelector} from "../../../redux/ReduxHooks";
import {selectChat} from "../../../redux/ChatSlice";


interface Props {
    username: string;
}

const colors = ['#FF6B6B', '#5FAD56', '#F06292', '#4169E1', '#4B0082', '#6A5ACD', '#6495ED', '#9370DB']
const EMPTY_ARRAY: any[] = []

const ChatList: React.FC<Props> = ({username}) => {
    const dispatch = useReduxDispatch();
    const refreshChatList = useReduxSelector(state => state.chat.refreshTrigger);
    const searchResults = useReduxSelector(state => state.chat.searchChats);
    const [chats, setChats] = useState<ChatRoom[]>(EMPTY_ARRAY);

    useEffect(() => fetchChats(), [refreshChatList]);
    useEffect(() => setChats(searchResults),[searchResults]);


    const fetchChats = () => {
        Fetch(`${backend_rest}/chats?username=${username}`, 'GET', 'Fetch chats')
            .then(it => it.json())
            .then(chats  => {
                 chats = chats.map((one: ChatRoom) => ({
                    ...one,
                    isSubscribed: true,
                }));
                setChats(chats);
            })
            .catch(it => console.error('Failed to fetch chats:', it.text()))
    }


    const ChatRoomItem = (chat: ChatRoom) => {
        const backgroundColor = {backgroundColor: colors[(chat.chatName.charCodeAt(0) + chat.chatName.charCodeAt(2)) % colors.length]}
        return (
            <div className="chat-room-item" key={chat.chatName} title={chat.description} onClick={() => dispatch(selectChat(chat))}>
                <div className="chat-avatar" style={backgroundColor}>{chat.chatName[0]}</div>
                <div className="chat-info">
                    <div className="chat-name">{chat.chatName}</div>
                </div>
            </div>
        );
    };

    return (
        <div className="chat-list">
            {chats.map(chat => ChatRoomItem(chat))}
        </div>
    );
};

export default ChatList;