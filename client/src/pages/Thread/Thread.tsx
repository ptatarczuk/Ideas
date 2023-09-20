import { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Thread } from '../../models/Thread';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';
import { ThreadComponent } from '../../components/Thread/Thread';
import Comments from '../../components/Comments/Comments';
import { Likes } from '../../components/Likes';
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


    const token: Token | null = useContext(UserContext);
    const decodedToken: object | any = token ? jwt_decode(token.user) : null;
    //to any zmienic    


    useEffect(() => {
        console.log(decodedToken);
        fetchThread();
    }, [id]);


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
       
            <div className='orange-bar'>
            <div className='thread-container'>
                <ThreadComponent thread={thread} decodedToken={decodedToken} />
                <Likes thread={thread} fetchThread={fetchThread} />
                <Comments threadId={id} decodedToken={decodedToken} />
                <Conclusion thread={thread} decodedToken={decodedToken} />
            </div>
            </div>
           
    );
};

