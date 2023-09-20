import { Thread } from '../../models/Thread';
import { useEffect, useState } from 'react';
import { Vote } from '../../models/Vote';
import './Likes.css';


interface LikesProps {
    thread: Thread;
    fetchThread: () => void;
}

export const Likes: React.FC<LikesProps> = ({ thread, fetchThread }) => {
    const [points, setPoints] = useState(0);

    let totalPoints: number = 0;

    useEffect(() => {
        for (const vote of thread.votes) {
            if (vote.voteType === "LIKE") {
                totalPoints++;
                console.log(totalPoints);
            } else if (vote.voteType === "DISLIKE") {
                totalPoints--;
            }
        }
        setPoints(totalPoints);
    }, [thread])


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
                    //POPRAWIĆ TO
                    userId: thread.user.userId,
                    voteType: 'LIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }
            fetchThread();

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
                    userId: thread.user.userId,
                    voteType: 'DISLIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }
            fetchThread();

        } catch (error: any) {
            console.error('Error:', error.message);
        }
    };

    return (<div>
        <button className='add-like__button' onClick={handleAddLike}>+</button>
        <h1>{points}</h1>
        <button className='add-dislike__button' onClick={handleAddDislike}>-</button>
    </div>);
}