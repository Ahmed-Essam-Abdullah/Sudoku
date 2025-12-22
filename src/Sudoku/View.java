/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.io.IOException;
import javaapplication٧.Controllable;

/**
 *
 * @author AliAl
 */
public class View implements Controllable{
    public boolean[] getCatalog(){
        return null;
    };

    @Override
    public int[][] getGame(char level) throws NotFoundException{
    
    
    return null};

    void driveGames(String sourcePath) throws SolutionInvalidException;
// A boolean array which says if a specifc cell is correct or invalid

    bool[][] verifyGame(int[][] game);
// contains the cell x, y and solution for each missing cell

    int[][] solveGame(int[][] game) throws InvalidGame;
// Logs the user action

    @Override
    void logUserAction(UserAction userAction) throws IOException{};
    
}
