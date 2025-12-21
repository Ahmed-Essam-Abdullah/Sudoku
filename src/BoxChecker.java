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
    private int boxIndex;

    public BoxChecker(int[][] board, ArrayList<String> errors, int boxIndex) {
        this.board = board;
        this.errors = errors;
        this.valid = true;
        this.boxIndex = boxIndex;

    }

    @Override
    public void run() {

        int startRow = (boxIndex / 3) * 3;
        int startCol = (boxIndex % 3) * 3;

        ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            duplicates.add(new ArrayList<>());
        }

        int position = 1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                int value = board[startRow + r][startCol + c];

                if (value == 0){
                    position++;
                    continue;
                }

                if (value < 0 || value > 9) {
                    errors.add(
                        "Box" + (boxIndex + 1) +
                        "has invalid values" value +
                        "at a position" + position + "."
                    );
                    
                    valid = false;
                    position++;
                    continue;
                       
                    
                    valid = false;
                    continue;
                }

                duplicates.get(value).add(position);
                position++;
            }
        }

        for (int i = 1; i <= 9; i++) {
            ArrayList<Integer> list = duplicates.get(i);
            if (list.size() > 1) {
                errors.add(
                    "Box " + (boxIndex + 1) +
                    "has duplicate values" + i +
                    "at a position " + list + "."
                );

                
                valid = false;
            }
        }
    }

    public boolean isValid() {
        return valid;
    }
}