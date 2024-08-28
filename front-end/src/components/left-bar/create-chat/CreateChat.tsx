import React, {useState} from 'react';
import './create-chat.css'

import {backend_rest} from "../../../constants/ConstantsAPI";
import {Fetch} from "../../services/service";
import {useReduxDispatch} from "../../../redux/ReduxHooks";
import {refreshChatList} from "../../../redux/ChatSlice";


class ChatRoom {
    username: string
    chatName: string;
    description: string;

    constructor(username: string, chatName: string, description: string) {
        this.username = username
        this.chatName = chatName;
        this.description = description;
    }
}

interface Props {
    username: string;
}

const CreateChat: React.FC<Props> = ({username }) => {
    const dispatch = useReduxDispatch();
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [chatName, setChatName] = useState('');
    const [chatDescription, setChatDescription] = useState('');


    const handleCreateChat = async (e: React.FormEvent) => {
        e.preventDefault();
        const result = await Fetch(`${backend_rest}/chats`, 'POST', 'Chat creation', new ChatRoom(username, chatName, chatDescription))
        if (result.ok) {
            setShowCreateModal(false);
            setChatName('');
            setChatDescription('');
            dispatch(refreshChatList());
        }
    };


    return (
        <>
            <div className="create-chat-container" onClick={() => setShowCreateModal(true)}>Create a new chat</div>
            {/* Create Chat Modal */}
            {showCreateModal && (
                <div className="modal">
                    <div className="modal-content">
                        <span className="close" onClick={() => setShowCreateModal(false)}>&times;</span>
                        <h2>Create a New Chat</h2>
                        <form id="create-chat-form" onSubmit={handleCreateChat}>
                            <input type="text" value={chatName} onChange={(e) => setChatName(e.target.value)} placeholder="Enter chat name" required/>
                            <input type="text" value={chatDescription} onChange={(e) => setChatDescription(e.target.value)} placeholder="Enter chat description"/>
                            <button type="submit">Create</button>
                        </form>
                    </div>
                </div>
            )}
        </>
    );
};

export default CreateChat;