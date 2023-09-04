import React, {useState, useEffect} from 'react'
import { Thread } from '../../models/Thread';
import { SingleThread } from "./SingleThread"


export const Threads: React.FC = () => {

    const [threads, setThreads] = useState<Thread[]>([])
    const [isLoading, setIsLoading] = useState(true)
    const [error, setError] = useState('')
    

    useEffect(() => {
        const fetchThreads = async () => {
    
            try {
                const response = await fetch('http://localhost:8080/threads/')
            
                if (!response.ok) {
                    throw new Error('Network response was not ok')
                }

                const data = await response.json()
                setThreads(data)
                setIsLoading(false)

            } catch (error) {
                setError('An error occurred while fetching data')
                setIsLoading(false)
            } finally {
                setIsLoading(false);
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

