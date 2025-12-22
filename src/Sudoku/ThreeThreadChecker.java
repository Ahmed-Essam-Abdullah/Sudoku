/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.ArrayList;

/**
 *
 * @author AliAl
 */
public class ThreeThreadChecker implements Checker {

    private final ArrayList<String> errors;

    public ThreeThreadChecker() {
        this.errors = new ArrayList<>();
    }

    public  void check(int[][] board) {
        Thread rowThread = new Thread(() -> {
            for (int r = 0; r < 9; r++) {
                new RowChecker(board, errors, r).run();
            }
        });

        Thread columnThread = new Thread(() -> {
            for (int c = 0; c < 9; c++) {
                new ColumnChecker(board, errors, c).run();
            }
        });

        Thread BoxThread = new Thread(() -> {
            for (int b = 0; b < 9; b++) {
                new BoxChecker(board, errors, b).run();
            }
        });
        rowThread.start();
        columnThread.start();
        BoxThread.start();
        try{
            rowThread.join();
            columnThread.join();
            BoxThread.join();}
        catch(InterruptedException e)
        {System.out.println(e);}
        if (errors.isEmpty()) {
            System.out.println("Valid");
        } else {System.out.println("InValid");
            for(String error :this.errors)
            {
                System.out.println(error);
            }
        }
    }

}