import React, { useEffect, useState, useContext } from "react";
import { Category } from "../../models/Category";
import jwt_decode from "jwt-decode";
import { UserContext } from "../../context/UserContext";
import { getJwtToken } from "../../authHelpers/authHelpers";
import axios from "axios";
import {
    Box,
  Button,
  FormControl,
  Input,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";

interface Token {
  user: string;
  setUser: () => void;
}

const AddThread: React.FC = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [justification, setJustification] = useState("");
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<number | string>(0);
  const [attachment, setAttachment] = useState<File | null>(null);
  const token: Token | null = useContext(UserContext);
  const decodedToken: object | any = token ? jwt_decode(token.user) : null;

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await fetch("http://localhost:8080/categories/");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setCategories(data);
      } catch (error) {
        console.error("Error fetching categories:", error);
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
      formData.append("model", JSON.stringify(requestData));

      if (attachment) {
        formData.append("file", attachment);
      }

      const response = await axios.post(
        "http://localhost:8080/threads/addThread",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form",
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      );
      console.log(response);
    } catch (error) {
      console.error("Error adding thread:", error);
    }
  };

  return (
    <Box sx={{ display: "flex", justifyContent: "center"}}>
      <form onSubmit={handleSubmit}>
        <Box sx={{ display: "flex", flexDirection: "column"}}>
        <TextField
          sx={{ marginTop: "15px", width: "800px"}}
          id="outlined-helperText"
          label="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <TextField
          sx={{ marginTop: "15px", width: "800px" }}
          id="outlined-helperText"
          label="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <TextField
          sx={{ marginTop: "15px", marginBottom: "15px", width: "800px" }}
          id="outlined-helperText"
          label="Justification"
          value={justification}
          onChange={(e) => setJustification(e.target.value)}
        />
        </Box>
        <FormControl fullWidth>
          <InputLabel id="role-select-label">Select Category</InputLabel>
          <Select
            sx={{ height: "50px", width: "200px" }}
            labelId="category-label"
            id="category"
            value={!selectedCategory ? "" : selectedCategory.toString()}
            label="Select Category"
            onChange={(e) =>
              setSelectedCategory(parseInt(e.target.value, 10) || 0)
            }
          >
            {categories
              ? categories.map((category) => (
                  <MenuItem
                    key={category.categoryId}
                    value={category.categoryId}
                  >
                    {category.categoryName}
                  </MenuItem>
                ))
              : null}
          </Select>
        </FormControl>

        <input
          accept="image/*"
          style={{ display: "none" }}
          id="raised-button-file"
          multiple
          type="file"
          onChange={handleAttachmentChange}
        />
        <Box sx={{ display: "flex", justifyContent: "space-between" }}>
        
        <label htmlFor="raised-button-file">
          <Button sx={{ marginTop: "15px" }}
          variant="outlined" component="span">

            Upload Picture
          </Button>
        </label>

        <Button sx={{ marginTop: "15px", marginLeft: "15px"}} variant="outlined" type="submit">Submit</Button>
        </Box>
      </form>
      {attachment && (
          <div style={{ position: "absolute", top: 430, left: 320,  display: "flex", flexDirection: "column", alignItems: "center"}}>
            
          <img
            src={URL.createObjectURL(attachment)}
            alt="Attachment"
            style={{ maxWidth: "100px" }}
          /> 
          <Button variant="outlined" onClick={handleRemoveAttachment} style={{ marginTop: "8px" }}>
            Remove
          </Button>
      
        </div>
      )}
    </Box>
  );
};

export default AddThread;
