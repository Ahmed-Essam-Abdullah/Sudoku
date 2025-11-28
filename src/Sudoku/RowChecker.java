/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.List;



/**
 *
 * @author AliAl
 */
public class RowChecker implements Runnable{
    private int[][] board;
    private List<String> errors;
            
   

    public RowChecker(int[][] board,List <String> errors) {
        this.board = board;
        this.errors = errors;
    } 
    @Override
    public void run() {
        for(int row=0;row<9;row++){
        int [] freq=new int[10];
        for(int col=0;col<9;col++)
        {
        freq[board[row][col]]++;
        }
        
      for(int i=1;i<=9;i++)
        {
        if(freq[i]>1)
        {synchronized (System.out) {
            
                System.out.println("Row "+(row+1)+" value "+i+" repeated "+freq[i] +"times");
            }}
        }
        
        
        
        
        
        }
    }
    
    
}
