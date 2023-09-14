import React from "react";
import Autocomplete from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";

interface AutocompleteComponentProps {
  options: string[];
  value: string | null;
  onChange: (newValue: string | null) => void;
}

const AutocompleteComponent: React.FC<AutocompleteComponentProps> = ({
  options,
  value,
  onChange,
}) => {
  return (
    <Autocomplete
    
      disablePortal
      id="combo-box-demo"
      options={options}
      value={value}
      sx={{ minWidth: 300, maxWidth: 600, marginLeft: "10%", paddingTop: 1 }}
      onChange={(event, newValue) => {
        onChange(newValue);
      }}
      renderInput={(params) => <TextField {...params} label="Title" />}
    />
  );
};

export default AutocompleteComponent;