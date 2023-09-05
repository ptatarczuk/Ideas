import React, {useState, useEffect} from 'react'
import { Thread } from '../../models/Thread'
import { SingleThread } from "./SingleThread"
import { dbApiWithoutAuth } from '../../httpService/httpService'


export const Threads: React.FC = () => {

    const [threads, setThreads] = useState<Thread[]>([])
    const [isLoading, setIsLoading] = useState(true)
    const [error, setError] = useState('')


    useEffect(() => {
        const fetchThreads = async () => {
    
            try {
                const responseData = await dbApiWithoutAuth.get('/threads/')
                setThreads(responseData.data)
                setIsLoading(false)

            } catch (error) {
                setError('An error occurred while fetching data')
                setIsLoading(false)
            }
        }
        fetchThreads()
    }, [])

    return (

        <div>
            {isLoading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : (

                
                <table>
                    <thead>
                        <tr>
                        <th>ID</th>
                        <th>DATE</th>
                        <th>TITLE</th>
                        <th>POINTS</th>
                        <th>USER</th>
                        <th>CATEGORY</th>
                        <th>STAGE</th>
                        </tr>
                    </thead>
                    <tbody>
                        {threads.map(thread => 
                            <SingleThread key={thread.threadId} thread={thread} />
                        )}
                    </tbody>
                </table>
            )            
            }
        </div>
    )
}