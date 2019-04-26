package com.tokopedia.testproject.problems.algorithm.continousarea;

import java.util.ArrayList;

public class Solution {
    private static int row = 0;
    private static int column = 0;
    private static int count = 0;
    private static ArrayList<Integer> countArray;



    public static int maxContinuousArea(int[][] matrix) {
        // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        initVar(matrix);

        if (column > row) {
            swap(matrix, column, row);
        } else {
            loopMatrix(matrix, column, row);

        }

        return findHighResult();
    }


    private static void initVar(int[][] matrix){
        countArray = new ArrayList<>();
        row = matrix.length;
        column = matrix[0].length;
    }

    private static void swap(int[][] screen, int column, int row) {
        int temp = column;
        column = row;
        row = temp;
        loopMatrix(screen, column, row);
    }


    private static void loopMatrix(int[][] screen, int column, int row) {

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                try {
                    floodFill(screen, i, j, column, row);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private static void floodFillUtil(int[][] screen, int x, int y, int prevC, int a, int b) {


        if (x < 0 || x >= a || y < 0 || y >= b) {
            return;
        }
        if (screen[x][y] != prevC) {
            return;
        }
        screen[x][y] = 99;

        count = count + 1;
        floodFillUtil(screen, x + 1, y, prevC, a, b);
        floodFillUtil(screen, x - 1, y, prevC, a, b);
        floodFillUtil(screen, x, y + 1, prevC, a, b);
        floodFillUtil(screen, x, y - 1, prevC, a, b);
    }


    private static void floodFill(int[][] screen, int x, int y, int ncolumn, int nrow) {
        count = 0;
        int[][] target = new int[row][column];
        for (int i = 0; i < row; i++) {
            System.arraycopy(screen[i], 0, target[i], 0, screen[i].length);
        }

        int prevC = target[x][y];
        floodFillUtil(target, x, y, prevC, ncolumn, nrow);
        countArray.add(count);
    }


    private static int findHighResult(){
        int result = 0;
        for (int i = 0; i < countArray.size(); i++) {
            if (countArray.get(i) > result) {
                result = countArray.get(i);
            }
        }
        return result;
    }

}
