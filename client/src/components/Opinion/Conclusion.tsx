import React, { useState, useEffect } from 'react';
import { Avatar, Grid, Divider, TextField, Button } from '@mui/material';
import { Thread } from '../../models/Thread';
import './Conclusion.css';
import { fetchUserData } from'../../api/api'

interface ConclusionProps {
    thread: Thread;
    decodedToken: any;
}

const buttonStyles = {
    minWidth: '120px',
    height: '30.5px',
    background: '#C198F0',
    opacity: 1,
    borderRadius: '15px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: '1em',
    color: '#ffffff',
    cursor: 'pointer',
    transition: 'transform 0.2s, opacity 0.2s, filter 0.2s',
    marginRight: '5%',
    marginLeft: '2.5%',
};

export const Conclusion: React.FC<ConclusionProps> = ({ thread, decodedToken }) => {
    const isAdmin = decodedToken.role === 'Admin';
    const [newConclusion, setNewConclusion] = useState<string>('');
    const [userData, setUserData] = useState<{ name: string; departmentName: string } | null>(null);

    useEffect(() => {
        const userId = thread.conclusion.userId;
        if (userId) {
            const data = fetchUserData(userId, setUserData);
        }
    }, [thread.conclusion.userId]);

    const handleAddConclusion = async (stage: string) => {
        try {
            const dataToSend = {
                content: newConclusion,
                userId: 1,
                threadId: thread.threadId,
                stageId: stage === 'APPROVED' ? 3 : 2,
            };

            const response = await fetch('http://localhost:8080/conclusion/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dataToSend),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        } catch (error) {
            console.error('An error occurred while saving data', error);
        }

        setNewConclusion('');
    };

    return (
        <div className="justification__container">
            {thread.conclusion ? (
                <div className="justification-preview" style={{ padding: '40px 20px' }}>
                    <Grid container wrap="nowrap" spacing={2}>
                        <Grid item>
                            <Avatar alt={userData?.name} src={''} />
                        </Grid>
                        <Grid justifyContent="left" item xs zeroMinWidth>
                            <h4 style={{ margin: 0, textAlign: 'left' }}>{userData?.name}</h4>
                            <h5 style={{ margin: 0, textAlign: 'left' }}>{userData?.departmentName}</h5>
                            <p style={{ textAlign: 'left' }}>{thread.conclusion.content}</p>
                            <p style={{ textAlign: 'left', color: 'gray' }}>
                                {thread.conclusion.dateOfPost.toString()}
                            </p>
                        </Grid>
                    </Grid>
                    <Divider variant="fullWidth" style={{ margin: '30px 0' }} />
                </div>
            ) : null}
            {isAdmin && !thread.conclusion ? (
                <div className="justification-edit">
                    <TextField
                        required
                        id="outlined-required"
                        label="New Conclusion"
                        value={newConclusion}
                        onChange={(e) => setNewConclusion(e.target.value)}
                    />
                    <Button
                        variant="contained"
                        style={{
                            ...buttonStyles,
                        }}
                        color="primary"
                        onClick={() => handleAddConclusion('APPROVED')}
                    >
                        APPROVED
                    </Button>
                    <Button
                        variant="contained"
                        style={{
                            ...buttonStyles,
                        }}
                        color="primary"
                        onClick={() => handleAddConclusion('NOT APPROVED')}
                    >
                        NOT APPROVED
                    </Button>
                </div>
            ) : null}
        </div>
    );
};
