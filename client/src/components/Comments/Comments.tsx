import React, { useEffect, useState } from "react";
import { Grid, Paper, Divider, Avatar, TextField, Button, FormControl, InputLabel, Input } from "@mui/material";
import { Comment } from "../../models/Comment";
import "./Comments.css";

interface CommentsProps {
  threadId: string | undefined;
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
};

interface UserData {
  name: string;
  departmentName: string;
  email: string;
}

const Comments: React.FC<CommentsProps> = ({ threadId, decodedToken }) => {
  const [comments, setComments] = useState<Comment[]>([]);
  const [newComment, setNewComment] = useState<string>('');

  const [userDatas, setUserDatas] = useState<{ [key: number]: UserData }>({});

  const fetchUserData = async (userId: number) => {
    const apiUrl = `http://localhost:8080/users/id/${userId}`;
    try {
      const response = await fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      return {
        name: data.name,
        departmentName: data.department.departmentName,
        email: data.email,
      };
    } catch (error) {
      console.error('Error while fetching user data.', error);
      throw error;
    }
  }

  const fetchComments = async () => {

    try {
      const response = await fetch(
        `http://localhost:8080/comments/thread/${threadId}`
      );
      const data = await response.json();
      setComments(data);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
    } catch (error) {
      console.error("An error occurred while fetching data", error);
    }
  };

  const isUserComment = (comment: Comment, email: string) => {
    console.log(email)
    console.log(decodedToken.sub === email)
    return (
      decodedToken.role === "User" && decodedToken.sub === email
    );
  };

  const isAdmin = decodedToken.role === "Admin";

  useEffect(() => {
    fetchComments();
    comments.forEach((comment) => {
      fetchUserData(comment.commentAuthorId)
        .then((userData) => {
          setUserDatas((prevUserDatas) => ({
            ...prevUserDatas,
            [comment.commentAuthorId]: userData,
          }));
        })
        .catch((error) => {
          console.error('Error while fetching user data.', error);
        });
    });
  }, [threadId]);

  const handleDeleteComment = async (commentId: number) => {
    try {
      const response = await fetch(`http://localhost:8080/comments/${commentId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setComments((prevComments) =>
          prevComments.filter((comment) => comment.commentId !== commentId)
        );
      } else {
        throw new Error("Network response was not ok");
      }
    } catch (error) {
      console.error("An error occurred while deleting the comment", error);
    }
  };

  const handleAddComment = async () => {
    try {
      const dataToSend = {
        commentText: newComment,
        userId: decodedToken.user_id,
        threadId: threadId,
      };

      const response = await fetch(`http://localhost:8080/comments/addComment`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      fetchComments();

    } catch (error) {
      console.error('An error occurred while saving data', error);
    }

    setNewComment('');
  };

  return (
    <div className="comments__container">
      <div className="comment__container" style={{ padding: "40px 20px" }}>
        <Paper style={{ padding: "40px 20px" }}>
          <>
          {comments.map((comment) => {
            const userData = userDatas[comment.commentAuthorId];
            return (
              <div key={comment.commentId}>
                <Grid container wrap="nowrap" spacing={2}>
                  <Grid item>
                  </Grid>
                  <Grid justifyContent="left" item xs zeroMinWidth>
                    <h4 style={{ margin: 0, textAlign: "left" }}>
                      {userData?.name}
                    </h4>
                    <h5 style={{ margin: 0, textAlign: "left" }}>
                      {userData?.departmentName}
                    </h5>
                    <p style={{ textAlign: "left" }}>{comment.commentText}</p>
                    <p style={{ textAlign: "left", color: "gray" }}>
                      {comment.commentDate}
                    </p>
                    {(isUserComment(comment, userData?.email) || isAdmin) && (
                      <Button
                        variant="contained"
                        style={{
                          ...buttonStyles,
                        }}
                        onClick={() => handleDeleteComment(comment.commentId)}
                      >
                        Remove Comment
                      </Button>
                    )}
                  </Grid>
                </Grid>
                <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
              </div>
            );
          })}
          <div className="add-comment-form">
            <FormControl fullWidth style={{ margin: "5%" }}>
              <InputLabel htmlFor="outlined-required">Type your comment</InputLabel>
              <Input
                style={{ marginRight: "10%" }}
                required
                id="outlined-required"
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
              />
            </FormControl>
            <Button
              variant="contained"
              style={{
                ...buttonStyles,
              }}
              onClick={handleAddComment}
            >
              SEND
            </Button>
          </div>
          </>
        </Paper>
      </div>
    </div>
  );
};

export default Comments;
