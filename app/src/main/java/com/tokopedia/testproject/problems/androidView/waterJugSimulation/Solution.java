package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static boolean simulateWaterJug(int jug1, int jug2, int target) {
//    public static List<WaterJugAction> simulateWaterJug(int jug1, int jug2, int target) {
        // TODO, simulate the smallest number of action to do the water jug problem
        // below is stub, replace with your implementation!
        //limit brought by the statement that water is finallly in one or both buckets
        if(jug1 + jug2 < target) return false;
        //case x or y is zero
        if( jug1 == target || jug2 == target || jug1 + jug2 == target )

            return true;


        //get GCD, then we can use the property of Bézout's identity
        return target%GCD(jug1, jug2) == 0;

//        List<WaterJugAction> list = new ArrayList<>();
//        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
//        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
//        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
//        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
//        list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));
//        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
//        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
//        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
//        return list;

    }

    public static int GCD(int jug1, int jug2){
        while(jug2 != 0 ){
            int temp = jug2;
            jug2 = jug1%jug2;
            jug1 = temp;
        }
        return jug1;
    }
}
