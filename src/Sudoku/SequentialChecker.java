package Sudoku;

import java.util.ArrayList;

public class SequentialChecker implements Checker {

    @Override
    public void check(int[][] board) {
        boolean valid = true;
        ArrayList<String> errors = new ArrayList<>();

        // Here we check the list
        for (int row = 0; row < 9; row++) {
            ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
            for (int i = 0; i <= 9; i++) duplicates.add(new ArrayList<>());

            for (int col = 0; col < 9; col++) {
                int val = board[row][col];
                if (val < 1 || val > 9) continue;
                duplicates.get(val).add(col + 1);  // here I put every index of the variable in its array list
            }

            for (int val = 1; val <= 9; val++) {
                ArrayList<Integer> positions = duplicates.get(val);
                if (positions.size() > 1) {
                    errors.add("Row " + (row + 1) + ", value " + val + ": " + positions);
                    valid = false;
                }
            }
        }

        //Here we check the columns
        for (int col = 0; col < 9; col++) {
            ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
            for (int i = 0; i <= 9; i++) duplicates.add(new ArrayList<>());

            for (int row = 0; row < 9; row++) {
                int val = board[row][col];
                if (val < 1 || val > 9) continue;
                duplicates.get(val).add(row + 1);
            }

            for (int val = 1; val <= 9; val++) {
                ArrayList<Integer> positions = duplicates.get(val);
                if (positions.size() > 1) {
                    errors.add("Column " + (col + 1) + ", value " + val + ": " + positions);
                    valid = false;
                }
            }
        }

        //  Here we Check the boxes
        for (int box = 0; box < 9; box++) {
            int startRow = (box / 3) * 3;
            int startCol = (box % 3) * 3;

            ArrayList<ArrayList<Integer>> duplicates = new ArrayList<>();
            for (int i = 0; i <= 9; i++) duplicates.add(new ArrayList<>());

            int pos = 1;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    int val = board[startRow + r][startCol + c];
                    if (val >= 1 && val <= 9) {
                        duplicates.get(val).add(pos);
                    }
                    pos++;
                }
            }

            for (int val = 1; val <= 9; val++) {
                ArrayList<Integer> positions = duplicates.get(val);
                if (positions.size() > 1) {
                    errors.add("Box " + (box + 1) + ", value " + val + ": " + positions);
                    valid = false;
                }
            }
        }

        // Printing our results
        if (valid) {
            System.out.println("VALID");
        } else {
            for (String e : errors) {
                System.out.println(e);
            }
            System.out.println("INVALID");
        }
    }
}
