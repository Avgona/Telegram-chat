import React, { useState } from 'react';
import JoinForm from './components/join-form/JoinForm';
import MainContainer from './components/main-container/MainContainer';
import './styles/common.css'

const App: React.FC = () => {
    const [username, setUsername] = useState<string | null>(null);

    const handleJoin = (username: string) => {
        setUsername(username);
    };

    return (
        <>
            {!username && <JoinForm onJoin={handleJoin} />}
            {username && <MainContainer username={username} />}
        </>
    );
};

export default App;
