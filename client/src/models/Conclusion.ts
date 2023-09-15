import {User} from "./User"
import {Thread} from "./Thread"


export interface Conclusion {
    conclusionId: number
    content: string
    points: number
    dateOfPost: string;
    user: User
    thread: Thread
}