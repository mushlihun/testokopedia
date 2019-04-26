package com.tokopedia.testproject.problems.algorithm.waterJug;

public class Solution {

    public static int minimalPourWaterJug(int jug1, int jug2, int target) {
        // TODO, return the smallest number of POUR action to do the water jug problem
        // below is stub, replace with your implementation!
        return minSteps(jug1, jug2,target)/2;
    }

    private static int minSteps(int jug1, int jug2, int target)
    {

        if (target > jug2)
        {
            return -1;
        }


        if ((target % gcd(jug2, jug1)) != 0)
        {
            return -1;
        }

        return Math.min(pour(jug2, jug1, target), pour(jug1, jug2, target));
    }

    private static int gcd(int jug2, int jug1)
    {
        if (jug1 == 0)
        {
            return jug2;
        }
        return gcd(jug1, jug2 % jug1);
    }


    private static int pour(int fromCap, int toCap, int target)
    {
        int from = fromCap;
        int to = 0;
        int step = 1;

        while (from != target && to != target)
        {
            int temp = Math.min(from, toCap - to);
            to += temp;
            from -= temp;
            step++;

            if (from == target || to == target)
            {
                break;
            }

            if (from == 0)
            {
                from = fromCap;
                step++;
            }

            if (to == toCap)
            {
                to = 0;
                step++;
            }
        }
        return step;
    }

}