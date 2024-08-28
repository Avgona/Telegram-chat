import React from 'react'
import ChatArea from '../chat-area/ChatArea'
import ChatList from "../left-bar/chat-list/ChatList"
import CreateChat from "../left-bar/create-chat/CreateChat";
import '../../styles/common.css'
import './main-container.css'
import Search from "../left-bar/search/Search";


interface MainContainerProps {
    username: string;
}

const MainContainer: React.FC<MainContainerProps> = ({ username }) => {

    return (
        <div id="main-part-container">
            <div id="left-bar">
                <div className="left-upper-bar">
                    <Search />
                    <CreateChat username={username} />
                </div>
                <ChatList username={username} />
            </div>
            <ChatArea username={username} />
        </div>
    );
};

export default MainContainer;