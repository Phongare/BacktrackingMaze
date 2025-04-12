import java.util.*;

public class Maze {

    int rows, cols;
    char[][] maze;

    Random rand = new Random();

    char wall = '#';
    char path = ' ';

    int[] dirx = {-1, 1, 0, 0}; //up down right left
    int[] diry = {0, 0, 1, -1};

    int startX = 1, startY = 1;

    int endX, endY;

    public Maze(int rows, int cols) {

        //odd numbers so maze works well
        if(rows % 2 == 0) {
            this.rows = rows + 1;
        } else {
            this.rows = rows;
        }

        if(cols % 2 == 0) {
            this.cols = cols + 1;
        } else {
            this.cols = cols;
        }

        this.maze = new char[this.rows][this.cols];

        //endx -2 bc index starts from 0 + we don't want to make a hole in wall
        this.endX = this.rows - 2;
        this.endY = this.cols - 2;

        generate();
    }

    public void generate() {

        for (char[] row : maze) {
            Arrays.fill(row, wall);
        }
        digging(startX, startY);
        maze[startX][startY] = path;
        maze[endX][endY] = path;
    }

    private void digging(int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions, rand);

        for (int i : directions) {
            //new paths we do *2 bc if we go only 1 symbol long, if maze will want to go horizontally next time there will be a hole
            int newx = x + dirx[i] * 2;
            int newy = y + diry[i] * 2;

            //checking that we're not making a hole and we're not digging the same coordinate twice
            if ( maze[newx][newy] == wall) {
                maze[x + dirx[i]][y + diry[i]] = path;
                maze[newx][newy] = path;

                //if everything's good we're trying to dig again
                digging(newx, newy);
            }
        }
    }


}