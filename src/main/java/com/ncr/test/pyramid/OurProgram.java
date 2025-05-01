package com.ncr.test.pyramid;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.impl.NaivePyramidSolver;

/**
 * TASK: This is your 1st task. 
 * Please look at the {@link com.ncr.test.pyramid.solver.impl.NaivePyramidSolver} and
 * <ul>
 *   <li>describe or fix the bugs and problems. Comment your changes.
 *   <li>describe or implement test scenarios you need to prove its correctness.
 * </ul>
 */
public class OurProgram {

    /**
     * (This doesn't get the correct number)
     * Fixed.
     */
    public static void main(String[] args) {
        // RandomPyramidGenerator is used only once, thus shortening the construction
        final Pyramid pyramid = new RandomPyramidGenerator(5, 99).generatePyramid();
        System.out.println(pyramid);
        System.out.println("This result was wrong, now it's fixed, please take a look at comments in code.");

        // Also the NaivePyramidSolver used only once, let's make it shorter and more readable.
        // And eliminating additional invocations of System.*
        System.out.println(
                new NaivePyramidSolver()
                        .pyramidMaximumTotal(pyramid)
                        + "\n"
        );
    }
}