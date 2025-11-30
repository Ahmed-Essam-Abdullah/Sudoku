package Sudoku;

import java.util.ArrayList;
import java.util.List;

public class ColumnChecker implements Runnable {

    private int[][] board;
    private ArrayList<String> errors;
    private boolean valid;
    private int col;

    public ColumnChecker(int[][] board, ArrayList<String> errors,int col) {
        this.board = board;
        this.errors = errors;
        this.valid = true;
        this.col=col;
    }

    @Override
    public void run() {
            ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
            for (int i = 0; i < 10; i++) 
            {duplicates.add(new ArrayList<>());}
                for (int row = 0; row < 9; row++) {
                if (board[row][col] < 1) {
                            System.out.println("Invalid number" + board[row][col]);
                            continue;
                        }
                        duplicates.get(board[row][col]).add(row+1);
                    }
                
                


                for (int i = 1; i <= 9; i++) {
                    ArrayList<Integer> vArrayList = duplicates.get(i);
                    if (vArrayList.size() > 1) {
                       if(valid)
                           valid=false;
                        synchronized (errors) {

                            errors.add("Column: " + col + 
                                " has invalid value " + i +
                                " at position " + vArrayList.toString() + " .");
                        }
                    }
                }
        }

    

    public boolean isValid() {
        return valid;
    }

}
