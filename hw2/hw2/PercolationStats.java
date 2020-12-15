package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;
    private double[] results;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T <= 0) {
            throw new IllegalArgumentException("The given grid size N or sample size T is invalid.");
        }
        trials = 0;
        results = new double[T];
        for (int i = 0; i < T; i++) {
            int numOpenSites = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
                numOpenSites = p.numberOfOpenSites();
            }
            results[i] = (double) numOpenSites / (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

}
