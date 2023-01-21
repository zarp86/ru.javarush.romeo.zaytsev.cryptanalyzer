package utils;

public class MyArraysUtil {
    public static int findIndexOfMaxInIntArray(int[] arrayOfInts) {
        int max = arrayOfInts[0];
        int indexOfMax = 0;
        for (int i = 1; i < arrayOfInts.length; i++) {
            if (max < arrayOfInts[i]) {
                max = arrayOfInts[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

}
