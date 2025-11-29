/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.ArrayList;

/**
 *
 * @author Khaled
 */
public class TwentySevenThreadChecker implements Checker{
    private final ArrayList<String> errors;
    
    public TwentySevenThreadChecker() {
        this.errors = new ArrayList<>();
    }
    
    @Override
     public void check(int[][] board) {

        ArrayList<Thread> threads = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            threads.add(new Thread(new RowChecker(board, errors, r)));
        }

        for (int c = 0; c < 9; c++) {
            threads.add(new Thread(new ColumnChecker(board, errors, c)));
        }

        for (int b = 0; b < 9; b++) {
            threads.add(new Thread(new BoxChecker(board, errors, b)));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        if (errors.isEmpty()) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
            for (String e : errors) {
                System.out.println(e);
            }
        }
     }
}
