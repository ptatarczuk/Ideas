import { useEffect, useState } from 'react';
import { Grid, Paper, Divider, Avatar } from '@mui/material'
import {Comment} from '../../models/Comment'

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
    const isUserComment = (comment: Comment) => {
        return decodedToken.role === "User" && decodedToken.sub === comment.user.email;
    }
    const isAdmin = decodedToken.role === "Admin";

    useEffect(() => {
        const fetchComments = async () => {
            try {
                const response = await fetch(`http://localhost:8080/comments/thread/${threadId}`);
                const data = await response.json();
                setComments(data);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

            } catch (error) {
                console.error('An error occurred while fetching data', error);
            }
        };
        fetchComments();
    }, [threadId])


    const handleDeleteComment = async (commentId: number) => {
        try {
            const response = await fetch(`http://localhost:8080/comments/${commentId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            setComments(prevComments => prevComments.filter(comment => comment.commentId !== commentId));
        } catch (error) {
            console.error('An error occurred while deleting the comment', error);
        }
    };



    return (
        <div>
            <Paper style={{ padding: "40px 20px" }}>
                {comments.map(comment => (
                    <div key={comment.commentId}>
                        <Grid container wrap="nowrap" spacing={2}>
                            <Grid item>
                                <Avatar alt="Remy Sharp" src={""} />
                            </Grid>
                            <Grid justifyContent="left" item xs zeroMinWidth>
                                <h4 style={{ margin: 0, textAlign: "left" }}>{comment.user.name}</h4>
                                <h5 style={{ margin: 0, textAlign: "left" }}>{comment.user.department.departmentName}</h5>

                                <p style={{ textAlign: "left" }}>
                                    {comment.commentText}
                                </p>
                                <p style={{ textAlign: "left", color: "gray" }}>
                                    {comment.commentDate}
                                </p>
                                {(isUserComment(comment) || isAdmin) && (
                                    <button onClick={() => handleDeleteComment(comment.commentId)}>Remove Comment</button>
                                )}
                            </Grid>
                        </Grid>
                        <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
                    </div>
                ))}
            </Paper>
        </div>
    );
};

export default Comments;