package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;
import com.ncr.test.pyramid.utils.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * TASK: There is something wrong here. A few things actually...
 */
public class NaivePyramidSolver implements PyramidSolver {
    static int shift = 0;
    static Map<Coord, Long> cached = new HashMap<>();

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        var row = pyramid.getRows() - 1;
        System.out.println("---(OUT)---\nROWS: " + row);
        return getTotalAbove(row, 0, pyramid);
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        Coord coord = new Coord(row, column);
        int myValue = pyramid.get(row, column);
        Long valueInCache = cached.get(coord);
        if (valueInCache != null) {
            return valueInCache;
        } else {
            shift += 2;
            String prefix = Util.repeatChar(' ', shift);
            System.out.println(prefix + " >>> getTotalAbove(" + row + ", " + column + ")");

            if (row == 0) return myValue;

            System.out.println(prefix + "   gotten from (" + row + ":" + column + "), myValue=" + myValue);

            var above_left = getTotalAbove(row - 1, column, pyramid);
            long left = myValue + above_left;
            System.out.println(prefix + "   left >> (" + row + ":" + column + "), myValue=" + myValue + ", above_left=" + above_left + "; left sum=" + left);

            var above_right = getTotalAbove(row - 1, column + 1, pyramid);
            long right = myValue + above_right;
            System.out.println(prefix + "   right >> (" + row + ":" + column + "), myValue=" + myValue + ", above_right=" + above_right + "; right sum: " + right);

            long max = Math.max(left, right);
            System.out.println(prefix + "--- local max: " + max);

//            OwnValue ownValue = new OwnValue(myValue, max);
            cached.put(coord, max);
            return max;
        }
    }

    static class OwnValue {
        long value;
        long max;

        public OwnValue(long value, long sum) {
            this.value = value;
            this.max = sum;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }
    }

    static class Coord {
        int row;
        int column;

        public Coord(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }
    }
}