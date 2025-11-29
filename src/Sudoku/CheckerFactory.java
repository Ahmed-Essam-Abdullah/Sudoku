/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author AliAl
 */
public class CheckerFactory {
    public  Checker createChecker(String mode)
    {  if(mode.equals("0"))
        return new SequentialChecker();
    else if (mode.equals("3"))
       {return new ThreeThreadChecker();}
    
    return null;
    }
    
}
