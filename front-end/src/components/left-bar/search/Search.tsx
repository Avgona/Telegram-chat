import React, {useEffect, useState} from 'react';
import './search.css'
import {debounce} from 'lodash';
import {backend_rest} from "../../../constants/ConstantsAPI";
import {useReduxDispatch} from "../../../redux/ReduxHooks";
import {addSearchResult} from "../../../redux/ChatSlice";



const Search: React.FC = () => {
    const dispatch = useReduxDispatch();
    const [searchTerm, setSearchTerm] = useState('');


    useEffect(() => {
        const debounceCall = debounce(() => {
            if (searchTerm.length >= 3) {
                fetch(`${backend_rest}/chats/search?searchTerm=${searchTerm}`)
                    .then(res => res.json())
                    .then(data => dispatch(addSearchResult(data)))
                    .catch(error => console.error('Search error:', error));
            }
        }, 750);
        debounceCall()
        return () => {debounceCall.cancel()}
    }, [searchTerm]);


    return (
        <div className="search-container">
            <div className="search-icon"></div>
            <input type="text" placeholder="Search" value={searchTerm} onChange={(e) => setSearchTerm(e.currentTarget.value)}/>
        </div>
    );
};

export default Search;