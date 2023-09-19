import { useEffect, useState } from "react";
import { Grid, Paper, Divider, Avatar, TextField, Button } from "@mui/material";
import { Comment } from "../../models/Comment";

//czy dajemuy mozliwosc edycji komentarza? tylko autorowi? Jak ma to wygladac?
interface CommentsProps {
  threadId: string | undefined;
  decodedToken: any;
}

//czy pobierać ściezke zdjęcia uzytkownika fetchem czy nie? - jeśli pobierać to w bazie nie powinno być nic więcej nic id uytkownika
// i wtedy pobieramy osobno email itp
// jeśli nie pobierać, to powinno to być zawarte w danych do komentarza
const Comments: React.FC<CommentsProps> = ({ threadId, decodedToken }) => {
  const [comments, setComments] = useState<Comment[]>([]);
  const [newComment, setNewComment] = useState<string>('');

  
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

  const isUserComment = (comment: Comment) => {
    return (
      decodedToken.role === "User" && decodedToken.sub === comment.user.email
    );
  };
  const isAdmin = decodedToken.role === "Admin";

  useEffect(() => {
    fetchComments();
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
    <div>
      <div>
        <Paper style={{ padding: "40px 20px" }}>   {/* stworzyc odzielny plik css */}
          {comments.map((comment) => (
            <div key={comment.commentId}>
              <Grid container wrap="nowrap" spacing={2}>
                <Grid item>
                  <Avatar alt="Remy Sharp" src={""} />
                </Grid>
                <Grid justifyContent="left" item xs zeroMinWidth>
                  <h4 style={{ margin: 0, textAlign: "left" }}>
                    {comment.user.name}
                  </h4>
                  <h5 style={{ margin: 0, textAlign: "left" }}>
                    {comment.user.department.departmentName}
                  </h5>

                  <p style={{ textAlign: "left" }}>{comment.commentText}</p>
                  <p style={{ textAlign: "left", color: "gray" }}>
                    {comment.commentDate}
                  </p>
                  {(isUserComment(comment) || isAdmin) && (
                    <button
                      onClick={() => handleDeleteComment(comment.commentId)}
                    >
                      Remove Comment
                    </button>
                  )}
                </Grid>
              </Grid>
              <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
            </div>
          ))}
        </Paper>
      </div>
      <div>
        <TextField
          required
          id="outlined-required"
          label="Type your comment"
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
        />
        <Button
          variant="contained"
          color="primary"
          onClick={handleAddComment}
        >
          SEND
        </Button>
      </div>
    </div>
  );
};

export default Comments;
