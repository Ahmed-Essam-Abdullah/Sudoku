package Sudoku;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuVerifier {

    public static boolean isValidAndComplete(int[][] board) {
        return isValid(board) && !hasZero(board);
    }

    private static boolean hasZero(int[][] board) {
        for (int[] row : board)
            for (int v : row)
                if (v == 0) return true;
        return false;
    }

    public static boolean isValid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!checkRow(board, i)) return false;
            if (!checkCol(board, i)) return false;
            if (!checkBox(board, i / 3 * 3, i % 3 * 3)) return false;
        }
        return true;
    }

    public static boolean isValidWithPermutation(int[][] board,
                                                 List<int[]> empty,
                                                 int[] perm) {

        for (int i = 0; i < 9; i++) {
            if (!checkRow(board, i, empty, perm)) return false;
            if (!checkCol(board, i, empty, perm)) return false;
        }

        for (int r = 0; r < 9; r += 3)
            for (int c = 0; c < 9; c += 3)
                if (!checkBox(board, r, c, empty, perm)) return false;

        return true;
    }

    // I put here the Checkers (for normal and flyweight)

    private static boolean checkRow(int[][] b, int r) {
        return checkSet(getRow(b, r));
    }

    private static boolean checkCol(int[][] b, int c) {
        return checkSet(getCol(b, c));
    }

    private static boolean checkBox(int[][] b, int r, int c) {
        return checkSet(getBox(b, r, c));
    }

    private static boolean checkRow(int[][] b, int r,
                                    List<int[]> e, int[] p) {
        return checkSet(getRow(b, r, e, p));
    }

    private static boolean checkCol(int[][] b, int c,
                                    List<int[]> e, int[] p) {
        return checkSet(getCol(b, c, e, p));
    }

    private static boolean checkBox(int[][] b, int r, int c,
                                    List<int[]> e, int[] p) {
        return checkSet(getBox(b, r, c, e, p));
    }

    // any duplicates I return false by the checkSet
    private static boolean checkSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int v : arr) {
            if (v == 0) continue;
            if (!set.add(v)) return false;
        }
        return true;
    }

    // Our Flyweight getters

    private static int[] getRow(int[][] b, int r) {
        return b[r];
    }

    private static int[] getCol(int[][] b, int c) {
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) col[i] = b[i][c];
        return col;
    }

    private static int[] getBox(int[][] b, int r, int c) {
        int[] box = new int[9];
        int k = 0;
        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                box[k++] = b[i][j];
        return box;
    }

    private static int getValue(int[][] b, int i, int j,
                                List<int[]> e, int[] p) {
        if (b[i][j] != 0) return b[i][j];
        for (int k = 0; k < e.size(); k++)
            if (e.get(k)[0] == i && e.get(k)[1] == j)
                return p[k];
        return 0;
    }

    private static int[] getRow(int[][] b, int r,
                                List<int[]> e, int[] p) {
        int[] row = new int[9];
        for (int j = 0; j < 9; j++)
            row[j] = getValue(b, r, j, e, p);
        return row;
    }

    private static int[] getCol(int[][] b, int c,
                                List<int[]> e, int[] p) {
        int[] col = new int[9];
        for (int i = 0; i < 9; i++)
            col[i] = getValue(b, i, c, e, p);
        return col;
    }

    private static int[] getBox(int[][] b, int r, int c,
                                List<int[]> e, int[] p) {
        int[] box = new int[9];
        int k = 0;
        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                box[k++] = getValue(b, i, j, e, p);
        return box;
    }
}
