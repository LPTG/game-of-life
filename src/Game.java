
public class Game {
	GridBuilder grid;
	//int start[][] = {{4, 3}, {4, 4}, {4, 5}}; // oscillator
	//int start[][] = {{1, 1}, {1, 3}, {2, 2}, {2, 3}, {3, 2}}; // glider
	int start[][] = {{2, 4}, {2, 5}, {2, 6}, {2, 10}, {2, 11}, {2, 12}, {4, 2}, {4, 7}, {4, 9}, {4, 14}, {5, 2}, {5, 7}, {5, 9}, {5, 14}, {6, 2}, {6, 7}, {6, 9}, {6, 14},
		{7, 4}, {7, 5}, {7, 6}, {7, 10}, {7, 11}, {7, 12}, {9, 4}, {9, 5}, {9, 6}, {9, 10}, {9, 11}, {9, 12}, {10, 2}, {10, 7}, {10, 9}, {10, 14}, {11, 2}, 
		{11, 7}, {11, 9}, {11, 14}, {12, 2}, {12, 7}, {12, 9}, {12, 14}, {14, 4}, {14, 5}, {14, 6}, {14, 10}, {14, 11}, {14, 12}}; // big oscillator
	
	public Game(GridBuilder newGrid) {
		grid = newGrid;
		System.out.println("Conway's Game Of Life");
		grid.setCells(start);
	}
	
	public void startGame(GridBuilder grid) {
		grid.displayGrid();
		
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(150);
				
				grid.buildNextCycle();
				grid.displayGrid();
				
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		if (args.length > 2) {
			System.out.println("Please enter one or two integers as arguments.");
		}
		
		GridBuilder grid = null;
		
		if (args.length == 1) {
			int dimensions = Integer.parseInt(args[0]);
			grid = new ToroidalBuilder(dimensions);
		} else if (args.length == 2) {
			int cols = Integer.parseInt(args[0]);
			int rows = Integer.parseInt(args[1]);
			grid = new ToroidalBuilder(cols, rows);
		}
		
		Game newGame = new Game(grid);
		newGame.startGame(grid);
	}
}
