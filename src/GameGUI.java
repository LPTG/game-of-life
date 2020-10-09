
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;  
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;  

// Main class to build GUI
public class GameGUI extends Application {  

    @Override  
    public void start(Stage primaryStage) throws Exception {  
    	Group root = new Group();
    	Scene s = new Scene(root);
    	
    	primaryStage.setScene(s);
    	primaryStage.setTitle("Conway's Game of Life");
    	primaryStage.getIcons().add(new Image("/resources/glider_icon.png"));
        
        //Label zoom_label = new Label("Zoom:");  
        Button start = new Button("Start");
        Button next = new Button("Next");
        Button reset = new Button("Reset");
        Slider speed = new Slider(1, 60, 15);
        //Slider zoom = new Slider(1, 100, 20);
        
        Label speedLabel = new Label("FPS:");  
        Label speedValue = new Label(Integer.toString((int) speed.getValue()));
       
        speed.setBlockIncrement(1.0);

        // Hard coded values for board
    	int rowNum = 20;
    	int colNum = 20;
    	
    	int cellSize = 20;
        int spacing = 3;

        HBox mainLayout = new HBox();
        
        // Set builder to use for board
        final ToroidalBuilder state = new ToroidalBuilder(colNum, rowNum);
        
        // Create board with provided parameters and add it to the GUI
        Board board = new Board(state, rowNum, colNum, cellSize, spacing);
        BorderPane boardHolder = new BorderPane(board);
        
        // Add controls to GUI
        GridPane speedLayout = new GridPane();
        speedLayout.add(speedLabel, 0, 0);
        speedLayout.add(speed, 0, 1);
        speedLayout.add(speedValue, 1, 1);
        
        VBox controlsLayout = new VBox(start, next, speedLayout, reset);
        
        // Styling for UI components
        start.setMinSize(150, 25);
        next.setMinSize(150, 25);
        reset.setMinSize(150, 25);
        start.setStyle("-fx-font-size: 1.2em");
        next.setStyle("-fx-font-size: 1.2em");
        reset.setStyle("-fx-font-size: 1.2em");
        speedLabel.setTextFill(Color.web("#f5f1ed"));
        speedLabel.setStyle("-fx-font-size: 1.2em");
        speedValue.setTextFill(Color.web("#f5f1ed"));
        speedValue.setStyle("-fx-font-size: 1.2em");
        boardHolder.setStyle("-fx-background-color: #b3b3b3");
        controlsLayout.setSpacing(10.0);
        HBox.setMargin(controlsLayout, new Insets(10, 10, 10, 10));
        s.setFill(Color.web("#252323"));
        
        // Event handler for UI buttons
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getSource() == start) {
					if (start.getText() == "Start") {
						board.boardAnimation.start();
						start.setText("Pause");
					} else {
						board.boardAnimation.stop();
						start.setText("Start");
					}
					
					event.consume();
				}
				
				if (event.getSource() == next) {
					board.nextFrame();
					event.consume();
				}
				
				if (event.getSource() == reset) {
					state.resetGrid();
					board.nextFrame();
					event.consume();
				}
			}
        };
        
        start.setOnMouseClicked(handler);
        next.setOnMouseClicked(handler);
        reset.setOnMouseClicked(handler);
        
        // Event listener for speed slider
        speed.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
				board.speedModifier = (double) newVal;
				speedValue.setText((Integer.toString((int) speed.getValue())));
			}
        	
        });
        
        // Event listener for setting board cells
        board.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// Will still activate cell if you click on spacing
				int colClicked = (int) (event.getX() / (cellSize + spacing));
				int rowClicked = (int) (event.getY() / (cellSize + spacing));
				
				board.gb.setCell(colClicked, rowClicked);
				board.drawCell(colClicked, rowClicked);
				
				event.consume();
			}
        	
        });
        
        // Add board and controls to HBox
        mainLayout.getChildren().addAll(boardHolder, controlsLayout);
        
        // Add HBox to root group of nodes
    	root.getChildren().add(mainLayout);
    	
    	// Interesting pre-written start states 
    	//int startState[][] = {{1, 1}, {1, 3}, {2, 2}, {2, 3}, {3, 2}}; // glider
    	//int startState[][] = {{2, 4}, {2, 5}, {2, 6}, {2, 10}, {2, 11}, {2, 12}, {4, 2}, {4, 7}, {4, 9}, {4, 14}, {5, 2}, {5, 7}, {5, 9}, {5, 14}, {6, 2}, {6, 7}, {6, 9}, {6, 14},
    	//	{7, 4}, {7, 5}, {7, 6}, {7, 10}, {7, 11}, {7, 12}, {9, 4}, {9, 5}, {9, 6}, {9, 10}, {9, 11}, {9, 12}, {10, 2}, {10, 7}, {10, 9}, {10, 14}, {11, 2}, 
    	//	{11, 7}, {11, 9}, {11, 14}, {12, 2}, {12, 7}, {12, 9}, {12, 14}, {14, 4}, {14, 5}, {14, 6}, {14, 10}, {14, 11}, {14, 12}}; // big oscillator
    	
    	//state.setCells(startState);
    	
    	primaryStage.show(); 	
    }  
    
    public static void main(String[] args) {  
        launch(args);  
    }  
      
}  
