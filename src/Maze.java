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
            if (inBounds(newx,newy) && maze[newx][newy] == wall) {
                maze[x + dirx[i]][y + diry[i]] = path;
                maze[newx][newy] = path;

                //if everything's good we're trying to dig again
                digging(newx, newy);
            }
        }
    }


    public boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < rows && y < cols;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("ВВЕДИТЕ ЧИСЛО: ");
        int size = isValid(input);

        Maze m = new Maze(size, size);

        m.printMaze();
    }

    public static int isValid(Scanner input) {

        while (true) {
            try {
                int m = Integer.parseInt(input.nextLine());
                if(m>9) {
                    return m;
                } else {
                    System.out.println("ВВЕДИТЕ ПОЛОЖИТЕЛЬНОЕ ЧИСЛО БОЛЬШЕ ДЕВЯТИ: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("ОШИБКА. ВВЕДЕНО НЕ ЦЕЛОЕ ЧИСЛО: ");
            }

        }
    }

    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}