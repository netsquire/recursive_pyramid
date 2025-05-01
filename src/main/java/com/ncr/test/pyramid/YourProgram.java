package com.ncr.test.pyramid;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.PyramidSolver;
import com.ncr.test.pyramid.solver.impl.YourSolver;

/**
 * TASK: This is your 2nd task. 
 * Make sure your algorithm will work well with bigger data as generated below.
 */
public class YourProgram {
    public static void main(String[] args) {
        // let's have bigger data (and money!!)
        final PyramidGenerator generator = new RandomPyramidGenerator(99, 10000);
        final Pyramid pyramid = generator.generatePyramid();
//        System.out.println(pyramid); // to slow down the spam on display

        final PyramidSolver solver = new YourSolver();
        System.out.println("Maximum path sum for this pyramid is :" + solver.pyramidMaximumTotal(pyramid));
    }
}
