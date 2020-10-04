
public abstract class GridBuilder {
	protected boolean grid[][] = null;
	GridDisplay display = new DisplayCLI();
	
	// Build a rectangle grid
	public GridBuilder(int cols, int rows) {
		grid = new boolean[rows][cols];
	}
	
	// Build a square grid
	public GridBuilder(int dimensions) {
		grid = new boolean[dimensions][dimensions];
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
	
	public void displayGrid() {
		display.printGrid(grid);
	}
	
	public void buildNextCycle() {};
}
