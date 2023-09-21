import { Thread } from '../../models/Thread';
import { useContext, useEffect, useState } from 'react';
import { Vote } from '../../models/Vote';
import './Likes.css';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';

interface Token {
    user: string;
    setUser: () => void;
}

interface LikesProps {
    thread: Thread;
    fetchThread: () => void;
}

export const Likes: React.FC<LikesProps> = ({ thread, fetchThread }) => {
    const [points, setPoints] = useState(0);
    const token: Token | null = useContext(UserContext);
    const decodedToken: object | any = token ? jwt_decode(token.user) : null;

    let totalPoints: number = 0;

    useEffect(() => {
        for (const vote of thread.votes) {
            if (vote.voteType === "LIKE") {
                totalPoints++;
                console.log("LIKE" +totalPoints);
            } else if (vote.voteType === "DISLIKE") {
                totalPoints--;
                console.log("DISLIKE" +totalPoints);
            }
        }
        setPoints(totalPoints);
    }, [thread, points])


    const handleAddLike = async () => {
        try {
            const jwtToken = localStorage.getItem('token');
            const response = await fetch('http://localhost:8080/votes/addVote', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    threadId: thread.threadId,
                    userId: decodedToken.user_id,
                    voteType: 'LIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }
            fetchThread();
            setPoints(points + 100 ) //tutaj wystarczy zaktualizowac punkty, nie wazne jaka wartosc,  potem to trzeba naprawic !
        } catch (error: any) {
            console.error('Error:', error.message);
        }
    };


    const handleAddDislike = async () => {
        try {
            const response = await fetch('http://localhost:8080/votes/addVote', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    threadId: thread.threadId,
                    userId: decodedToken.user_id,
                    voteType: 'DISLIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }
            fetchThread();
            setPoints(points - 1 )

        } catch (error: any) {
            console.error('Error:', error.message);
        }
    };

    return (
    <div className='likes__container'>
        <button className='add-like__button' onClick={handleAddLike}>+</button>
        <h1>{points}</h1>
        <button className='add-dislike__button' onClick={handleAddDislike}>-</button>
    </div>);
}