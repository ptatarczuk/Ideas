import React, { useEffect, useState, useContext } from 'react';
import { Category } from '../../models/Category';
import jwt_decode from 'jwt-decode';
import { UserContext } from '../../context/UserContext';
import { getJwtToken } from '../../authHelpers/authHelpers'
import axios from 'axios';

interface Token {
    user: string;
    setUser: () => void;
}

const AddThread: React.FC = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [justification, setJustification] = useState('');
    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<number | "">(0);
    const [attachment, setAttachment] = useState<File | null>(null);
    const token: Token | null = useContext(UserContext);
    const decodedToken: object | any = token ? jwt_decode(token.user) : null;

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
        const selectedFile = e.target.files?.[0];
        if (selectedFile) {
            setAttachment(selectedFile);
        }
    };

    const handleRemoveAttachment = () => {
        setAttachment(null);
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const jwtToken = await getJwtToken();
            if (!jwtToken) {
                return;
            }
            const requestData = {
                title: title,
                description: description,
                justification: justification,
                userEmail: decodedToken.sub,
                categoryId: parseInt(selectedCategory.toString()),
            };

            const formData = new FormData();
            formData.append('model', JSON.stringify(requestData));

            if (attachment) {
                formData.append('file', attachment);
            }

            const response = await axios.post(
                'http://localhost:8080/threads/addThread',
                formData,
                {
                    headers: {
                        'Content-Type': 'multipart/fo
                        Authorization: `Bearer ${jwtToken}`,
                    },
                }
            );
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
                <select value={selectedCategory === "" ? "" : selectedCategory.toString()} onChange={(e) => setSelectedCategory(parseInt(e.target.value, 10) || 0)}>
                    <option value="">Select a category</option>
                    {categories.map((category) => (
                        <option key={category.categoryId} value={category.categoryId}>
                            {category.categoryName}
                        </option>
                    ))}
                </select>
                <label>Attachment:</label>
                <input type="file" onChange={handleAttachmentChange} />
                {attachment && (
                    <div>
                        <img src={URL.createObjectURL(attachment)} alt="Attachment" style={{ maxWidth: '100px' }} />
                        <button onClick={handleRemoveAttachment}>Remove</button>
                    </div>
                )}
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddThread;
