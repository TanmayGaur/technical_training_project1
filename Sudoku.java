import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sudoku extends JFrame implements ActionListener {

    private final int GRID_SIZE = 9;
    private JButton[][] buttons;
    private int[][] board;

    public Sudoku() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        board = new int[GRID_SIZE][GRID_SIZE];
        buttons = new JButton[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 16));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

      
       
// Example board provided for demonstration
board[0][0] = 3;
board[0][2] = 6;
board[0][3] = 5;
board[0][5] = 8;
board[0][6] = 4;
board[1][0] = 5;
board[1][1] = 2;
board[1][5] = 3;
board[1][6] = 9;
board[2][1] = 8;
board[2][2] = 7;
board[2][7] = 6;
board[2][8] = 1;
board[3][0] = 8;
board[3][3] = 2;
board[3][4] = 1;
board[3][5] = 4;
board[3][8] = 9;
board[4][2] = 4;
board[4][6] = 6;
board[5][0] = 7;
board[5][3] = 3;
board[5][4] = 9;
board[5][5] = 2;
board[5][8] = 1;
board[6][0] = 1;
board[6][1] = 3;
board[6][6] = 2;
board[6][7] = 8;
board[7][2] = 8;
board[7][3] = 6;
board[7][8] = 3;
board[8][2] = 3;
board[8][3] = 1;
board[8][5] = 6;
board[8][6] = 5;
board[8][8] = 8;

      
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] != 0) {
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                    buttons[i][j].setEnabled(false); 
                }
            }
        }

        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int row = 0, col = 0;

       
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

       
        String input = JOptionPane.showInputDialog(this, "Enter a number (1-9):");
        if (input != null && isValidPlacement(row, col, Integer.parseInt(input))) {
            buttons[row][col].setText(input);
            board[row][col] = Integer.parseInt(input);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid placement!");
        }
    }

   boolean isValidPlacement(int row, int col, int value) {
        //  row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == value && col != i) {
                return false;
            }
        }
    
        // column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == value && row != i) {
                return false;
            }
        }
    
        // sub-grid
        int subGridRowStart = (row / 3) * 3;
        int subGridColumnStart = (col / 3) * 3;
        for (int i = subGridRowStart; i < subGridRowStart + 3; i++) {
            for (int j = subGridColumnStart; j < subGridColumnStart + 3; j++) {
                if (board[i][j] == value && row != i && col != j) {
                    return false;
                }
            }
        }
    
        return true;
    }
    public static void main(String[] args) {
        new Sudoku();
    }
}

