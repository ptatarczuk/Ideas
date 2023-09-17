import React, { useEffect, useState, useContext } from 'react';
import { Category } from '../../models/Category';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';

interface Token {
    user: string;
    setUser: () => void;
}

const AddThread: React.FC = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [justification, setJustification] = useState('');
    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [attachments, setAttachments] = useState<File[]>([]);
    const token: Token | null = useContext(UserContext);
    const decodedToken: object | any = token ? jwt_decode(token.user) : null;
    const [userData, setUserData] = useState(null); 
    
    
    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/users/id/${decodedToken.user_id}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
    
                const data = await response.json();
                setUserData(data);
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };
        fetchUserData();
    }, [decodedToken.userId]);


    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch('http://localhost:8080/categories/');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                const data = await response.json();
                setCategories(data);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };
        fetchCategories();
    }, []);

    const handleAttachmentChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFiles = e.target.files;
        if (selectedFiles) {
            const filesArray = Array.from(selectedFiles);
            setAttachments(filesArray);
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const formData = new FormData();
            formData.append('title', title);
            formData.append('description', description);
            formData.append('justification', justification);
            formData.append('userEmail', decodedToken.sub);
            formData.append('categoryId', selectedCategory);
            formData.append('user', JSON.stringify(userData));

            if (attachments.length > 0) {
                for (const file of attachments) {
                    formData.append('file', file);
                }
            }

            const response = await fetch('http://localhost:8080/threads/addThread', {
                method: 'POST',
                body: formData,
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            console.log(response);
        } catch (error) {
            console.error('Error adding thread:', error);
        }
    };

    return (
        <div>
            <h1>Add your idea:</h1>
            <form onSubmit={handleSubmit}>
                <label>Title:</label>
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
                <label>Description:</label>
                <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
                <label>Justification:</label>
                <textarea value={justification} onChange={(e) => setJustification(e.target.value)} />
                <select value={selectedCategory} onChange={(e) => setSelectedCategory(e.target.value)}>
                    <option value="">Select a category</option>
                    {categories.map((category) => (
                        <option key={category.categoryId} value={category.categoryId}>
                            {category.categoryName}
                        </option>
                    ))}
                </select>
                <label>Attachments:</label>
                <input type="file" multiple onChange={handleAttachmentChange} />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddThread;
