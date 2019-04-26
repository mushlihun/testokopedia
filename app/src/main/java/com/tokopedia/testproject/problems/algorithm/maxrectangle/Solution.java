package com.tokopedia.testproject.problems.algorithm.maxrectangle;

import java.util.Stack;

public class Solution {

    public static int maxRect(int[][] matrix) {
        // TODO, return the largest area containing 1's, given the 2D array of 0s and 1s
        // below is stub
        return maxRectProcess(matrix);
    }

    private static int maxRectProcess(int[][] matrix) {

        int row = matrix.length;
        int column = matrix[0].length;
        int result = maxHist(column, matrix[0]);

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++)

                if (matrix[i][j] == 1) matrix[i][j] += matrix[i - 1][j];

            result = Math.max(result, maxHist(column, matrix[i]));
        }

        return result;
    }

    private static int maxHist(int column, int row[]) {

        Stack<Integer> result = new Stack<Integer>();

        int top_val;

        int max_area = 0;

        int area;

        int i = 0;
        while (i < column) {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);
            else {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;

                if (!result.empty())
                    area = top_val * (i - result.peek() - 1);
                max_area = Math.max(area, max_area);
            }
        }


        while (!result.empty()) {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;
            if (!result.empty())
                area = top_val * (i - result.peek() - 1);

            max_area = Math.max(area, max_area);
        }
        return max_area;
    }


}