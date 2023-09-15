import React, { useEffect, useState } from 'react';
import { Category } from '../../models/Category';


const Ideas: React.FC = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [justification, setJustification] = useState('');
    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [attachments, setAttachments] = useState<File[]>([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch('http://localhost:8080/categories/');
                console.log(response);
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
            const newThread = {
                title,
                description,
                justification,
                user: {
                    "userId": 1
                },
                category: { categoryId: selectedCategory },
            };

            const response = await fetch('http://localhost:8080/threads/addThread', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newThread),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            console.log(response);
            console.log(newThread);
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

export default Ideas;
