/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.StorageManager;
import GUI.UserAction;
import javax.imageio.IIOException;

/**
 *
 * @author AliAl
 */
public class ControllerFecadeAdapter implements Controllable {

    private ControllerFacade controllerFacade;

    public ControllerFecadeAdapter() {
        this.controllerFacade = new ControllerFacade();
    }

    @Override
    public boolean[] getCatalog() {

        boolean[] GameStatus = new boolean[2];
        GameStatus[0] = controllerFacade.getCatalog().getCurrent();
        GameStatus[1] = controllerFacade.getCatalog().getAllModesExist();
        return GameStatus;
    }

    ;

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        DifficultyEnum difficulty;
        if (level == 'E') {
            difficulty = DifficultyEnum.EASY;
        } else if (level == 'M') {
            difficulty = DifficultyEnum.MEDIUM;
        } else {
            difficulty = DifficultyEnum.HARD;
        }
        Game game = controllerFacade.getGame(difficulty);
        return game.getBoard();
    }

    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException {
        try {
            storage.StorageManager storage = new StorageManager();
            int[][] board = ( storage.loadGame(sourcePath)).getBoard();
            Game game = new Game(board);
            controllerFacade.driveGames(game);
        } catch (IOException e) {
            throw new SolutionInvalidException("Could not read file at: " + sourcePath);
        }
    }

    @Override
    public boolean[][] verifyGame(int[][] boardData) {
        boolean[][] result = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = true;
            }
        }

        Game gameObj = new Game(boardData);
        String status = controllerFacade.verifyGame(gameObj);

        if (status.startsWith("invalid")) {
            String[] parts = status.split(" ");

            for (int i = 1; i < parts.length; i++) {

                String[] coords = parts[i].split(",");
                int r = Integer.parseInt(coords[0]);
                int c = Integer.parseInt(coords[1]);
                if (r >= 0 && r < 9 && c >= 0 && c < 9) {
                    result[r][c] = false;
                }
            }
        }
        return result;
    }

    @Override
    public int[][] solveGame(int[][] gameBoard) throws InvalidGame {

        Game gameObj = new Game(gameBoard);

        int[] flatSolution = controllerFacade.solveGame(gameObj);

        int[][] solvedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(gameBoard[i], 0, solvedBoard[i], 0, 9);
        }

        for (int i = 0; i < flatSolution.length; i += 3) {
            int r = flatSolution[i];
            int c = flatSolution[i + 1];
            int val = flatSolution[i + 2];
            solvedBoard[r][c] = val;
        }

        return solvedBoard;
    }

    public void logUserAction(UserAction userAction) throws IOException {
       try { int row = userAction.getRow();
        int col = userAction.getCol();
        int newVar = userAction.getNewValue();
        int oldVar = userAction.getOldValue();
        String uString = row + "," + col + "," + newVar + "," + oldVar;
        controllerFacade.logUserAction(uString);
    }
 catch (IOException e)
       {          throw new IIOException("Can't save logs :" + e.getMessage());}
    }
}
