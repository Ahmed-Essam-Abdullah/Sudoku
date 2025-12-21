/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import GUI.UserAction;
import java.io.IOException;


/**
 *
 * @author AliAl
 */

public class View implements Controllable{
    private ControllerFecadeAdapter controllerFecadeAdapter=new ControllerFecadeAdapter();
    public boolean[] getCatalog(){
      boolean[] catalog =controllerFecadeAdapter.getCatalog();
        return null;
    }

    @Override
    public int[][] getGame(char level) throws NotFoundException{
    int [][] game =controllerFecadeAdapter.getGame(level);
    return game;}

    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException
    {controllerFecadeAdapter.driveGames(sourcePath);}

    @Override
    public boolean[][] verifyGame(int[][] game)
    {boolean [][] result =controllerFecadeAdapter.verifyGame(game);
    return result;
}

    @Override
    public int[][] solveGame(int[][] game) throws InvalidGame
    {int [][]solution=controllerFecadeAdapter.solveGame(game);
    return solution;}

    @Override
    public void logUserAction(UserAction userAction) throws IOException{
    controllerFecadeAdapter.logUserAction(userAction);}
    public  int[][] checkGames() {
 int [][] board=controllerFecadeAdapter.checkGames();
    return board;
 
 }
     public void saveChanges(int [][] board) 
 {
    controllerFecadeAdapter.saveChanges(board);
 }
  public void finishGame(int [][] board) 
  { 
    controllerFecadeAdapter.finishGame(board);
 }
}
