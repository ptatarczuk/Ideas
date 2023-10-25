import {User} from "./User"
import {Thread} from "./Thread"


export interface Comment {
    commentId: number
    commentText: string
    commentDate: string
    commentAuthorId: number,
    threadId: number
}