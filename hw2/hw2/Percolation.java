package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author: Jingjing */

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF wuf;
    private WeightedQuickUnionUF wufNoBottom; // to avoid backwash
    private int numOpenSites;
    private int[][] directions = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {0, 1}};

    // create N-by-N grid, with all sited initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("The given size N is invalid!");
        }
        grid = new boolean[N][N];
        size = N;
        // virtual top and bottom grid
        top = 0;
        bottom = N * N + 1;
        wuf = new WeightedQuickUnionUF(N * N + 2);
        wufNoBottom = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < size; i++){
            wuf.union(top, toInt(0, i));
            wufNoBottom.union(top, toInt(0, i));
            wuf.union(bottom, toInt(size-1, i));
        }
    }
    // transform 2d position to 1d
    private int toInt(int row, int col) {
        return row * size + col + 1;
    }
    // validate
    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size){
            throw new IndexOutOfBoundsException("The input row/col is invaid!");
        }
    }
    // Open the site (row, col) if it is blocked
    public void open(int row, int col) {
        validate(row, col);
        if (! isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
            if (row == 0) {
                wuf.union(top, toInt(row, col));
                wufNoBottom.union(top, toInt(row, col));
            } else if (row == size - 1) {
                wuf.union(bottom, toInt(row, col));
            }
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (0 <= newRow && newRow < size && 0 <= newCol && newCol < size && isOpen(newRow, newCol)) {
                    wuf.union(toInt(row, col), toInt(newRow, newCol));
                    wufNoBottom.union(toInt(row, col), toInt(newRow, newCol));
                }
            }
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return wufNoBottom.connected(top, toInt(row, col));
    }
    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return wuf.connected(top, bottom);
    }

    // use for unit testing
    public static void main(String[] args) {
        Percolation pclt = new Percolation(3);
        pclt.open(0, 0);
        assertEquals(false, pclt.percolates());
        assertFalse(pclt.isFull(1, 2));
        pclt.open(1, 0);
        pclt.open(2, 0);
        assertEquals(true, pclt.percolates());
        assertTrue(pclt.isFull(1, 0));
        pclt.open(2, 2);
        pclt.open(1, 1);
        assertTrue(pclt.isFull(1, 1));
        assertEquals(false, pclt.isFull(2, 2));
        assertTrue(pclt.isFull(1, 0));
        Percolation pclt2 = new Percolation(1);
        pclt2.open(0, 0);
        assertEquals(true, pclt.percolates());
        assertEquals(true, pclt.isFull(0, 0));
    }

}
