import {Role} from "./Role"
import { Department } from "./Department"


export interface User {
    userId: number
    name: string
    email: string
    password: string
    role: Role
    //ma być roles: string[]
    department: Department
}