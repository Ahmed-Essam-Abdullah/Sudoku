/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.StorageManager;

/**
 *
 * @author AliAl
 */
public class ControllerFecadeAdapter implements Controllable{
    private ControllerFacade controllerFacade;

    public ControllerFecadeAdapter() {
        this.controllerFacade =new ControllerFacade();
    }
    @Override
    public boolean[] getCatalog(){
    
    boolean[] GameStatus=new boolean[2];
    GameStatus[0]=controllerFacade.getCatalog().getCurrent();
    GameStatus[1]=controllerFacade.getCatalog().getAllModesExist();
    return GameStatus;
    }
    ;

    @Override
    public int[][] getGame(char level) throws NotFoundException
    {DifficultyEnum difficulty;
    if(level == 'E')
        difficulty=DifficultyEnum.EASY;
    else if(level == 'M')
        difficulty=DifficultyEnum.MEDIUM;    
    else
        difficulty=DifficultyEnum.HARD;
    Game game =controllerFacade.getGame(difficulty);
        return game.getBoard();
    }
  @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException
    {
    try{storage.StorageManager storage =new StorageManager();
    int [][]board=(int[][]) storage.loadGame(sourcePath);
    Game game=new Game(board);
    ControllerFacade.driveGames(game);
    }   catch (IOException e) {
            throw new SolutionInvalidException("Could not read file at: " + sourcePath);
        }}
    bool[][] verifyGame(int[][] game);
// contains the cell x, y and solution for each missing cell

    int[][] solveGame(int[][] game) throws InvalidGame;
// Logs the user action

    void logUserAction(UserAction userAction) throws IOException;
}
