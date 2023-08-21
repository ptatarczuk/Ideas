import {User} from "./User"
import {Thread} from "./Thread"


export interface Conclusion {
    conclusionId: number
    content: string
    points: number
    dateOfPost: Date
    user: User
    thread: Thread
}