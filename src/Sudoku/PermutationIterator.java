package Sudoku;

import java.util.Iterator;

public class PermutationIterator implements Iterator<int[]> {

    private final int[] values;
    private boolean hasNext = true;

    public PermutationIterator(int size) {
        values = new int[size];
        for (int i = 0; i < size; i++)
            values[i] = 1;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public int[] next() {
        int[] current = values.clone();
        increment();
        return current;
    }

    private void increment() {
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] < 9) {
                values[i]++;
                return;
            }
            values[i] = 1;
        }
        hasNext = false;
    }
}
