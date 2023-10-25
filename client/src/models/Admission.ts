import {User} from "./User"
import {Thread} from "./Thread"


export interface Admission {
    admissionId: number
    content: string
    dateOfPost: Date
    userId: number
    threadId: number
}