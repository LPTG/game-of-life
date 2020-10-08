# Conway's Game of Life
[The Game of Life](https://en.m.wikipedia.org/wiki/Conway%27s_Game_of_Life), also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. 

### GUI:

Using the GUI, players can set an initial state by clicking on cells to toggle them on. The game will begin after pressing the start button. Press the pause button to stop the 
simulation and the reset button to reset to a blank grid. The speed of the simulation can be adjusted from 1 frame per second to 60 frames per second using the speed slider. Cells can be added to the simulation as it plays out or players can pause the simulation to add them.


### CLI:

This simulation is run on the command line and simulates an animation by printing the state every 150 milliseconds.
By passing the program a single integer, you can define the size of the grid to use.
If you pass two integers, the board will take them as the columns and rows respectively.

# Installation
### GUI:

To run the simulation on your own machine, compile GameGUI.java

`javac GameGUI.java`

Then run it by passing one or two integers

`java GameGUI`

### CLI:

To run the simulation on your own machine, compile Game.java

`javac GameCLI.java`

Then run it by passing one or two integers

`java GameCLI 20`

or

`java GameCLI 25 40`

# Examples

A large oscillator

[![Oscillator](https://i.gyazo.com/7928e4af0bfcf1354720d04daec4bb1b.gif)](https://gyazo.com/7928e4af0bfcf1354720d04daec4bb1b)

A glider

Notice how it travels across the borders of the grid

[![Glider](https://i.gyazo.com/508b24c9dbbe4182ced9f13426dd521c.gif)](https://gyazo.com/508b24c9dbbe4182ced9f13426dd521c)

Start states are currently hardcoded into the GameCLI class.


