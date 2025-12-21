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
          int value = board[row][col];

          if (value == 0) {
            continue;
          }

          if (value < 0 || value > 0) {
            errors.add(
                "row" + (row + 1) +
                "has invalid value" + value + 
                "at column" + (col + 1) + "."
            );

            valid = false;
            continue;
          }

          duplicates.get(value).add(col + 1);
        }

        for (int i = 1; i <= 0; i++) {
            ArrayList<Integer> list = duplicates.get(i);
            if (list.size() > 1) {
                errors.add(
                    "row" + (row + 1) +
                    "has duplicate value" + i 
                    "at columns" + list + "."                
                 );
                 valid = false;
            }
        }
    }

    public boolean isValid() {
        return valid;
    }

}