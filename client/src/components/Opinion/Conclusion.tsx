import { Thread } from '../../models/Thread';


interface ConclusionProps {
    thread: Thread;
    decodedToken: any;
}


export const Conclusion: React.FC<ConclusionProps> = ({ thread, decodedToken }) => {
    const isUser = decodedToken.role === "User";
    const isAdmin = decodedToken.role === "Admin";

    
    return (
        <div>
          {thread.conclusion ? (
            // Wyświetl istniejące konkluzje
            <div>
              <h2>Conclusion:</h2>
              <ul>
                <h1>{thread.conclusion.content}</h1>
              </ul>
            </div>
          ) : null}
          {isAdmin ? (
            <div>
              <button>Add Conclusion:</button>
        </div>): null}
        </div>
      );
    };
