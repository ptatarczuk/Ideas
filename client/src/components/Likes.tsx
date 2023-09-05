import { Thread } from '../models/Thread';
import { useState } from 'react';
import { Vote } from '../models/Vote';


interface LikesProps {
    thread: Thread;
}

export const Likes: React.FC<LikesProps> = ({ thread }) => {
    const totalPoints = thread.votes.reduce((accumulator: number, vote: Vote) => {
        if (vote.voteType === 'LIKE') {
            return accumulator + 1;
        } else if (vote.voteType === 'DISLIKE') {
            return accumulator - 1;
        }
        return accumulator;
    }, 0);

    const handleAddLike = async () => {
        try {
            const response = await fetch('/votes/addVote', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    threadId: thread.threadId,
                    userId: 1,
                    voteType: 'LIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }

            const data = await response.json();

        } catch (error: any) {
            console.error('Error:', error.message);
        }
    };


    const handleAddDislike = async () => {
        try {
            const response = await fetch('/votes/addVote', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    threadId: thread.threadId,
                    userId: 1,
                    voteType: 'DISLIKE',
                }),
            });

            if (!response.ok) {
                throw new Error('Error while adding DISLIKE vote');
            }

            const data = await response.json();

        } catch (error: any) {
            console.error('Error:', error.message);
        }
    };





    return (<div>
        <button onClick={handleAddLike}>+</button>
        <h1>{totalPoints}</h1>
        <button onClick={handleAddDislike}>-</button>
    </div>);
}