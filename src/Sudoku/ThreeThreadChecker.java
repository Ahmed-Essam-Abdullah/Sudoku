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

    private ArrayList<String> errors;

    public ThreeThreadChecker() {
        this.errors = new ArrayList<String>();
    }

    public  void check(int[][] board) {
        Thread rowThread = new Thread(new RowChecker(board, errors));
        Thread columnThread = new Thread(new ColumnChecker(board, errors));
        Thread BoxThread = new Thread(new BoxChecker(board, errors));
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
