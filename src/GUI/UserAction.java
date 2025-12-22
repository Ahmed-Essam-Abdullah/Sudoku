/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author AliAl
 */
public class UserAction {

    private int row;
    private int col;
    private int oldValue;
    private int newValue;

    public UserAction(int row, int col, int oldValue, int newValue) {
        this.row = row;
        this.col = col;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    
    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getOldValue() { return oldValue; }
    public int getNewValue() { return newValue; }
    
  
    @Override
    public String toString() {
        return row + "," + col + "," + newValue + "," + oldValue;
    }
  
}
