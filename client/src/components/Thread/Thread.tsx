import React, { useState, useEffect } from 'react';
import { Button, Box, FormControl, InputLabel, Input } from '@mui/material';
import { Thread } from '../../models/Thread';
import { getJwtToken } from '../../authHelpers/authHelpers';
import axios from 'axios';
import './Thread.css';

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
};

const hoverStyles = {
  transform: 'scale(1.1)',
  opacity: 0.8,
};

interface ThreadComponentProps {
  thread: Thread;
  decodedToken: any;
  setRefreshThread: (refresh: boolean) => void;
}

export const ThreadComponent: React.FC<ThreadComponentProps> = ({
  thread,
  decodedToken,
  setRefreshThread,
}) => {
  const [editedThread, setEditedThread] = useState<Thread>({ ...thread });
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const [buttonText, setButtonText] = useState<string>('EDIT');
  const isAuthor = editedThread.user.email === decodedToken.sub;
  const [newImage, setNewImage] = useState<File | null>(null);
  const [image, setImage] = useState<string>(
    'https://blogit.itu.dk/lucarossi/wp-content/uploads/sites/80/2019/09/1_ATVm5uCixG7ntr40XlQbrg.jpeg'
  );
  const isUser = decodedToken.role === 'User';
  const isAdmin = decodedToken.role === 'Admin';

  useEffect(() => {
    const fetchImage = async () => {
      try {
        if (thread.photo) {
          const response = await fetch(`http://localhost:8080${thread.photo}`);
          const blob = await response.blob();
          const blobUrl = window.URL.createObjectURL(blob);
          setImage(blobUrl);
        }
      } catch (error) {
        console.error('An error occurred while fetching data', error);
      }
    };

    fetchImage();
  }, [thread]);

  const handleFieldChange = (field: keyof Thread, value: any) => {
    setEditedThread((prevThread) => ({
      ...prevThread,
      [field]: value,
    }));
  };

  const handleEdit = () => {
    setIsEditing(true);
    setButtonText('SAVE');
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

      const response = await axios.patch(
        `http://localhost:8080/threads/id/${thread.threadId}`,
        formData,
        config
      );

      if (response.status !== 200) {
        const errorData = response.data;
        throw new Error(`Network response was not ok. Error data: ${JSON.stringify(errorData)}`);
      }

      setIsEditing(false);
      setButtonText('EDIT');

      setRefreshThread(true);
    } catch (error) {
      console.error('An error occurred while saving data', error);
    }
  };

  return (
    <Box
      component="form"
      sx={{
        '& .MuiFormControl-root': { m: 1, width: '100%' },
      }}
      noValidate
      autoComplete="off"
    >
      <div className="title__container">
        {(isUser && isAuthor) || isAdmin ? (
          <Button
            variant="contained"
            style={{
              ...buttonStyles,
              ...(buttonText === 'EDIT' && !isEditing ? {} : hoverStyles),
            }}
            onClick={buttonText === 'EDIT' ? handleEdit : handleSave}
          >
            {buttonText}
          </Button>
        ) : null}
        <FormControl fullWidth sx={{ m: 1 }}>
          <InputLabel htmlFor="outlined-title">Title</InputLabel>
          <Input
            sx={{
              "& .MuiInputBase-input.Mui-disabled": {
                WebkitTextFillColor: "#000000",
              },
            }}
            required
            id="outlined-title"
            value={editedThread.title}
            onChange={(e) => handleFieldChange('title', e.target.value)}
            disabled={!isEditing}
          />
        </FormControl>
      </div>
      <div className="thread-information__container">
        <div className="image__container" >
          {/* {ma być image, src tylko testowo} */}
          {<img
            src={image} alt="Thread Photo" />}
          {isEditing && (
            <div>
              <input type="file" onChange={handleImageChange} style={{ maxWidth: '1000px' }} />
              {newImage && <button onClick={handleRemoveImage}>Remove Image</button>}
                </div>
              )}
            </div>
            <div className="thread-details__container">
              <FormControl fullWidth sx={{ m: 0.85 }}>
                <InputLabel htmlFor="outlined-author">Author</InputLabel>
                <Input required id="outlined-author" disabled={true} value={editedThread.user.name} />
              </FormControl>
              <FormControl fullWidth sx={{ m: 0.85  }}>
                <InputLabel htmlFor="outlined-department">Departament</InputLabel>
                <Input
                  required
                  id="outlined-department"
                  disabled={true}
                  value={editedThread.user.department.departmentName}
                />
              </FormControl>
              <FormControl fullWidth sx={{ m: 0.85  }}>
                <InputLabel htmlFor="outlined-category">Category</InputLabel>
                <Input required id="outlined-category" disabled={true} value={editedThread.category.categoryName} />
              </FormControl>
              <FormControl fullWidth sx={{ m: 0.85  }}>
                <InputLabel htmlFor="outlined-stage">Stage</InputLabel>
                <Input required id="outlined-stage" disabled={true} value={editedThread.stage.stageName} />
              </FormControl>
              <FormControl fullWidth sx={{ m: 0.85  }}>
                <InputLabel htmlFor="outlined-status">Status</InputLabel>
                <Input required id="outlined-status" disabled={true} value={editedThread.status.name} />
              </FormControl>
        </div>
      </div>
      <div className="description__container">
        <FormControl fullWidth sx={{ m: 0.85 }}>
          <InputLabel htmlFor="outlined-description">Description</InputLabel>
          <Input
            required
            id="outlined-description"
            value={editedThread.description}
            onChange={(e) => handleFieldChange('description', e.target.value)}
            disabled={!isEditing}
          />
        </FormControl>
        <FormControl fullWidth sx={{ m: 0.85 }}>
          <InputLabel htmlFor="outlined-justification">Justification</InputLabel>
          <Input
            required
            id="outlined-justification"
            value={editedThread.justification}
            onChange={(e) => handleFieldChange('justification', e.target.value)}
            disabled={!isEditing}
          />
        </FormControl>
      </div>
    </Box>
  );
};