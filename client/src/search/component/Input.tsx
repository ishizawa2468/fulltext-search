import { useState } from "react";
import { TextField, Button, Box, Paper, InputAdornment } from "@mui/material";
import { Search as SearchIcon } from "@mui/icons-material";
import { useNavigate, useSearch } from "@tanstack/react-router";

function Input() {
  const navigate = useNavigate({ from: "/" });
  const search = useSearch({ from: "/" });
  const [query, setQuery] = useState(search.query || "");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    navigate({
      search: { query: query.trim() },
      replace: true,
    });
  };

  const handleClear = () => {
    setQuery("");
    navigate({
      search: { query: "" },
      replace: true,
    });
  };

  return (
    <Paper elevation={2} sx={{ p: 3 }}>
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          display: "flex",
          gap: 2,
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Box sx={{ width: "90%" }}>
          <TextField
            fullWidth
            variant="outlined"
            placeholder="検索キーワードを入力..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon color="action" />
                </InputAdornment>
              ),
            }}
            sx={{ flexGrow: 1 }}
          />
        </Box>
        <Box sx={{ display: "flex", gap: 2, alignItems: "center" }}>
          <Button
            type="submit"
            variant="contained"
            size="large"
            disabled={!query.trim()}
            sx={{ minWidth: 100 }}
          >
            検索
          </Button>
          <Button
            variant="outlined"
            size="large"
            onClick={handleClear}
            sx={{ minWidth: 100 }}
          >
            クリア
          </Button>
        </Box>
      </Box>
    </Paper>
  );
}

export default Input;
