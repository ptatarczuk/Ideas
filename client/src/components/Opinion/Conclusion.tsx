import React, { useState } from 'react';
import { Avatar, Grid, Divider, Paper, TextField, Button } from '@mui/material';
import { Thread } from '../../models/Thread';

interface ConclusionProps {
    thread: Thread;
    decodedToken: any;
}

export const Conclusion: React.FC<ConclusionProps> = ({ thread, decodedToken }) => {
    const isAdmin = decodedToken.role === 'Admin';
    const [newConclusion, setNewConclusion] = useState<string>('');
    console.log(thread);
//ściezka do colclusion + zastanowić sie co ze stagem
//sprawdzić gdy nie ma conclusion
//linijka 70 sprawdzić

const handleAddConclusion = async (stage: string) => {
    try {
      const dataToSend = {
        content: newConclusion,
        userId: decodedToken.userId, 
        threadId: thread.threadId,
        stageId: stage === "APPROVED" ? 2 : 3, 
      };
  
      const response = await fetch(`http://localhost:8080/conclusion/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend),
      });
  
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
  
      // zaktualizować stan komponentu
  
    } catch (error) {
      console.error('An error occurred while saving data', error);
    }
  
    setNewConclusion('');
  };
  




    return (
        <div>
            {thread.conclusion ? (
                <Paper style={{ padding: '40px 20px' }}>
                    <h2>Conclusion:</h2>
                    <Grid container wrap="nowrap" spacing={2}>
                        <Grid item>
                            <Avatar alt={thread.conclusion.user.name} src={''} />
                        </Grid>
                        <Grid justifyContent="left" item xs zeroMinWidth>
                            <h4 style={{ margin: 0, textAlign: 'left' }}>{thread.conclusion.user.name}</h4>
                            <h5 style={{ margin: 0, textAlign: 'left' }}>{thread.conclusion.user.department.departmentName}</h5>
                            <p style={{ textAlign: 'left' }}>{thread.conclusion.content}</p>
                            <p style={{ textAlign: 'left', color: 'gray' }}>{thread.conclusion.dateOfPost}</p>
                        </Grid>
                    </Grid>
                    <Divider variant="fullWidth" style={{ margin: '30px 0' }} />
                </Paper>
            ) : null}
            {isAdmin && !thread.conclusion ? (
                <div>
                    <TextField
                        required
                        id="outlined-required"
                        label="New Conclusion"
                        value={newConclusion}
                        onChange={(e) => setNewConclusion(e.target.value)}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleAddConclusion("APPROVED")}
                    >
                        APPROVED
                    </Button>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleAddConclusion("NOT APPROVED")}
                    >
                        NOT APPROVED
                    </Button>

                </div>
            ) : null}
        </div>
    );
};
