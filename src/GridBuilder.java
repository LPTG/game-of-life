// Framework for the actual logic to update cells in the grid
public abstract class GridBuilder {
	protected boolean grid[][] = null;
	protected int rows;
	protected int cols;
	//GridDisplay display = new DisplayCLI();
	
	// Build a rectangle grid
	public GridBuilder(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		grid = new boolean[rows][cols];
	}
	
	// Build a square grid
	public GridBuilder(int dimensions) {
		this.rows = dimensions;
		this.cols = dimensions;
		grid = new boolean[dimensions][dimensions];
	}
	
	public void setCell(int col, int row) {
		if (grid == null)
			return;
		
		if (col < 0 || col >= cols)
			return;
		
		if (row < 0 || row >= rows)
			return;
		
		grid[row][col] = true;
	}
	
	// Set the state of grid
	public void setCells(int cells[][]) {
		if (grid == null)
			return;
		
		// Set given cells to true
		// Coords are modded by row length and column length to wrap around in case of overflow
		for (int i = 0; i < cells.length; i++)
			grid[cells[i][0] % grid[0].length][cells[i][1] % grid.length] = true;
	}
	
	public void resetGrid() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				grid[i][j] = false;
	}
	
	public void buildNextCycle() {};
}
