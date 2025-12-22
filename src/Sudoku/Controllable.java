/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sudoku;

/**
 *
 * @author AliAl
 */
public interface Controllable {
    
    bool[] getCatalog();

    int[][] getGame(char level) throws NotFoundException;

    void driveGames(String sourcePath) throws SolutionInvalidException;
// A boolean array which says if a specifc cell is correct or invalid

    bool[][] verifyGame(int[][] game);
// contains the cell x, y and solution for each missing cell

    int[][] solveGame(int[][] game) throws InvalidGame;
// Logs the user action

    void logUserAction(UserAction userAction) throws IOException;

}
