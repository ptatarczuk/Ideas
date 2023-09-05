import {User} from "./User"
import { Category } from "./Category"
import { Stage } from "./Stage"
import { Status } from "./Status"
import { Admission } from "./Admission"
import { Conclusion } from "./Conclusion"
import {Vote} from "./Vote"


export interface Thread {
    threadId: number
    title: string
    description: string
    justification: string
    photo: string
    points: number
    user: User
    category: Category
    stage: Stage
    status: Status
    comments: Comment[]
    admission: Admission
    conclusion: Conclusion
    votes: Vote[];
}