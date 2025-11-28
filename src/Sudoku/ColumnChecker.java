

package Sudoku;

import java.util.ArrayList;
import java.util.List;





public class ColumnChecker implements Runnable{
     private int[][] board;
    private ArrayList <String>  errors;
    private boolean valid;
            
   

    public ColumnChecker(int[][] board,ArrayList <String>  errors) {
        this.board = board;
        this.errors = errors;
        this.valid=true;
    } 
    @Override 
    public void run() {
        for(int col=0;col<9;col++){
        int [] freq=new int[10];
        for(int row=0;row<9;row++)
        {
        freq[board[row][col]]++;
        }
        
      for(int i=1;i<=9;i++)
        {
        if(freq[i]>1)
        {synchronized (System.out) {
                this.valid=false;
                errors.add("Column "+(col+1)+" value "+i+" repeated "+freq[i] +"times");
            }}
        }
        
        
        
        
        
        }
    }

    public boolean isValid() {
        return valid;
    }
    
}
