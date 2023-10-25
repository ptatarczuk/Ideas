import {User} from "./User"
import {Thread} from "./Thread"


export interface Conclusion {
    conclusionId: number;
    content: string;
    dateOfPost: string;
    userId: number; 
    threadId: number; 
}
