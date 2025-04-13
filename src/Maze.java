import java.util.*;

public class Maze {

    int rows, cols;
    char[][] maze;

    Random rand = new Random();

    public static char wall = '▆';
    public static char path = ' ';
    public static char visited = '0';

    //directions
    public static int[] dirx = {-1, 1, 0, 0};
    public static int[] diry = {0, 0, 1, -1};

    public static int startX = 1, startY = 1;

    public static int endX, endY;

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
        //firstly all maze is the whole wall;
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

        solveMaze(m.maze, m.startX, m.startY, m.endX, m.endY);

        m.printMaze();
    }

    public static int isValid(Scanner input) {
        //because of the while cycle this function won't leave you until you write the valid symbols
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
        //regular printing 2D array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static boolean solveMaze(char[][] maze, int x, int y, int endX, int endY) {
        //if coordinates are on finish it returns true and solveMaze is completed
        if (x == endX && y == endY) {
            maze[x][y] = visited;
            return true;
        }
        //if coordinates are not path, then function goes back
        if (maze[x][y] != path) {
            return false;
        }
        //if everything is good coordinates become visited
        maze[x][y] = visited;
        if(maze[x+1][y]==visited) {
            maze[x][y]='△';
        } else if(maze[x-1][y]==visited) {
            maze[x][y]='∇';
        } else if(maze[x][y-1]==visited) {
            maze[x][y]='▷';
        } else if(maze[x][y+1]==visited) {
            maze[x][y]='◁';
        }


        for (int d = 0; d < 4; d++) {
            int newx = x + dirx[d];
            int newy = y + diry[d];
            //then from this visited coordinates another SolveMaze starts working
            if (solveMaze(maze, newx, newy, endX, endY)) {
                return true;
            }
            //when in some far recursive function we reach finish then every function returns true and the whole function is completed
        }

        //if for cycle doesn't give true,then this path is incorrect and we have to go back
        maze[x][y] = path;
        return false;
    }


}

