import java.io.File;
import java.io.PrintWriter;
import java.util.*;

//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 12/6/2019
//Assignment:      Project 6 - Plan a Flight
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements several variations of the in-place QuickSort algorithm, each with a different choice of 
//             pivot. The program also outputs the sorted list, unsorted list, and run time for each pivot selection 
//             to files listed in the command line execution. 


public class Main {
	
	public static void main(String[] args) {
			
			Scanner in;
	        if (args.length!=3) {
	            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
	            System.exit(0);
	        }
	        try {
	        	// able to read in and write out to input and output files
	            File input_file = new File(args[0]);
	            in = new Scanner(input_file);
	            in.useDelimiter("[|]");
	            
	            while(in.hasNext()) {
	            	// read in values for departure and arrival locations, cost, and time
	            	String fromCity = in.next();
	            	System.out.println(fromCity);
	            	String toCity = in.next();
	            	System.out.println(toCity);
	            	int cost = Integer.valueOf(in.next());
	            	System.out.println(cost);
	            	int time = Integer.valueOf(in.next());
	            	System.out.println(time);
	            	
	            	System.out.println(fromCity + " " + toCity + " " + cost + " " + time);
	            	
	            } // end while loop, end of file
	            in.close();
	            
	            File output_file = new File(args[2]);
	            PrintWriter out;
	            out = new PrintWriter(output_file);
	            out.close();
	        } // end of try 
	        catch(Exception e){
	        	if(e.getMessage() != "No line found")
	        		System.out.print("Exception: " + e.getMessage());
	        } // end of catch
	        
	} // end of function main
	
} // end of Main Class