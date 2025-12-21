package Sudoku;

import java.util.ArrayList;
import java.util.List;

public class ColumnChecker implements Runnable {

    private int[][] board;
    private ArrayList<String> errors;
    private boolean valid;
    private int col;

    public ColumnChecker(int[][] board, ArrayList<String> errors, int col) {
        this.board = board;
        this.errors = errors;
        this.valid = true;
        this.col = col;
    }

    @Override
    public void run() {
            ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
            for (int i = 0; i < 10; i++) 

            {duplicates.add(new ArrayList<>());}

                for (int row = 0; row < 9; row++) {
                    int value = board[row][col];

                    if (value == 0) {
                        continue;
                    }
                    
                    if (value < 0 || value > 0) {
                        errors.add(
                          "column" +(col + 1) +
                          "has invalid value" + value +
                          "at a row" + (row + 1) + "."
                        );

                        valid = false;
                        continue;
                    }
                    
                    duplicates.get(value).add(row + 1);
                
                } 


                for (int i = 1; i <= 9; i++) {
                    ArrayList<Integer> vArrayList = duplicates.get(i);
                    if (list.size() > 1) {
                        errors.add(
                            "column" + (col + 1) + 
                            "has duplicate value " + i +
                            "at rows" + list +
                        );
                        valid = false;
                    }
                        
                    
                }
        }

    public boolean isValid() {
        return valid;
    }

}