/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;
import java.io.IOException;
import storage.StorageManager;
/**
 *
 * @author AliAl
 */
public class ControllerFacade implements Viewable{
    private static GameGenerator generator;
    private static SudokuVerifier verifier;
    private static StorageManager storage = new StorageManager();
    private static SudokuSolver solver;


    public ControllerFacade() {

    }

   public static Catalog  getCatalog()
      {Catalog catalog=new Catalog();
      boolean hasUnfinished=storage.hasIncompleteGame();
      catalog.setCurrent(hasUnfinished);
      boolean modesExist=storage.hasIncompleteGame();
      catalog.setAllModesExist(modesExist);
      
      return catalog;
      }
      public static Game getGame(DifficultyEnum level) throws NotFoundException{
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

  public static void driveGames(Game sourceGame) throws SolutionInvalidException
    {
        if(!SudokuVerifier.isValidAndComplete(sourceGame.getBoard()))
        throw new SolutionInvalidException("The path provided does not contain a complete and correct Sudoku solution.");
    int [][] easy=new int[9][9];
    int [][] medium=new int[9][9];
    int [][] hard=new int[9][9];
    GameGenerator.generateAll(hard, easy, medium, hard);
    try{
    storage.saveGame(storage.getEasyPath(), easy);
    storage.saveGame(storage.getMediumPath(), medium);
    storage.saveGame(storage.getHardPath(), hard);
    }
    catch (IOException e){
    throw new SolutionInvalidException("Failed to save game files:"+e.getMessage());
    }
    
    }

    public String verifyGame(Game game){
    int board=game.getBoard();
    boolean hasDuolicates =false;
    boolean hasZeros =false;
 for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {   
         int 
    
    
        }
 }
        
        
        return null;
    
    }
// returns the correct combination for the missing numbers
// Hint: So, there are many ways you can approach this, one way is
// to have a way to map an index in the combination array to its location in theboard
// one other way to to try to encode the location and the answer all in just oneint

    int[] solveGame(Game game) throws InvalidGame;
// Logs the user action

    void logUserAction(String userAction) throws IOException;    
    
}
