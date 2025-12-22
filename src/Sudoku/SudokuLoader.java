package Sudoku;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuLoader {
    private static final int Grid_Size = 9;


    public static int[][] readSudokuCsv(String filePath) {
        int[][] board = new int[Grid_Size][Grid_Size];
        String line;
        int row = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                if (row >= Grid_Size) {
                    throw new IllegalArgumentException("too many rows (expected 9)");
                }

                // Split line into values
                String[] values = line.split(",");

                if (values.length != Grid_Size) {
                    throw new IllegalArgumentException(
                            "The row "+(row + 1) +" has incorrect number of cells (expected 9)");
                }

                // Parse numbers in this row
                for (int col = 0; col < Grid_Size; col++) {
                    try {
                        board[row][col] = Integer.parseInt(values[col].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "Invalid formatting in row "+(row + 1) +" and Col " + (col + 1));
                    }
                }
                // move to next row
                row++;
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + filePath, e);
        }

        if (row < Grid_Size) {
            throw new IllegalArgumentException("too few rows (expected 9)");
        }
        return board;
    }

}