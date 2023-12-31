import { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Thread } from '../../models/Thread';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';
import { ThreadComponent } from '../../components/Thread/Thread';
import Comments from '../../components/Comments/Comments';
import { Likes } from '../../components/Likes/Likes';
import { Conclusion } from '../../components/Opinion/Conclusion';

import './Thread.css'



export const ThreadPage: React.FC = () => {
    const { id } = useParams();
    const [thread, setThread] = useState<Thread | null>(null);
    //czy sam mechanizm dekodowania nie powinien byc gdzies przeniesiony?
    interface Token {
        user: string;
        setUser: () => void;
    }
    const [refreshThread, setRefreshThread] = useState<boolean>(false);


    const token: Token | null = useContext(UserContext);
    const decodedToken: object | any = token ? jwt_decode(token.user) : null;
    //to any zmienic    


    useEffect(() => {
        console.log(decodedToken);
        fetchThread();
    }, [id]);

    useEffect(() => {
        if (refreshThread) {
            fetchThread();
            setRefreshThread(false); // Wyłącz odświeżanie
        }
    }, [refreshThread]);


    const fetchThread = async () => {
        try {
            const response = await fetch(`http://localhost:8080/threads/id/${id}`);

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setThread(data);
        } catch (error) {
            console.error('An error occurred while fetching data', error);
        }

    };



    if (!thread) {
        return <p>Loading...</p>;
    }

    return (
        <div className='page-wrapper'>
            <div className='orange-bar'/>
            <div className='thread-container'>
                    <div className='thread-and-likes__container'>
                        <ThreadComponent thread={thread} decodedToken={decodedToken} setRefreshThread={setRefreshThread} />
                        <Likes thread={thread} fetchThread={fetchThread} />
                    </div>
                    <Comments threadId={id} decodedToken={decodedToken} />
                    <Conclusion thread={thread} decodedToken={decodedToken} fetchThread={fetchThread} />
                </div>
        </div>

    );
};

