// Only used to print grid to command line
public class DisplayCLI {
	char on = '#';
	char off = '-';
	
	public void printGrid(boolean[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] ? on : off);
				System.out.print(" ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}

}
