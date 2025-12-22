package Sudoku;
import java.util.List;

public class GameGenerator {

    public static void generateAll(int[][] source,
                                   int[][] easy,
                                   int[][] medium,
                                   int[][] hard)
            throws SolutionInvalidException {

        if (!SudokuVerifier.isValidAndComplete(source)) {
            throw new SolutionInvalidException("Source solution invalid or incomplete");
        }

        copyBoard(source, easy);
        copyBoard(source, medium);
        copyBoard(source, hard);

        RandomPairs rp = new RandomPairs();

        removeCells(easy, rp.generateDistinctPairs(10));
        removeCells(medium, rp.generateDistinctPairs(20));
        removeCells(hard, rp.generateDistinctPairs(25));
    }

    private static void copyBoard(int[][] src, int[][] dst) {
        for (int i = 0; i < 9; i++)
            System.arraycopy(src[i], 0, dst[i], 0, 9);
    }

    private static void removeCells(int[][] board, List<int[]> pairs) {
        for (int[] p : pairs) {
            board[p[0]][p[1]] = 0;
        }
    }
}

