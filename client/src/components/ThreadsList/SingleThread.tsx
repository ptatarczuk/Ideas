import { Thread } from "../../models/Thread"
import { Link } from 'react-router-dom';



function formatDate(date: Date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
  
    return `${day}.${month}.${year}`;
}


/* <td>{thread.category.categoryName}</td>
<td>{thread.stage.stageName}</td>  i username*/


export const SingleThread: React.FC<{thread: Thread}> = ({thread}) => {

    return (
        <>
            <tr key={thread.threadId}>
                <td>{thread.threadId}</td>
                <td>{formatDate(new Date())}</td>
                <td><Link to={`/thread/${thread.threadId}`}>{thread.title} </Link></td>
                <td>{thread.points}</td>
                
               
            </tr>
        </>
    ) 
}
