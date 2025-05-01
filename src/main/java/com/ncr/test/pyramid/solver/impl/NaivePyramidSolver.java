package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

public class NaivePyramidSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        int myValue = pyramid.get(row, column);

        // Main issue:
        // if (row == 0) return 0;
        // Original line ˆˆˆ returns zero instead of terminal leaf's value (myValue)
        if (row == 0) return myValue;

        // Dump (naive) invocation with checking for already calculated sum
        // See performance optimization in -> my solution, please!
        long left = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);

        return Math.max(left, right);
    }
}
