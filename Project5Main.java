import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.time.Duration;

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

public class Main {

	public static void main(String[] args) {
		
        if (args.length!=5) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);
        }
        
        try {
//   		The first argument is the array size the second one is filename for the reports file, the third 
//   		one is the filename to store the unsorted array and the fourth is the filename to store sorted array
        	
    		System.out.println("These are the command line arguments: " + args[1] + ", " + args[2] + ", " + args[3] + ", " + args[4]);
            // able to read in and write out to input and output files
    		File unsorted_output_file = new File(args[3]);
            PrintWriter out;
            out = new PrintWriter(unsorted_output_file);
		
//		Before any sorting begins, record the unsorted array into a new file
//		created with the name in the third place in the command line. After all sorting is over record the
//		final sorted array into a new file created with the name in the fourth place in command line.  
            
		// generates integer list of specified size and copy variables
		ArrayList<Integer> arrayList1 = QuickSorter.generateRandomList(Integer.valueOf(args[1]));
		ArrayList<Integer> arrayList2 = new ArrayList<Integer>(arrayList1.size());
		ArrayList<Integer> arrayList3 = new ArrayList<Integer>(arrayList1.size());
		ArrayList<Integer> arrayList4 = new ArrayList<Integer>(arrayList1.size());
		
		// output unsorted array to file
		for(int i:arrayList1) {
    		out.println(i);
    		out.flush();
    		// also add integers to array copies
    		arrayList2.add(i);
    		arrayList3.add(i);
    		arrayList4.add(i);
    	}
		out.close(); // close file

		Duration time1 = QuickSorter.timedQuickSort(arrayList1, QuickSorter.PivotStrategy.FIRST_ELEMENT);
		Duration time2 = QuickSorter.timedQuickSort(arrayList2, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
		Duration time3 = QuickSorter.timedQuickSort(arrayList3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
		Duration time4 = QuickSorter.timedQuickSort(arrayList4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
		
		// output sorted array to file
        File sorted_output_file = new File(args[4]);
        out = new PrintWriter(sorted_output_file);
        
		for(int i = 0; i < arrayList1.size(); i++) {
    		out.println(arrayList1.get(i));
    		out.flush();
    	}
		out.close(); // close file
		
		// output report to file
		File report_file = new File(args[2]);
        out = new PrintWriter(report_file);
        
        out.println("Array Size = " + arrayList1.size());
		out.flush();
		out.println("FIRST_ELEMENT : " + time1);
		out.flush();
		out.println("RANDOM_ELEMENT : " + time2);
		out.flush();
		out.println("MEDIAN_OF_THREE_RANDOM _ELEMENTS : " + time3);
		out.flush();
		out.println("MEDIAN_OF_THREE_ELEMENTS : " + time4);
		out.flush();
		
		out.close(); // close file
        
		System.out.println("Time 1: " + time1);
		System.out.println("Time 2: " + time2);
		System.out.println("Time 3: " + time3);
		System.out.println("Time 4: " + time4);
		
        } // end of try 
        catch(Exception e){
        	if(e.getMessage() != "No line found")
        		System.out.print("Exception: " + e.getMessage());
        } // end of catch
        
	} // end of function main

} // end of main class
