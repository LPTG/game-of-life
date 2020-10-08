# Conway's Game of Life

This simulation is run on the command line and simulates an animation by printing the state every 150 milliseconds.
By passing the program a single integer, you can define the size of the grid to use.
If you pass two integers, the board will take them as the columns and rows respectively.

# Installation
To run the simulation on your own machine, compile Game.java

`javac Game.java`

Then run it by passing one or two integers

`java Game 20`

or

`java Game 25 40`

# Examples

A large oscillator

[![Oscillator](https://i.gyazo.com/7928e4af0bfcf1354720d04daec4bb1b.gif)](https://gyazo.com/7928e4af0bfcf1354720d04daec4bb1b)

A glider

Notice how it travels across the borders of the grid

[![Glider](https://i.gyazo.com/508b24c9dbbe4182ced9f13426dd521c.gif)](https://gyazo.com/508b24c9dbbe4182ced9f13426dd521c)

Start states are currently hardcoded into the Game class but will be revised to allow modifications before starting.


