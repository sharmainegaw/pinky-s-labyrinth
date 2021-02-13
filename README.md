# pinky-s-labyrinth
A mazebot using A* algorithm implemented on Java with JavaFX GUI, in collaboration with Christine Deticio, Robi Banogon, and Martin Sanchez.

## Running the Application
Start the application using the commands `javac *.java`, then `java MazeApp`.

Maze size only accepts numeric input from 8 to 64 (inclusive).

Clicking `explore` will run the algorithm, and the bot, Pinky, will try to find the shortest path to the goal (lower right square of the maze). Squares with yellow dots means it's unexplored. It'll stop exploring once it's able to find a path to the goal.

Clicking `simulate` will show Pinky traversing through the maze using the shortest path. If it can't physically reach the goal, an error will appear.