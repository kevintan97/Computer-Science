package ce152;
import java.util.Arrays;

import javax.lang.model.element.Element;

public class Exercise2 {
	public static int[] closestToMean (double[][] array){
		
		double sum = 0, counter = 0;
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length ; j++) {
				sum += array[i][j];
				counter++;
			}
		}
		double mean = (sum/counter);
		System.out.println("Mean = " + mean);
		
		
		int position[] = new int[2]; // initialise the result array
		double diff = Double.MAX_VALUE; // set diff = the largest value a double can take
		
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length ; j++) {
				if (diff > Math.abs(mean - array[i][j])){ // if the new difference is smaller than the previous difference, 
					diff = Math.abs(mean - array[i][j]);  // then set diff = the new difference
					position[0] = i;					  // and set the result[0] = row number
					position[1] = j;					  // and set the result[1] = column number
				} 
			}
		}
		return position;
	}
	
	public static void testClosestToMean() {
		double data[][] = new double[3][4];
		
		data[0][0] = 3;
		data[0][1] = -1;
		data[0][2] = -4;
		data[0][3] = 0;
		data[1][0] = 5;
		data[1][1] = -2;
		data[1][2] = 9;
		data[1][3] = 6;
		data[2][0] = 8;
		data[2][1] = 2;
		data[2][2] = 4;
		data[2][3] = -9;
		
		System.out.println("Result = " + Arrays.toString(closestToMean(data)));
		}	
	
	
	public static void main(String[] args) {
		testClosestToMean();
	}
}
		
