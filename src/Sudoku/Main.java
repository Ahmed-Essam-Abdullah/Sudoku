
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.Arrays;

/**
 *
 * @author AliAl
 */
public class Main {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("write correct command");
            System.exit(1);
        }

        String fileName = args[0];
        String mode=args[1];
        int[][] board=null;
        try{
            board = SudokuLoader.readSudokuCsv(fileName);
            System.out.println("Sudoku is loaded successfully");
            for(int[] row:board){
                System.out.println(Arrays.toString(row));
            }
        }  catch (IllegalArgumentException e) {
            System.err.println("Error loading Sudoku puzzle: " + e.getMessage());
            e.printStackTrace();
        }
        CheckerFactory checkerFactory=new CheckerFactory();
        Checker checker=checkerFactory.createChecker(mode);
        checker.check(board);
    }

}
