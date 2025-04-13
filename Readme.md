# Maze generation
Firstly we define maze start and finish, then we make the maze odd so he would not break down, then we create 2D text array which represents maze and fill him with walls, then we start digging this 2D cube.
we dig not the next "pixel" but after the next one in order to connect them later, because we must have many walls between them, if we dug the next pixel, then in the end it would just dig a hollow square. Then we check if this is already a path or if we try to dig a border, and if everything is correct we dig it and call Digging function again so it could continue from this new "pixel". When there won't be any solution Digging function will stop.
# Maze solver
This function works by backtracking method which means it will always try to do smth, and if there's no way to do smth, it will go back until find a way.
Firstly we check if we are on finish line, if we aren't we continue.
Then we check if our coordinate is on a path not on a wall or smth else.
if everything is good then we mark this one as a visited cell.
Then we dig in random direction and call the Solving function again but from a new coordinate.
In the perfect way it will find a finish and give us true which will mean end of a fucntion.
But if there's no way to dig anymore, function goes back and marks our coordinate as a path again.