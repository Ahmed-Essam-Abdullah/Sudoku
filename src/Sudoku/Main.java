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
    String fileName = "src/resources/sudoku.csv";
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
    Checker1thread.check(board);
}
    
}
