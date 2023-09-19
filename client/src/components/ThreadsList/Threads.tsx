import React, { useState, useEffect } from "react";
import { Thread } from "../../models/Thread";

import axios from "axios";
import Box from "@mui/material/Box";
import { StatusFilter } from "../Filters/StatusFilter";
import {
  Button,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import AutocompleteComponent from "../Filters/AutocompleteComponent";
import { useNavigate } from "react-router-dom";
import { getJwtToken } from "../../authHelpers/authHelpers";

export const Threads: React.FC = () => {
  interface Token {
    user: string;
    setUser: () => void;
  }

  const [threads, setThreads] = useState<Thread[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);
  const [filteredThreads, setFilteredThreads] = useState<Thread[]>([]); // zmienic na obiekt
  const [selectedThreadTitle, setSelectedThreadTitle] = useState<
    // polaczyc z filteredThreads
    string | null
  >();
  const [displayedThreads, setDisplayedThreads] = useState<Thread[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchThreads = async () => {
      try {
        const jwtToken = await getJwtToken();
        if (!jwtToken) {
          return;
        }
        const url = "http://localhost:8080/threads/";
        const response = await axios.get(url, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        });
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

  useEffect(() => {
    setIsButtonDisabled(displayedThreads === filteredThreads);
  }, [displayedThreads, filteredThreads]);

  const handleButtonClick = () => {
    setDisplayedThreads(filteredThreads);
    setSelectedThreadTitle(null);
    setIsButtonDisabled(true);
  };

  return (
    <div>
      <Box marginLeft="9%">
        <Grid container spacing={2} alignItems="left">
          <Grid item>
            <AutocompleteComponent
              options={threads.map((thread) => thread.title)}
              value={selectedThreadTitle || null}
              onChange={(newValue) => {
                setSelectedThreadTitle(newValue);
                const filtered = threads.filter(
                  (thread) => thread.title === newValue
                );
                setDisplayedThreads(filtered);
              }}
            />
          </Grid>
          <Grid item>
            <StatusFilter
              threads={threads}
              setFilteredThreads={setFilteredThreads}
              setDisplayedThreads={setDisplayedThreads}
            />
          </Grid>
          <Grid item>
            <Button
              variant="outlined"
              onClick={handleButtonClick}
              disabled={isButtonDisabled}
            >
              Reset
            </Button>
          </Grid>
        </Grid>
      </Box>
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
                {[
                  "Date",
                  "Title",
                  "Points",
                  "Category",
                  "Stage",
                  "Author",
                  "Department",
                ].map((name) => (
                  <TableCell key={name} align="right">
                    <b>{name}</b>
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {displayedThreads.map((thread) => (
                <TableRow
                  key={thread.threadId}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  onClick={() => {
                    const url = `/thread/${thread.threadId}`;
                    navigate(url);
                  }}
                >
                  <TableCell component="th" scope="row" align="right">
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
