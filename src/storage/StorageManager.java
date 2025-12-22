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
    private static final String GAME_FILE = "game.txt";
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
    
    public static void main(String[] args) {
        System.out.println("=== Starting StorageManager Test ===\n");
        StorageManager storage = new StorageManager();

        // 1. اختبار قراءة ملف CSV (بفواصل)
        System.out.println("--- Test 1: Loading CSV File ---");
        try {
            // أ. صناعة ملف CSV للتجربة
            String csvFilename = "test_sudoku.csv";
            createDummyCSV(csvFilename);
            System.out.println("Created dummy file: " + csvFilename);

            // ب. محاولة قراءة الملف باستخدام loadRawBoard
            System.out.println("Attempting to load via loadRawBoard...");
            File f = new File(csvFilename);
            Game loadedGame = storage.loadRawBoard(f.getAbsolutePath());

            // ج. طباعة النتيجة
            printBoard(loadedGame.getBoard());
            System.out.println("✅ CSV Test Passed!\n");

        } catch (IOException e) {
            System.err.println("❌ CSV Test Failed: " + e.getMessage());
            e.printStackTrace();
        }

        // 2. اختبار الحفظ والقراءة العادي (للتأكد إننا مبوظناش القديم)
        System.out.println("--- Test 2: Standard Save/Load ---");
        try {
            int[][] sampleBoard = new int[9][9];
            sampleBoard[0][0] = 5; // قيمة للتجربة
            Game g = new Game(sampleBoard);

            storage.saveGame(storage.getEasyPath(), g);
            System.out.println("Saved game to Easy folder.");

            Game loaded = storage.loadGame(storage.getEasyPath());
            if (loaded.getBoard()[0][0] == 5) {
                System.out.println("✅ Standard Load/Save Passed!");
            } else {
                System.out.println("❌ Value mismatch in standard load.");
            }

        } catch (IOException e) {
            System.err.println("❌ Standard Test Failed: " + e.getMessage());
        }
    }

    // دالة مساعدة لعمل ملف CSV
    private static void createDummyCSV(String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // بورد سودوكو بسيط مفصول بفواصل (,)
            bw.write("9,6,3,1,7,4,2,5,8"); bw.newLine();
            bw.write("1,7,8,3,2,5,6,4,9"); bw.newLine();
            bw.write("2,5,4,6,8,9,7,3,1"); bw.newLine();
            bw.write("8,2,1,4,3,7,5,9,6"); bw.newLine();
            bw.write("4,9,6,8,5,2,3,1,7"); bw.newLine();
            bw.write("7,3,5,9,6,1,8,2,4"); bw.newLine();
            bw.write("5,8,9,7,1,3,4,6,2"); bw.newLine();
            bw.write("3,1,7,2,4,6,9,8,5"); bw.newLine();
            bw.write("6,4,2,5,9,8,1,7,3"); bw.newLine();
        }
    }

    // دالة لطباعة البورد في الكونسول
    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
