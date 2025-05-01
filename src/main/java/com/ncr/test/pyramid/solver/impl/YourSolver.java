package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

public class YourSolver implements PyramidSolver {
    static long[][] proxyPyramid;

    // To evaluate the usefulness of proxying/caching
    static int cacheUpdates = 0;
    static int cacheFetches = 0;

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        // Prepare proxy pyramid, of square shape,
        // then rows==columns with initial values==0 (is used in occurrancy check, no need to set any values)
        int rows = pyramid.getRows();
        proxyPyramid = new long[rows][rows];

        long totalAbove = getTotalAbove(rows - 1, 0, pyramid);

        // let's evaluate the effectiveness - depends on the size of pyramid's matrix
        System.out.println("---\nCache updated: " + cacheUpdates);
        System.out.println("Cache successfully fetched: " + cacheFetches);
        double ratio = cacheUpdates != 0 ? (double) cacheFetches / cacheUpdates : 0.0;
        System.out.println("- with ratio: " + ratio);

        return totalAbove;
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        int myValue = pyramid.get(row, column);
        long proxyMax = proxyPyramid[row][column];
        if(proxyMax != 0){
            cacheFetches++;
            return proxyMax;
        } else {
            if (row == 0) return myValue;
            long left = myValue + getTotalAbove(row - 1, column, pyramid);
            long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
            long max = Math.max(left, right);
            proxyPyramid[row][column] = max;
            cacheUpdates++;
            return max;
        }
    }
}
