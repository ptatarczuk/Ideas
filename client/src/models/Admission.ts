import {User} from "./User"
import {Thread} from "./Thread"


export interface Admission {
    admissionId: number
    content: string
    dateOfPost: Date
    user: User
    thread: Thread
}