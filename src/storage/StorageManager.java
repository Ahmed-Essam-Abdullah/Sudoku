/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import Sudoku.Game;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Khaled
 */
public class StorageManager {
    private static final String ROOT = "storage";
    private static final String EASY = ROOT + "/easy";
    private static final String MEDIUM = ROOT + "/medium";
    private static final String HARD = ROOT + "/hard";
    private static final String INCOMPLETE = ROOT + "/incomplete";
    private static final String GAME_FILE = "sudoku.csv";
    private static final String LOG_FILE = "log.txt";
    
    public StorageManager() {
        createFolders();
    }

    private void createFolders() {
        new File(EASY).mkdirs();
        new File(MEDIUM).mkdirs();
        new File(HARD).mkdirs();
        new File(INCOMPLETE).mkdirs();
    }


    
    public String getEasyPath() { return EASY; }
    public String getMediumPath() { return MEDIUM; }
    public String getHardPath() { return HARD; }
      public String getSavedPath() { return "src/resources"; }
    public String getIncompletePath() { return INCOMPLETE; }
    
    public void saveGame(String folder, Game game) throws IOException {
        File file = new File(folder + "/" + GAME_FILE);
        writeBoard(file, game.getBoard());
    }
    
    private void writeBoard(File file, int[][] board) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int[] row : board) {
                for (int j = 0; j < 9; j++) {
                    bw.write(row[j] + (j < 8 ? " " : ""));
                }
                bw.newLine();
            }
        }
    }
    
    public Game loadGame(String folder) throws IOException {
              
        File file = new File(folder + "/" + GAME_FILE);
        if (!file.exists())
            throw new FileNotFoundException("Game not found");
       int[][] board = readBoard(file);
       return new Game(board);
    }
    
private int[][] readBoard(File file) throws IOException {
        int[][] board = new int[9][9];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 9; i++) {
                String line = br.readLine();
                if (line == null) break;

                String[] parts = line.trim().split("[,\\s]+");

                for (int j = 0; j < 9; j++) {
                    if (j < parts.length) {
                        try {
                            board[i][j] = Integer.parseInt(parts[j].trim());
                        } catch (NumberFormatException e) {
                            board[i][j] = 0; 
                        }
                    }
                }
            }
        }
        return board;
    }
public Game loadRawBoard(String fullPath) throws IOException {
        File file = new File(fullPath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found at: " + fullPath);
        }
        int[][] board = readBoard(file);
        return new Game(board);
    }
     
      public boolean hasGame(String folder) {
        return new File(folder + "/" + GAME_FILE).exists();
    }
      
      public void saveCurrentGame(Game game) throws IOException {
        saveGame(INCOMPLETE, game);
    }

    public Game loadCurrentGame() throws IOException {
        return loadGame(INCOMPLETE);
    }

    public boolean hasIncompleteGame() {
        return hasGame(INCOMPLETE);
    }
    
    public void clearCurrentGame() throws IOException {
        Files.deleteIfExists(Paths.get(INCOMPLETE, GAME_FILE));
        Files.deleteIfExists(Paths.get(INCOMPLETE, LOG_FILE));
    }
    
    public boolean hasAllDifficulties() {
        return hasAny(EASY) && hasAny(MEDIUM) && hasAny(HARD);
    }

    private boolean hasAny(String folder) {
        File f = new File(folder);
        return f.listFiles() != null && f.listFiles().length > 0;
    }
    
    public void deleteGame(String folder) throws IOException {
        Files.deleteIfExists(Paths.get(folder, GAME_FILE));
    }
   

}
