import {
  Table as MuiTable,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Box,
  CircularProgress,
  Alert,
  Chip,
} from '@mui/material'
import { useSearch } from '../../api/backend'

interface TableProps {
  query: string
}

function Table({ query }: TableProps) {
  const { data: searchResults, isLoading, error } = useSearch(
    { query },
    {
      query: {
        enabled: !!query.trim(),
        staleTime: 1000 * 60 * 5, // 5分間キャッシュ
      }
    }
  )

  if (!query.trim()) {
    return (
      <Paper elevation={1} sx={{ p: 4, textAlign: 'center' }}>
        <Typography variant="h6" color="text.secondary">
          検索キーワードを入力してください
        </Typography>
      </Paper>
    )
  }

  if (isLoading) {
    return (
      <Paper elevation={1} sx={{ p: 4, textAlign: 'center' }}>
        <CircularProgress size={40} />
        <Typography variant="body1" sx={{ mt: 2 }}>
          検索中...
        </Typography>
      </Paper>
    )
  }

  if (error) {
    return (
      <Alert severity="error" sx={{ mb: 2 }}>
        エラーが発生しました: {error.message}
      </Alert>
    )
  }

  if (!searchResults?.data || searchResults.data.length === 0) {
    return (
      <Paper elevation={1} sx={{ p: 4, textAlign: 'center' }}>
        <Typography variant="h6" color="text.secondary">
          検索結果が見つかりませんでした
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
          キーワード: "{query}"
        </Typography>
      </Paper>
    )
  }

  return (
    <Box>
      <Box sx={{ mb: 2, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <Typography variant="h5" component="h2">
          検索結果
        </Typography>
        <Chip 
          label={`${searchResults.data.length}件`} 
          color="primary" 
          variant="outlined" 
        />
      </Box>
      
      <TableContainer component={Paper} elevation={2}>
        <MuiTable>
          <TableHead>
            <TableRow sx={{ backgroundColor: 'grey.50' }}>
              <TableCell sx={{ fontWeight: 'bold' }}>名前</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {searchResults.data.map((result, index) => (
              <TableRow 
                key={index}
                hover
                sx={{
                  '&:nth-of-type(odd)': {
                    backgroundColor: 'action.hover',
                  },
                }}
              >
                <TableCell>
                  {result.name || '名前なし'}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </MuiTable>
      </TableContainer>
    </Box>
  )
}

export default Table
