import { Thread } from "../../models/Thread"


function formatDate(date: Date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
  
    return `${day}.${month}.${year}`;
}



export const SingleThread: React.FC<{thread: Thread}> = ({thread}) => {

    return (
        <>
            <tr key={thread.threadId}>
                <td>{thread.threadId}</td>
                <td>{formatDate(new Date())}</td>
                <td>{thread.title}</td>
                <td>{thread.points}</td>
                <td>{thread.user.name}</td>
                <td>{thread.category.categoryName}</td>
                <td>{thread.stage.stageName}</td>
            </tr>
        </>
    ) 
}
