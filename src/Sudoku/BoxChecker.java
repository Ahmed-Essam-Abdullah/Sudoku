/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.value;

public class BoxChecker implements Runnable {

    private int[][] board;
    private ArrayList<String> errors;
    private boolean valid;

    public BoxChecker(int[][] board, ArrayList<String> errors) {
        this.board = board;
        this.errors = errors;
        this.valid = true;

    }

    @Override
    public void run() {

        int box = 0;
        for (int rowbox = 0; rowbox <= 6; rowbox += 3) {
            for (int colbox = 0; colbox <= 6; colbox += 3) {
                box++;
                ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    duplicates.add(new ArrayList<>());
                }
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        if (board[row + rowbox][col + colbox] < 1) {
                            System.out.println("Invalid number" + board[row + rowbox][col + colbox]);
                            continue;
                        }
                        duplicates.get(board[row + rowbox][col + colbox]).add((row * 3 + col + 1));
                    }
                }

                for (int i = 1; i <= 9; i++) {
                    ArrayList<Integer> vArrayList = duplicates.get(i);
                    if (vArrayList.size() > 1) {
                       if(valid)
                           valid=false;
                        synchronized (errors) {

                            errors.add("Box: " + box + " value " + i + " repeated " + vArrayList.toString() + " .");
                        }
                    }
                }
            }
        }
    }

    public boolean isValid() {
        return valid;
    }
}
