/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Khaled
 */
public class UndoManager {
     private final Path logPath;
     
     public UndoManager(String incompleteFolderPath) {
        this.logPath = Paths.get(incompleteFolderPath, "log.txt");
    }
     
      public void logMove(String moveEntry) throws IOException {
        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(logPath.toFile(), true))) {
            bw.write(moveEntry);
            bw.newLine();
        }
    }
      
      public boolean undo(Game game, StorageManager storage) throws IOException {

        if (!Files.exists(logPath))
            return false;

        List<String> lines = Files.readAllLines(logPath);
        if (lines.isEmpty())
            return false;

        String lastEntry = lines.get(lines.size() - 1);
        String[] parts = lastEntry.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        int prev = Integer.parseInt(parts[3].trim());

        game.board[x][y] = prev;
        
        lines.remove(lines.size() - 1);
        Files.write(logPath, lines);

        storage.saveCurrentGame(game);

        return true;
    }
}
