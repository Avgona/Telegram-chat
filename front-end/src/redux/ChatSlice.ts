import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import {ChatRoom} from "../types/ChatTypes";



const chatSlice = createSlice({
    name: 'chat',
    initialState: {
        selectedChat: null as ChatRoom | null,
        chats: [] as ChatRoom[],
        searchChats: [] as ChatRoom[],
        refreshTrigger: false
    },
    reducers: {
        selectChat: (state, action: PayloadAction<ChatRoom>) => {
          state.selectedChat = action.payload
        },
        addSearchResult: (state, action: PayloadAction<ChatRoom[]>) => {
            state.searchChats = action.payload
        },
        refreshChatList: (state) => {
            state.refreshTrigger = !state.refreshTrigger;
            if (state.selectedChat && !state.selectedChat.isSubscribed)
                state.selectedChat = new ChatRoom(state.selectedChat, true)
        },
    },
});

export const {selectChat, addSearchResult, refreshChatList} = chatSlice.actions;
export default chatSlice.reducer;