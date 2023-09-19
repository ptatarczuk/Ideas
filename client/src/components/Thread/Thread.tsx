import { TextField, Button, Box } from '@mui/material';
import React, { useState } from 'react';
import { Thread } from '../../models/Thread';
import { getJwtToken } from '../../authHelpers/authHelpers'
import { Description } from '@mui/icons-material';
import axios from 'axios';


interface ThreadComponentProps {
    thread: Thread;
    decodedToken: any;
}

export const ThreadComponent: React.FC<ThreadComponentProps> = ({ thread, decodedToken }) => {
    const [editedThread, setEditedThread] = useState<Thread>({ ...thread });
    const [isEditing, setIsEditing] = useState<boolean>(false);
    const [buttonText, setButtonText] = useState<string>("EDIT");
    const isAuthor = editedThread.user.email === decodedToken.sub;
    const isUser = decodedToken.role === "User";
    const isAdmin = decodedToken.role === "Admin";


    const handleFieldChange = (field: keyof Thread, value: any) => {
        setEditedThread((prevThread) => ({
            ...prevThread,
            [field]: value,
        }));
    };

    const handleEdit = () => {
        setIsEditing(true);
        setButtonText("SAVE");
    };



    const handleSave = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const jwtToken = await getJwtToken();
            
            if (!jwtToken) {
                return;
            }
    
            const updatedThread = {
                title: editedThread.title,
                description: editedThread.description,
                justification: editedThread.justification,
            };
    
            const formData = new FormData();
            formData.append('thread', JSON.stringify(updatedThread));
           
    
            const config = {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    Authorization: `Bearer ${jwtToken}`,
                },
            };
    
            const response = await axios.patch(`http://localhost:8080/threads/id/${thread.threadId}`, formData, config);
    
            if (response.status !== 200) {
                const errorData = response.data;
                throw new Error(`Network response was not ok. Error data: ${JSON.stringify(errorData)}`);
            }
    
            setIsEditing(false);
            setButtonText("EDIT");
        } catch (error) {
            console.error('An error occurred while saving data', error);
        }
    };
    

    return (
        <Box
            component="form"
            sx={{
                '& .MuiTextField-root': { m: 1, width: '25ch' },
            }}
            noValidate
            autoComplete="off"
        >
            <h1>Thread</h1>
            {(isUser && isAuthor) || isAdmin ? (
                <Button variant="contained" onClick={buttonText === "EDIT" ? handleEdit : handleSave}>
                    {buttonText}
                </Button>
            ) : null}
            <TextField
                required
                id="outlined-required"
                label="Title"
                value={editedThread.title}
                onChange={(e) => handleFieldChange('title', e.target.value)}
                disabled={!isEditing}
            />
            <TextField
                required
                id="outlined-required"
                label="Author"
                disabled={true}
                value={editedThread.user.name}
            />
            <img src={editedThread.photo} alt="Thread Photo" />
            <TextField
                required
                id="outlined-required"
                label="Description"
                value={editedThread.description}
                onChange={(e) => handleFieldChange('description', e.target.value)}
                disabled={!isEditing}
            />
            <TextField
                required
                id="outlined-required"
                label="Justification"
                value={editedThread.justification}
                onChange={(e) => handleFieldChange('justification', e.target.value)}
                disabled={!isEditing}
            />
        </Box>
    );
};
