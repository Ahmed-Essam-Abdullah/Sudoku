public static void main(String[] args) {
    String fileName = "src/resources/sudoku.csv";
    int[][] board=null;
    try{
        board = SudokuLoader.readSudokuCsv(fileName);
        System.out.println("Sudoku is loaded successfully");
        for(int[] row:board){
            System.out.println(Arrays.toString(row));
        }
    }  catch (IllegalArgumentException e) {
        System.err.println("Error loading Sudoku puzzle: " + e.getMessage());
        e.printStackTrace();
    }
    Checker.check(board);
}