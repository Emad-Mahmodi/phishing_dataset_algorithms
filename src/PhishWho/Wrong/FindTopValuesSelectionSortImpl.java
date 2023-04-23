package PhishWho.Wrong;

import java.util.Arrays;

public class FindTopValuesSelectionSortImpl {

/**
 * Finds list of the highest 'n' values in the source list, ordered naturally, 
 * with the highest value at the start of the array and returns it 
 */
	
public int[] findTopNValues2(double[][] values, int n) {
    int length = values[0].length;

    for (int i=0; i<=n; i++) {
        int maxPos = i;
        for (int j=i+1; j<length; j++) {
        	double x1=values[0][j];
        	double x2= values[0][maxPos];

            if (values[0][j] > values[0][maxPos]) {
                maxPos = j;
            }
        }

        if (maxPos != i) {
            double maxValue = values[0][maxPos];
            double index = values[1][maxPos];
            values[0][maxPos] = values[0][i];
            values[0][i] = maxValue;
            
            values[1][maxPos] = values[1][i];
            values[1][i] = index;
        }           
    }
    int[] result=new int[values[0].length];
    for (int i = 0; i < values[0].length; i++) {
//		System.out.println((int)values[1][i]);
    	 result[i]=(int)values[1][i];
	}
//    System.out.println(result);
    return result;
    
    
}
}