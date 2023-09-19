import axios from "axios";
import { Status } from "../../models/Status";
import { Thread } from "../../models/Thread";
import React, { useState, useEffect } from "react";

import Select, { SelectChangeEvent } from "@mui/material/Select";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";

export const StatusFilter: React.FC<{
  threads: Thread[];
  setFilteredThreads: React.Dispatch<React.SetStateAction<Thread[]>>;
  setDisplayedThreads: React.Dispatch<React.SetStateAction<Thread[]>>;
}> = ({ threads, setFilteredThreads, setDisplayedThreads }) => {
  const [selectedStatus, setSelectedStatus] = useState<number | undefined>(1);
  const [statuses, setStatuses] = useState<Status[] | null>(null);

  const handleStatusChange = (event: SelectChangeEvent) => {
    setSelectedStatus(
      event.target.value ? parseInt(event.target.value) : undefined
    );
  };

  useEffect(() => {
    const fetchStatuses = async () => {
      try {
        const url = "http://localhost:8080/status/";
        const response = await axios.get(url);
        setStatuses(response.data);
      } catch (error) {
        console.error("Error fetching statuses:", error);
      }
    };

    fetchStatuses();
  }, []);

  useEffect(() => {
    if (!selectedStatus) {
      setDisplayedThreads(threads);
    } else {
      const filtered = threads.filter(
        (thread) => thread.status.statusId === selectedStatus
      );
      setDisplayedThreads(filtered);
      setFilteredThreads(filtered)
      //console.log(selectedStatus);
    }
  }, [selectedStatus, threads, setFilteredThreads, setDisplayedThreads]);

  return (
    <div>
      <Box
        sx={{ minWidth: 120, maxWidth: 200, marginLeft: "10%", paddingTop: 1 }}
      >
        <FormControl fullWidth>
          <InputLabel id="status-select-label">Status</InputLabel>
          <Select
            labelId="status-select-label"
            id="status-select"
            
            value={selectedStatus?.toString() || ""}
            label="Status"
            onChange={handleStatusChange}
          >
            {statuses
              ? statuses.map((status) => (
                  <MenuItem key={status.statusId} value={status.statusId}>
                    {status.name}
                  </MenuItem>
                ))
              : null}
          </Select>
        </FormControl>
      </Box>
    </div>
  );
};