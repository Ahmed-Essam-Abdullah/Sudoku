/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.io.IOException;
import javax.imageio.IIOException;
import storage.StorageManager;
import storage.UndoManager;

/**
 *
 * @author AliAl
 */
public class ControllerFacade implements Viewable {

    private static GameGenerator generator;
    private static SudokuVerifier verifier;
    private static StorageManager storage = new StorageManager();
    private static SudokuSolver solver;

    public ControllerFacade() {

    }

    public  Catalog getCatalog() {
        Catalog catalog = new Catalog();
        boolean hasUnfinished = storage.hasIncompleteGame();
        catalog.setCurrent(hasUnfinished);
        boolean modesExist = storage.hasAllDifficulties();
        catalog.setAllModesExist(modesExist);

        return catalog;
    }

    public  Game getGame(DifficultyEnum level) throws NotFoundException {
        try {
            String folderPath;
            switch (level) {
                case EASY:
                    folderPath = storage.getEasyPath();
                    break;
                case MEDIUM:
                    folderPath = storage.getMediumPath();
                    break;
                case HARD:
                    folderPath = storage.getHardPath();
                    break;
                default:
                    throw new NotFoundException("Games not Found");
            }

            return (Game) storage.loadGame(folderPath);

        } catch (IOException e) {
            throw new NotFoundException("Games not Found");
        }

    }

    public  void driveGames(Game sourceGame) throws SolutionInvalidException {
        if (!SudokuVerifier.isValidAndComplete(sourceGame.getBoard())) {
            throw new SolutionInvalidException("The path provided does not contain a complete and correct Sudoku solution.");
        }
        int[][] easy = new int[9][9];
        int[][] medium = new int[9][9];
        int[][] hard = new int[9][9];
       GameGenerator.generateAll(sourceGame.getBoard(), easy, medium, hard);
       try{
       storage.saveGame(storage.getEasyPath(), new Game(easy));
        storage.saveGame(storage.getMediumPath(), new Game(medium));
        storage.saveGame(storage.getHardPath(), new Game(hard));}
       catch(IOException e){
           throw new SolutionInvalidException("Can't save Game levels");}

    }

    public String verifyGame(Game game) {
        int[][] board = game.getBoard();

        if (!SudokuVerifier.isValid(board)) {
            StringBuilder sb = new StringBuilder("invalid");
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {

                    if (board[r][c] != 0 && SudokuVerifier.isDuplicateInAny(board, r, c)) {
                        sb.append(" ").append(r).append(",").append(c);
                    }
                }
            }
            return sb.toString();
        }
        if (SudokuVerifier.isValidAndComplete(board)) {
            return "valid";
        } else {
            return "incomplete";
        }
    }

    public int[] solveGame(Game game) throws InvalidGame 
    {int[][] solutionMoves = SudokuSolver.solve(game.getBoard());
    int [] CascadeSolution=new int [solutionMoves.length*3];
int index=0;
for (int i =0 ;i<solutionMoves.length;i++)
{//row , col ,value
CascadeSolution[index++]=solutionMoves[i][0];
CascadeSolution[index++]=solutionMoves[i][1];
CascadeSolution[index++]=solutionMoves[i][2];

}
    return CascadeSolution;}


    public void logUserAction(String userAction) throws IOException {
       try{ UndoManager undoManager=new UndoManager();
        undoManager.logMove(userAction);}
       catch (IOException e)
       {          throw new IIOException("Can't save logs :" + e.getMessage());}
    }
}
