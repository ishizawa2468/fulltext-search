import { Container, Typography, Box } from "@mui/material";
import Input from "../search/component/Input";
import Table from "../search/component/Table";
import { useSearch } from "@tanstack/react-router";

function SearchPage() {
  const search = useSearch({ from: "/" });

  return (
    <Container maxWidth="sm" sx={{ py: 4 }}>
      <Typography variant="h3" component="h1" gutterBottom align="center">
        Full Text Search
      </Typography>
      <Box sx={{ mb: 4 }}>
        <Input />
      </Box>
      <Table query={search.query || ""} />
    </Container>
  );
}

export default SearchPage;
