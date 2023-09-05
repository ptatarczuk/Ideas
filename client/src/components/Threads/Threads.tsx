import React, { useState, useEffect } from "react";
import { Thread } from "../../models/Thread";
import { SingleThread } from "./SingleThread";
import axios from "axios";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import { StatusFilter } from "../Filters/StatusFilter";
import {
  Autocomplete,
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";

// Importujemy StatusFilter

export const Threads: React.FC = () => {
  const [threads, setThreads] = useState<Thread[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");
  const [filteredThreads, setFilteredThreads] = useState<Thread[]>([]);
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);
  const [selectedThreadTitle, setSelectedThreadTitle] = useState<string | null>();
  const [displayedThreads, setDisplayedThreads] = useState<Thread[]>([])


  useEffect(() => {
    const fetchThreads = async () => {
      try {
        const url = "http://localhost:8080/threads/";
        const response = await axios.get(url);
        setThreads(response.data);
        setFilteredThreads(response.data);
        setIsLoading(false);
      } catch (error) {
        console.error("Error fetching threads:", error);
        setIsLoading(false);
      }
    };

    fetchThreads();
  }, []);

    // useEffect(() => {
    //   if (!isLoading) {
    //     setFilteredThreads(threads);
    //     setIsButtonDisabled(true);
    //   }
    // }, [threads, isLoading, isButtonDisabled]);

    useEffect(() => {
        setIsButtonDisabled(true)
        if (displayedThreads !== filteredThreads) {
        setIsButtonDisabled(false) }
    }, [displayedThreads, filteredThreads])

  const handleButtonClick = () => {
    setDisplayedThreads(filteredThreads);
    setSelectedThreadTitle(null) 
    //console.log(selectedThreadTitle);
    setIsButtonDisabled(true);
  };

  return (
    <div>
      <Autocomplete
        disablePortal
        id="combo-box-demo"
        options={threads.map((thread) => thread.title)}
        value={selectedThreadTitle}
        sx={{ width: 300 }}
        onChange={(event, newValue) => {
          setSelectedThreadTitle(newValue);
          const filtered = threads.filter(
            (thread) => thread.title === newValue
          );
          setDisplayedThreads(filtered);
        }}
        renderInput={(params) => <TextField {...params} label="Title" />}
      />

      <StatusFilter threads={threads} setFilteredThreads={setFilteredThreads} setDisplayedThreads={setDisplayedThreads}/>

<Button
        variant="outlined"
        onClick={handleButtonClick}
        disabled={isButtonDisabled}
      >
        Reset
      </Button> 

      {isLoading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>{error}</p>
      ) : (
        <TableContainer component={Paper}>
          <Table
            sx={{
              minWidth: 650,
              marginLeft: "10%",
              marginRight: "10%",
              maxWidth: "80%",
            }} // remove margins from here and add css file later
            aria-label="simple table"
          >
            <TableHead>
              <TableRow>
                <TableCell>Date</TableCell>
                <TableCell align="right">Title</TableCell>
                <TableCell align="right">Points</TableCell>
                <TableCell align="right">Category</TableCell>
                <TableCell align="right">Stage</TableCell>
                <TableCell align="right">Author</TableCell>
                <TableCell align="right">Department</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {displayedThreads.map((thread) => (
                <TableRow
                  key={thread.threadId}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {thread.date}
                  </TableCell>
                  <TableCell align="right">{thread.title}</TableCell>
                  <TableCell align="right">{thread.points}</TableCell>
                  <TableCell align="right">
                    {thread.category.categoryName}
                  </TableCell>
                  <TableCell align="right">{thread.stage.stageName}</TableCell>
                  <TableCell align="right">{thread.user.name}</TableCell>
                  <TableCell align="right">
                    {thread.user.department.departmentName}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </div>
  );
};
