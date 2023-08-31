import { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Thread } from '../../models/Thread';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';



export const ThreadPage: React.FC = () => {
    const { id } = useParams();
    const [thread, setThread] = useState<Thread | null>(null);
    interface Token {
        user: string;
        setUser: () => void;
    }
    const token: Token | null = useContext(UserContext);
    const decodedToken: string | null = token ? jwt_decode(token.user) : null;


    useEffect(() => {
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
        <div>
            <h1>Thread</h1>
            <p>Title: {thread.title}</p>
        </div>
    );
};

