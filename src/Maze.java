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

    }

}