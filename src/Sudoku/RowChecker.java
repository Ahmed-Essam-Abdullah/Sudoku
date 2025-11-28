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
public class RowChecker implements Runnable{
    private int[][] board;
    private ArrayList <String>  errors;
    private boolean valid;   
   

    public RowChecker(int[][] board,ArrayList <String>  errors) {
        this.board = board;
        this.errors = errors;
        this.valid=true;  
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
              this.valid=false;  
                errors.add("Row "+(row+1)+" value "+i+" repeated "+freq[i] +"times");
            }}
        }
        
        
        
        
        
        }
    }

    public boolean isValid() {
        return valid;
    }
    
    
}
