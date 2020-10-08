import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Board to display game on GUI
public class Board extends Canvas{
	double speedModifier = 60.0;
	final int rowNum;
	final int colNum;
	final int cellSize;
	final int spacing;
	GridBuilder gb;
	GraphicsContext gc = this.getGraphicsContext2D();
	AnimationTimer boardAnimation;
	
	public Board(GridBuilder gb, int rowNum, int colNum, int cellSize, int spacing) {
		super(rowNum * (cellSize + spacing), colNum * (cellSize + spacing));
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.cellSize = cellSize;
		this.spacing = spacing;
		this.gb = gb;
		initialize();
		nextFrame();
	}
	
	public void setState(boolean[][] newState) {
		this.gb.grid = newState;
	}
	
	public void setSpeed(int speed) {
		if (speed < 0)
			speed *= -1;
		
		if (speed == 0 || speed > 60)
			return;
		
		speedModifier = speed;
	}
	
	private void initialize() {
		boardAnimation = new AnimationTimer() {
			private int frameCount = 1;
			
			@Override
			public void handle(long currentNanoTime) {
				
				if (frameCount >= (60 / speedModifier)) {
					nextFrame();
					frameCount = 0;
				}
				
				frameCount++;
			}
		};
	}

	public void nextFrame() {
		int xPos = 0;
        int yPos = 0;

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                
                if (gb.grid[row][col]) {
                	gc.setFill(Color.BLACK);
	                gc.fillRect(xPos, yPos, cellSize, cellSize);
                } else {
                	gc.setFill(Color.WHITE);
	                gc.fillRect(xPos, yPos, cellSize, cellSize);
                }
                
                xPos += cellSize + spacing;
            }
            
            xPos = 0;
            yPos += cellSize + spacing;
        }
        
        gb.buildNextCycle();
	}
}
