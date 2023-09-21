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
  TextField,
} from "@mui/material";
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

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
  // const [filteredThreads, setFilteredThreads] = useState<Thread[]>([]); // zmienic na obiekt
  const [searchedThreadTitle, setSearchedThreadTitle] = useState<string | null>("");
  const [selectedStatus, setSelectedStatus] = useState<number | null>(1);
  const [page, setPage] = React.useState(1);
  const [totalPages, setTotalPages] = useState<number>(2);
  const [rowsNumber, setRowsNumber] = useState<number>(3);
  // const [displayedThreads, setDisplayedThreads] = useState<Thread[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    setIsLoading(true);
    const fetchThreads = async () => {
      try {
        const jwtToken = await getJwtToken();
        if (!jwtToken) {
          console.error("No token available")
          return;
        }
        const url = "http://localhost:8080/threads/";
        const queryParams = {
          pageNo: page-1,
          pageSize: rowsNumber,
          searchedTitle: searchedThreadTitle,
          filterStatusId: selectedStatus,
        };
        const response = await axios.get(url, {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
          params: queryParams,
        });
        setTotalPages(response.data.totalPages);
        setThreads(response.data.threads);
        setIsLoading(false);
      } catch (error) {
        console.error("Error fetching threads:", error);
        setIsLoading(false);
      }
    };

    fetchThreads();
  }, [page, searchedThreadTitle, selectedStatus]);

  useEffect(() => {
    if(searchedThreadTitle !== "" || selectedStatus !== 1) {
      setIsButtonDisabled(false);
    } 
    if((searchedThreadTitle === "") && selectedStatus === 1) {
      setIsButtonDisabled(true);
    }
      
  }, [searchedThreadTitle, selectedStatus]);

  const handleButtonClick = () => {
    setSearchedThreadTitle("");
    setSelectedStatus(1);
    setIsButtonDisabled(true);
  };

  const handleChange = (event: React.ChangeEvent<unknown>, value: number) => {
    setPage(value);
  };

  return (
    <>
    <Box marginLeft="13%" marginRight="11%">
      <Box>
        <Grid container spacing={2} alignItems="left">
          <Grid item>
        <TextField
          id="outlined-helperText"
          label="Helper text"
          value={searchedThreadTitle}
          onChange={(e)=>setSearchedThreadTitle(e.target.value)}
        />
          </Grid>
          <Grid item>
            <StatusFilter
              selectedStatus={selectedStatus}
              setSelectedStatus={setSelectedStatus}
            />
          </Grid>
          <Grid item>
            <Button sx={{ marginTop: '8px', height: '55px' }}
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
        <p>Cant load resources...</p>
      ) : error ? (
        <p>{error}</p>
      ) : (
        <TableContainer component={Paper}>
          <Table
            sx={{
              minWidth: 800,
              marginRight: "10%",
              maxWidth: "100%",
              "& .table-cell-date": { width: "5%" },
              "& .table-cell-title": { width: "20%" }, 
              "& .table-cell-points": { width: "5%" }, 
              "& .table-cell-category": { width: "10%" }, 
              "& .table-cell-stage": { width: "8%" }, 
              "& .table-cell-author": { width: "8%" }, 
              "& .table-cell-department": { width: "15%" },
            }} 
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
                  <TableCell key={name} align="left" className={`table-cell-${name.toLowerCase()}`}>
                    <b>{name}</b>
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {threads.map((thread) => (
                <TableRow
                  key={thread.threadId}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  onClick={() => {
                    const url = `/thread/${thread.threadId}`;
                    navigate(url);
                  }}
                >
                  <TableCell component="th" scope="row" align="left">
                    {thread.date}
                  </TableCell>
                  <TableCell align="left">{thread.title}</TableCell>
                  <TableCell align="left">{thread.points}</TableCell>
                  <TableCell align="left">
                    {thread.category.categoryName}
                  </TableCell>
                  <TableCell align="left">{thread.stage.stageName}</TableCell>
                  <TableCell align="left">{thread.user.name}</TableCell>
                  <TableCell align="left">
                    {thread.user.department.departmentName}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
      
      <Stack spacing={2} style={{ marginTop: "20px", float: "right" }} >
        <Pagination count={totalPages} page={page} onChange={handleChange} />
    </Stack>
    
    </Box>
    </>
  );
};
