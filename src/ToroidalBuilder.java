// Implementation of GridBuilder that allows cells to travel across any border
// If a cell crosses a border it will appear on the opposite border
public class ToroidalBuilder extends GridBuilder {
	public ToroidalBuilder(int cols, int rows) {
		super(cols, rows);
	}
	
	public ToroidalBuilder(int dimensions) {
		super(dimensions);
	}
	
	@Override
	public void buildNextCycle() {
		boolean firstRow[] = new boolean[grid[0].length];
		copyContents(grid[0], firstRow);
		
		boolean above[] = new boolean[grid[0].length];
		copyContents(grid[grid.length - 1], above);
		
		boolean curr[] = new boolean[grid[0].length];
		copyContents(grid[0], curr);
		
		// Calculate life for each cell in grid
		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[i].length; j++)
				grid[i][j] = determineLife(grid, above, curr, firstRow, i, j);

			//System.out.println();

			// Move above and curr buffers down a row
			copyContents(curr, above);
			
			if (i == grid.length - 1)
				copyContents(firstRow, curr);
			else 
				copyContents(grid[i + 1], curr);
		}
	}
	
	private int countLiveNeighbors(boolean[][] grid, boolean[] above, boolean[] curr, boolean[] firstRow, int row, int col) {
		int numLive = 0;

		// Row above, current, below
		for (int i = -1; i <= 1; i++) {
			// Column left, current, right
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				
				// Map column index to positive index on grid
				int tempCol = col + j;
				
				if (tempCol == -1)
					tempCol = grid[0].length - 1;
				else if (tempCol == grid[0].length)
					tempCol = 0;
				
				// Map row index to positive index on grid
				int tempRow = row + i;
				
				if (tempRow == -1)
					tempRow = grid.length - 1;
				else if (tempRow == grid.length)
					tempRow = 0;
				
				// If we're checking the top row, use the buffer array 'above'
				if (i == -1)
					numLive += above[tempCol] ? 1 : 0;
				// If we're checking the middle row, use the buffer array 'curr'
				else if (i == 0)
					numLive += curr[tempCol] ? 1 : 0;
				// If we're checking the last row of the last row in grid, use the buffer array 'firstRow'
				else if (row == grid.length - 1 && i == 1)
					numLive += firstRow[tempCol] ? 1 : 0;
				else
					numLive += grid[tempRow][tempCol] ? 1 : 0;
			}
		}
		
		return numLive;
	}
	
	private void copyContents(boolean[] original, boolean[] copy) {
		for (int i = 0; i < copy.length; i++) {
			copy[i] = original[i];
		}
	}
	
	private boolean determineLife(boolean[][] grid, boolean[] above, boolean[] curr, boolean[] firstRow, int row, int col) {
		int liveNeighbors = countLiveNeighbors(grid, above, curr, firstRow, row, col);
		
		//System.out.print(liveNeighbors + " ");
		
		// Current cell is alive
		if (grid[row][col]) {
			if (liveNeighbors != 2 && liveNeighbors != 3)
				return false;
			else
				return true;
		} else {
			if (liveNeighbors == 3)
				return true;
			else
				return false;
		}
	}
}
