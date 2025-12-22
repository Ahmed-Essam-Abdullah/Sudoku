package Sudoku;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SudokuSolver {

    public static int[][] solve(int[][] board) throws InvalidGame {

        List<int[]> emptyCells = getEmptyCells(board);

        if (emptyCells.size() != 5)
            throw new InvalidGame("Solver requires exactly 5 empty cells");

        Iterator<int[]> it = new PermutationIterator(5);

        while (it.hasNext()) {
            int[] perm = it.next();

            if (SudokuVerifier.isValidWithPermutation(board, emptyCells, perm)) {
                return buildSolution(emptyCells, perm);
            }
        }

        throw new InvalidGame("No solution found");
    }

    private static List<int[]> getEmptyCells(int[][] board) {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == 0)
                    list.add(new int[]{i, j});

        return list;
    }

    // Output format: {x, y, value}
    private static int[][] buildSolution(List<int[]> empty, int[] perm) {
        int[][] result = new int[5][3];

        for (int i = 0; i < 5; i++) {
            result[i][0] = empty.get(i)[0];
            result[i][1] = empty.get(i)[1];
            result[i][2] = perm[i];
        }
        return result;
    }
}
