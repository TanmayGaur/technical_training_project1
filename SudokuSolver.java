public class SudokuSolver {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
  
    public static boolean isValid(int[][] board, int row, int column, int num) {
      // Check row and column constraints
      for (int i = 0; i < BOARD_SIZE; i++) {
        if (board[row][i] == num || board[i][column] == num) {
          return false;
        }
      }
  
      // Check subsection constraint
      int startRow = row - row % SUBSECTION_SIZE;
      int startColumn = column - column % SUBSECTION_SIZE;
      for (int i = 0; i < SUBSECTION_SIZE; i++) {
        for (int j = 0; j < SUBSECTION_SIZE; j++) {
          if (board[i + startRow][j + startColumn] == num) {
            return false;
          }
        }
      }
      return true;
    }
  
    public static boolean solveSudoku(int[][] board, int row, int column) {
      // Base cases: reached end or encountered a filled cell
      if (row == BOARD_SIZE - 1 && column == BOARD_SIZE) {
        return true; // Solved the board
      } else if (column == BOARD_SIZE) {
        return solveSudoku(board, row + 1, 0); // Move to next row
      } else if (board[row][column] != 0) {
        return solveSudoku(board, row, column + 1); // Skip filled cells
      }
  
      // Try all possible numbers for the empty cell
      for (int num = 1; num <= BOARD_SIZE; num++) {
        if (isValid(board, row, column, num)) {
          board[row][column] = num; // Place the number
  
          // Recursively solve with the placed number
          if (solveSudoku(board, row, column + 1)) {
            return true; // Found a solution
          }
  
          // Backtrack if placement doesn't lead to a solution
          board[row][column] = 0; // Remove the number
        }
      }
  
      // No solution found at this cell
      return false;
    }
  
    public static void printBoard(int[][] board) {
      for (int row = 0; row < BOARD_SIZE; row++) {
        for (int column = 0; column < BOARD_SIZE; column++) {
          System.out.print(board[row][column] + " ");
        }
        System.out.println();
      }
    }
  
    public static void main(String[] args) {
      int[][] board = {
        {3, 0, 6, 5, 0, 8, 4, 0, 0},
        {5, 2, 0, 0, 0, 0, 0, 0, 0},
        {0, 8, 7, 0, 0, 0, 0, 3, 1},
        {0, 0, 3, 0, 1, 0, 0, 8, 0},
        {9, 0, 0, 8, 6, 3, 0, 0, 5},
        {0, 5, 0, 0, 9, 0, 6, 0, 0},
        {1, 3, 0, 0, 0, 0, 2, 5, 0},
        {0, 0, 0, 0, 0, 0, 0, 7, 4},
        {0, 0, 5, 2, 0, 0, 0, 0, 8}
      };
  
      System.out.println("Initial Board:");
      printBoard(board);
  
      if (solveSudoku(board, 0, 0)) {
        System.out.println("\nSolved Board:");
        printBoard(board);
      } else {
        System.out.println("No solution exists.");
      }
    }
  }
  