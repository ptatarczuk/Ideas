import { TextField, Button, Box } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { Thread } from '../../models/Thread';
import { getJwtToken } from '../../authHelpers/authHelpers'
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
    const [newImage, setNewImage] = useState<File | null>(null);
    const [image, setImage] = useState<string>("");
    const isUser = decodedToken.role === "User";
    const isAdmin = decodedToken.role === "Admin";
//


useEffect(() => {
    const fetchData = async () => {
        try {
            const response = await axios.get(`http://localhost:8080${thread.photo}`);
            const blob = new Blob([response.data], { type: 'image/png' }); 
            const blobUrl = URL.createObjectURL(blob);
            setImage(blobUrl);
            console.log(blobUrl)
        } catch (error) {
            console.error('An error occurred while fetching data', error);
        }
    };

    fetchData();
}, [thread]);


    const handleFieldChange = (field: keyof Thread, value: any) => {
        setEditedThread((prevThread) => ({
            ...prevThread,
            [field]: value,
        }));
    };

    const handleEdit = () => {
        console.log(image)
        setIsEditing(true);
        setButtonText("SAVE");
        console.log(thread)
    };

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFile = e.target.files?.[0];
        if (selectedFile) {
            setNewImage(selectedFile);
        }
    };

    const handleRemoveImage = () => {
        setNewImage(null);
    };



    const handleSave = async (e: React.FormEvent) => {
        console.log(image)
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

            if (newImage) {
                formData.append('file', newImage);
            }

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
            {<img src={image} alt="Thread Photo" />}
            {isEditing && (
                <div>
                    <input type="file" onChange={handleImageChange} style={{ maxWidth: '1000px' }} />
                    <button onClick={handleRemoveImage}>Remove Image</button>
                </div>
            )}
            <div>
            </div>

        </Box>
    );
};
