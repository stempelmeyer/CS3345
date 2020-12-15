//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 11/17/2019
//Assignment:      Project 5 - QuickSort
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements several variations of the in-place QuickSort algorithm, each with a different choice of 
//             pivot. The program also outputs the sorted list, unsorted list, and run time for each pivot selection 
//             to files listed in the command line execution. 
// 			   		An example is "./Main_Class 100000 report.txt unsorted.txt sorted.txt"

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class QuickSorter
{

    /*
     * This private constructor is optional, but it does help to prevent accidental client instantiation of QuickSorter
     * via the default constructor.  (defining any constructor prevents the compiler from creating a default constructor)
     * This particular anti-instantiation technique is exactly what {@link java.util.Collections} does.
     */

    // throws null pointer exception
    // returns time in nanoseconds that it took to sort list 
    // list is either non-empty or null
    public static <E extends Comparable<E>>Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy)
    {
		try {
			if(list == null || pivotStrategy == null)
				throw new NullPointerException();
			
			long startTime = System.nanoTime();
			long endTime;
			
			// send to quicksort function to be sorted
			quickSort(pivotStrategy, list, 0, list.size() - 1);
			
			endTime = System.nanoTime();
			
			// return Duration.between(startTime, endTime); when Duration is return type
			return Duration.ofNanos(endTime - startTime);
		}
		catch (NullPointerException e) {
			System.out.println("Null Pointer Exception thrown in timedQuickSort");
			return null;
		}
    }

    // throws illegal argument excetion
    public static ArrayList<Integer> generateRandomList(int size)
    {
		try {
			if (size <= 0)
				throw new IllegalArgumentException();
			
			// create random upper bound for array list integers
			Random random = new Random();
			
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++)
			{
				// adds to list with an integer between 0 and the random upper bound
				list.add(random.nextInt(100000-(-100000) + 1) - 100000); 
			}
			
			return list;	
		}
		catch (IllegalArgumentException i)
		{
			System.out.println("Illegal Argument Exception thrown in generateRandomList");
			return new ArrayList<Integer>(0);
		}
    }
    
    // this private method prints the list to user console
    private static <E extends Comparable<E>> void printList(ArrayList<E> list) {
    	// use iterator to print list
    	for(int i = 0; i < list.size(); i++) {
    		System.out.printf("%9s",list.get(i));
    	}
    	System.out.println();
    }
    
    // sorts small array (< 20 elements) by using insertion sort and returns sorted array
    private static <E extends Comparable<E>> ArrayList<E> insertionSort(ArrayList<E> list, int left, int right)
    {
    	int j;
    	
    	// traverses through array once selecting element to insert
    	for (int i = left+1; i < right+1; i++) {
    		E temp = list.get(i);
    		
    		// traverses through previous elements of array to insert selected element
    		for(j = i; j > left && temp.compareTo(list.get(j-1)) < 0; j--) {
    			// inserts element when at beginning of array (j=0) or when element to insert is less than traversing inner pointer
    			list.set(j, list.get(j-1));
    		}
    		
    		// will if element to insert is larger than the sorted elements before, do not swap/do not enter inner for loop
    		list.set(j, temp);
    	}
    	
    	return list;
    }
    
    // selects and returns pivot using the median of random three elements strategy
    private static <E extends Comparable<E>> E pivotMedianOfRandom(ArrayList<E> list, int left, int right) {
    	// select random element three times
		Random rand = new Random();
		E element1 = list.get(rand.nextInt(right-left+1)+left); // what if random element chosen is the same as another??
		E element2 = list.get(rand.nextInt(right-left+1)+left);
		E element3 = list.get(rand.nextInt(right-left+1)+left);
		
		//  element 1 is the pivot
		if (element1.compareTo(element2) < 0 && element1.compareTo(element3) > 0)        // element 3 < element 1 < element 2
			return element1;
		else if (element1.compareTo(element2) > 0 && element1.compareTo(element3) < 0)   // element 2 < element 1 < element 3
			return element1;
		
		// element 2 is the pivot
		else if (element2.compareTo(element1) < 0 && element2.compareTo(element3) > 0)   // element 3 < element 2 < element 1
			return element2;
		else if (element2.compareTo(element1) > 0 && element2.compareTo(element3) < 0)   // element 1 < element 2 < element 3
			return element2;
		
		// element 3 is the pivot
		else if (element3.compareTo(element1) < 0 && element3.compareTo(element2) > 0)   // element 2 < element 3 < element 1
			return element3;
		else 																		     // element 1 < element 3 < element 2
			return element3;
    }

    // selects and returns pivot using the median of three elements strategy
    private static <E extends Comparable<E>> E pivotMedianOfThree(ArrayList<E> list, int left, int right) {
    	// assign first, middle,and last elements of the array
		E element1 = list.get(left); 
		E element2 = list.get((left+right)/2);
		E element3 = list.get(right);
		
		//  element 1 is the pivot
		if (element1.compareTo(element2) < 0 && element1.compareTo(element3) > 0)        // element 3 < element 1 < element 2
			return element1;
		else if (element1.compareTo(element2) > 0 && element1.compareTo(element3) < 0)   // element 2 < element 1 < element 3
			return element1;
		
		// element 2 is the pivot
		else if (element2.compareTo(element1) < 0 && element2.compareTo(element3) > 0)   // element 3 < element 2 < element 1
			return element2;
		else if (element2.compareTo(element1) > 0 && element2.compareTo(element3) < 0)   // element 1 < element 2 < element 3
			return element2;
		
		// element 3 is the pivot
		else if (element3.compareTo(element1) < 0 && element3.compareTo(element2) > 0)   // element 2 < element 3 < element 1
			return element3;
		else 																		     // element 1 < element 3 < element 2
			return element3;
    }
    
    // recursive function for quicksort
    private static <E extends Comparable <E>> void quickSort(PivotStrategy strategy, ArrayList<E> list, int left, int right) {
    	
    	E pivotElement = null;
    	
    	// also check to make sure in array bounds
    	if (left + 20 < right) 
    	{
    		// this switch statement selects the pivot
	    	switch (strategy) {
	    	
				case FIRST_ELEMENT:
					pivotElement = list.get(left); // call pivot selection function for first element
//					System.out.println("This is the first pivot element selected: " + pivotElement);
					// send list and pivot element to recursive function
					break;
				
				case RANDOM_ELEMENT:
					Random rand = new Random();
					pivotElement =list.get(rand.nextInt(right-left+1)+left); // call pivot selection function for random element
//					System.out.println("This is the random pivot element selected: " + pivotElement);
					break;
					
				case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
					pivotElement = pivotMedianOfRandom(list, left, right); // call pivot selection function for three random element
//					System.out.println("This is the median random pivot element selected: " + pivotElement);
					break;
					
				case MEDIAN_OF_THREE_ELEMENTS:
					pivotElement = pivotMedianOfThree(list, left, right);
//					System.out.println("This is the median of 3 pivot element selected: " + pivotElement);
					break;
					
				default: 
					System.out.println("No pivot strategy was chosen");
					
				} // end switch statement
	    	
	    	// move pivot to the rightmost element of the array (subarray)
	    	int pivotLocation = list.indexOf(pivotElement);
	    	E temp = list.get(right);
	    	list.set(right, pivotElement);
	    	list.set(pivotLocation, temp);
	    	
			// partition based off of pivot
			int i = left;
			int j = right - 1; // because pivot is moved to the right position

			for (;;) {
				// keep moving i to the right while i < pivotElement
				while(i < list.indexOf(pivotElement) && list.get(i).compareTo(pivotElement) < 0) {
					i++;
				}
				// keep moving j to the left while j > pivotElement
				while(j > 0 && list.get(j).compareTo(pivotElement) > 0) {
					j--; 
				}
				if (list.get(i) == null || list.get(j) == null) { // if either traversal element is past the array bounds, break
						System.out.println("i or j pointer is null");
						break;
				}
				else if (i < j) {
					// swap elements if i < j
			    	E swap = list.get(j);
			    	list.set(j, list.get(i));
			    	list.set(i, swap);
				} 
				else { // traversal elements are not null and i > j
					break;
				}
			} // end for loop
	    	
	    	if (list.get(i) != pivotElement) {
					// restore pivot (swap i with the pivot)
					E swap = list.get(right);
			    	list.set(right, list.get(i));
			    	list.set(i, swap);
	    	}
	    	
	    	// recursive call with left and right subarrays to pivot
	    	quickSort(strategy, list, left, i-1); 
	    	quickSort(strategy, list, i+1, right);
    	} // end of if left <= right
    	// insertion sort if array or subarray has 20 elements or less
    	else {
    		insertionSort(list, left, right);
    	}
    	return ;
    }
    
    public static enum PivotStrategy
    {
        FIRST_ELEMENT, 						// first element as pivot
        RANDOM_ELEMENT,						// randomly choose pivot element
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,	// choose median of 3 randomly chosen elements as pivot
        MEDIAN_OF_THREE_ELEMENTS			// median of first, center, and last element (book method)
    }

}
