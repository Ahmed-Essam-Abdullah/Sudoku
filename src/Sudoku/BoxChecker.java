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
    private ArrayList <String>  errors;
    private boolean valid;
    public BoxChecker(int[][] board, ArrayList <String>  errors) {
        this.board = board;
        this.errors = errors;
         this.valid=true;
    }

    @Override
    public void run() {
        for (int rowbox = 0; rowbox <= 6; rowbox += 3) {
            for (int colbox = 0; colbox <= 6; colbox += 3) {
                 int[] freq = new int[10];
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        freq[board[row+rowbox][col+colbox]]++;
                    }

                    for (int i = 1; i <= 9; i++) {
                        if (freq[i] > 1) {
                            synchronized (errors) {
                                
                               errors.add("Row "+(((i-1)/3)+1)+"Column "+(((i-1)%3)+1)+" value "+i+" repeated "+freq[i] +"times");
                            }
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
