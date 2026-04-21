public class GameOfLife {
    private boolean[][] grid;
 
    public GameOfLife(boolean[][] initialGrid) {
        grid = initialGrid;
    }
 
    public void printGrid() {
        for (boolean[] row : grid) {
            for (boolean cell : row) {
                if (cell == true) {
                    System.out.print("0");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    private int countNeighbours(int row, int col) {
        int aliveNeighbours = 0;
        int height = grid.length;
        int width = grid[0].length;
        // Checking Left
        if (grid[row][(col - 1 + width) % width]) {
            aliveNeighbours++;
        }
        // Checking Right
        if (grid[row][(col + 1) % width]) {
            aliveNeighbours++;
        }
        // Checking Up
        if (grid[(row - 1 + height) % height][col]) {
            aliveNeighbours++;
        }
        // Checking Down
        if (grid[(row + 1) % height][col]) {
            aliveNeighbours++;
        }
        // Up and Left
        if (grid[(row - 1 + height) % height][(col - 1 + width) % width]) {
            aliveNeighbours++;
        }
        // Up and Right
        if (grid[(row - 1 + height) % height][(col + 1) % width]) {
            aliveNeighbours++;
        }
        // Down and Right
        if (grid[(row + 1) % height][(col + 1) % width]) {
            aliveNeighbours++;
        }
        // Down and Left
        if (grid[(row + 1) % height][(col - 1 + width) % width]) {
            aliveNeighbours++;
        }
        return aliveNeighbours;
    }
 
    public void simulateStep() {
        boolean[][] newGrid = new boolean[grid[0].length][grid.length];

        for (int row = 0; row < grid.length; row ++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == true) {
                    if (countNeighbours(row, col) == 2 || countNeighbours(row, col) == 3) {
                        newGrid[row][col] = true;
                    } else {
                        newGrid[row][col] = false;
                    }
                } else {
                    if (countNeighbours(row, col) == 3) {
                        newGrid[row][col] = true;
                    } else {
                        newGrid[row][col] = false;
                    }
                }
            }
        }

        grid = newGrid;
    }
 
    public static void main(String[] args) {
        boolean[][] initialGrid = new boolean[10][10];
        initialGrid[4][5] = true;
        initialGrid[4][6] = true;
        initialGrid[5][4] = true;
        initialGrid[5][5] = true;
        initialGrid[6][5] = true;
        GameOfLife game = new GameOfLife(initialGrid);
        for (int i = 0; i < 100; i++) {
            System.out.println("Before Step " + (i+1));
            game.printGrid();
            System.out.println();
            game.simulateStep();
        }
    }
}

/* notes while building
    - 
*/