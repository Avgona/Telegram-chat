import React, {useState} from 'react';

import './join-form.css';
import '../../styles/common.css'
import {backend_rest} from "../../constants/ConstantsAPI";
import {Fetch} from "../services/service";


interface Props {
    onJoin: (username: string) => void;
}

const JoinForm: React.FC<Props> = ({ onJoin }) => {
    const [username, setUsername] = useState('');

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        Fetch(`${backend_rest}/users?username=${username.trim()}`,'POST', 'User logging')
        onJoin(username.trim())
    };

    return (
        <div className="join-form-container">
            <form id="username-form" onSubmit={handleSubmit}>
                <label htmlFor="username"><h2>Enter your username</h2></label>
                <input type="text" id="username" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Enter your username" required/>
                <button type="submit">Join</button>
            </form>
        </div>
    );
};

export default JoinForm;