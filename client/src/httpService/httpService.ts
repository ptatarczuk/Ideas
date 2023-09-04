import axios from 'axios'
import { ACCESS_TOKEN } from '../components/constants'


export const dbApiWithAuth = axios.create( {
    baseURL: 'http://localhost:8080'
})

dbApiWithAuth.defaults.headers.common['Authorization'] = `Bearer ${ACCESS_TOKEN}`

export const dbApiWithoutAuth = axios.create ( {
    baseURL: 'http://localhost:8080'
})