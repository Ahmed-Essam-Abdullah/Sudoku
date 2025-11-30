/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AliAl
 */
public class RowChecker implements Runnable {

    private int[][] board;
    private ArrayList<String> errors;
    private boolean valid;
    private int row;

    public RowChecker(int[][] board, ArrayList<String> errors, int row) {
        this.board = board;
        this.errors = errors;
        this.valid = true;
        this.row = row;
    }

    @Override
    public void run() {

        ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            duplicates.add(new ArrayList<>());
        }
        for (int col = 0; col < 9; col++) {
            if (board[row][col] < 1) {
                System.out.println("Invalid number" + board[row][col]);
                continue;
            }
            duplicates.get(board[row][col]).add(col + 1);
        }

        for (int i = 1; i <= 9; i++) {
            ArrayList<Integer> vArrayList = duplicates.get(i);
            if (vArrayList.size() > 1) {
                if (valid) {
                    valid = false;
                }
                synchronized (errors) {

                    errors.add("Row: " + row
                            + " has invalid value " + i
                            + " at position " + vArrayList.toString() + " .");
                }
            }
        }
    }

    public boolean isValid() {
        return valid;
    }

}
