import java.util.Arrays;

public class BenchSort {

	/***
	 * main method for simulating the time difference between
	 * standard selection sort and java's Arrays.sort subroutine
	 * for an array of constant size.
	 * @author Dixit Bhatta
	 * @param args
	 */
	public static void main(String[] args) {
		// two arrays of same size being created
		int[] list1 = new int[100000]; 
		int[] list2 = new int[100000];
		int i, rnd; //variable for iteration and storing random value
		
		for(i=0; i < list1.length; i++){//loop until the arrays are full
			rnd = (int)(10 * Math.random()); //generate a random value
			//assign that value to both arrays
			list1[i] = rnd; 
			list2[i] = rnd;
		}//end of for loop
		
		System.out.println("For array of size 100000");
		//calculating and displaying time for selection sort
		long startTime1 = System.currentTimeMillis();//marks the start time
		selectionSort(list1);//observing timing for selection sort subroutine
		long runTime1 = System.currentTimeMillis() - startTime1; //total runtime for first sort
		System.out.println("Time for selection sort = " + runTime1/1000.0+ " seconds");
		
		//calculating and displaying time for Arrays.sort
		long startTime2 = System.currentTimeMillis();//marks the start time
		Arrays.sort(list2);//sort the array passed into the subroutine
		long runTime2 = System.currentTimeMillis() - startTime2;//total runtime for second sort
		System.out.println("Time for Arrays.sort = " + runTime2/1000.0 + " seconds");
	}//end of main method
	
	/*
	 * Selection Sort subroutine from textbook
	 */
	static void selectionSort(int[] A) {
		// Sort A into increasing order, using selection sort
		for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
		// Find the largest item among A[0], A[1], ...,
		// A[lastPlace], and move it into position lastPlace
		// by swapping it with the number that is currently
		// in position lastPlace.
			int maxLoc = 0; // Location of largest item seen so far.
			for (int j = 1; j <= lastPlace; j++) {
					if (A[j] > A[maxLoc]) {
					// Since A[j] is bigger than the maximum we’ve seen
					// so far, j is the new location of the maximum value
					// we’ve seen so far.
					maxLoc = j;
					}
			}
		int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
		A[maxLoc] = A[lastPlace];
		A[lastPlace] = temp;
		} // end of for loop
	}//end of sorting subroutine
	
}
