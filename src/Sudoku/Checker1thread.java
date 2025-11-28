package Sudoku;
import java.util.ArrayList;

public class Checker1thread implements Checker{
    
    public static void check(int[][] board) {
        boolean VALID= true;
        for (int row = 0; row < board.length; row++) {
            boolean valid = true;
            ArrayList<Integer> seen = new ArrayList<>();
            ArrayList<Integer> invalidCols = new ArrayList<>();

            for (int col = 0; col < board[0].length; col++) {
                int val = board[row][col];
                if (val == 0) continue;

                if (seen.contains(val)) {
                    invalidCols.add(col + 1);
                    valid = false;
                    VALID= false;
                } else {
                    seen.add(val);
                }
            }
            if (!valid) {
                System.out.print("ROW " + (row + 1) + ", #1, [");
                for (int i = 0; i < invalidCols.size(); i++) {
                    System.out.print(invalidCols.get(i));
                    if (i != invalidCols.size() - 1) System.out.print(", ");
                }
                System.out.println("]");
            }
        }

        for (int col = 0; col < board[0].length; col++) {
            boolean valid = true;
            ArrayList<Integer> seen = new ArrayList<>();
            ArrayList<Integer> invalidRows = new ArrayList<>();

            for (int row = 0; row < board.length; row++) {
                int val = board[row][col];
                if (val == 0) continue;

                if (seen.contains(val)) {
                    invalidRows.add(row + 1);
                    valid = false;
                    VALID= false;
                } else {
                    seen.add(val);
                }
            }

            if (!valid) {
                System.out.print("COL " + (col + 1) + ", #1, [");
                for (int i = 0; i < invalidRows.size(); i++) {
                    System.out.print(invalidRows.get(i));
                    if (i != invalidRows.size() - 1) System.out.print(", ");
                }
                System.out.println("]");
            }
        }
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {

                boolean valid = true;
                ArrayList<Integer> seen = new ArrayList<>();
                ArrayList<Integer> invalidCells = new ArrayList<>();
                int startRow = boxRow * 3;
                int startCol = boxCol * 3;

                //startRow = 0
                // starCol= 0

                for (int i = 0; i < 3; i++) {
                    int k=0;
                    for (int j = 0; j < 3; j++) {
                        int row = startRow + i;
                        int col = startCol + j;
                        int val = board[row][col];

                        if (val == 0) continue;
                        int cellIndex = i * 3 + j + 1;

                        if (seen.contains(val)) {
                            invalidCells.add(cellIndex);
                            valid = false;
                            VALID= false;
                        } else {
                            seen.add(val);
                        }
                        k+=3;
                    }
                }
                if (!valid) {
                    System.out.print("BOX " + ((boxRow * 3 + boxCol + 1)) + ", #1, [");
                    for (int k = 0; k < invalidCells.size(); k++) {
                        System.out.print(invalidCells.get(k));
                        if (k != invalidCells.size() - 1) System.out.print(", ");
                    }
                    System.out.println("]");
                }

            }
        }
        if(VALID){
            System.out.println("VALID");
        }
        else{
            System.out.println("INVALID");
        }
}
}