//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 9/28/2019
//Assignment:      Project 3 - Binary Search Trees with Lazy Deletion
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements a binary search tree with lazy deletion. Other functions include insert(value), delete(value), findMin(not deleted),
// 			   findMax(not deleted), contains(value), toString(preorder traversal), height(longest length), and size(number of nodes). 


import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in;
        if (args.length!=2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);
        }
        
        try {
            // able to read in and write out to input and output files
            File input_file = new File(args[0]);
            in = new Scanner(input_file);
            File output_file = new File(args[1]);
            PrintWriter out;
            out = new PrintWriter(output_file);

            // create linked list object
            LazyBinarySearchTree bst = new LazyBinarySearchTree();

            String operation = "";

            // create variables for each Item read in from the file
            int value = 0;

            while (in.hasNext()) {
            	
            	value = -1201;
                operation = in.next();

                // find if line also has a number to be read in
                if(operation.contains(":")) {
                	value = Integer.valueOf(operation.substring(operation.indexOf(":")+1));
                	operation = operation.substring(0, operation.indexOf(":"));
                }
                
                // preceding actions depend on command
                switch (operation) {

                    // insert reads in integers for ID and price
                    case "Insert":
                        try {
                        	if (value == -1201) {
                        		out.println("Error in line: " + operation);
                        		out.flush();
                        		in.nextLine();
                        		break;
                        	}
                        	// input validation for values between 1 and 99
                        	else if (value < 1 || value > 99)
                        		throw new IllegalArgumentException();
                        	
                        	out.println(bst.insert(value));
                        	out.flush();
                        }
                        catch (IllegalArgumentException j){
                            out.println("Error in insert: IllegalArgumentException raised");
                            out.flush();
                        }
                        break;
                        
                    case "Delete":
                        try {
                        	if (value == -1201) {
                        		out.println("Error in line: " + operation);
                        		out.flush();
                        		in.nextLine();
                        		break;
                        	}
                        	// input validation for values between 1 and 99
                        	if (value < 1 || value > 99)
                        		throw new IllegalArgumentException();
                        	
                            out.println(bst.delete(value));
                            out.flush();
                        }
                        catch (IllegalArgumentException j){
                            out.println("Error in insert: IllegalArgumentException raised");
                            out.flush();
                        }
                        break;

                    case "FindMin":
                        out.println(bst.findMin());
                        out.flush();
                        break;
                        
                    case "FindMax":
                    	out.println(bst.findMax());
                    	out.flush();
                        break;
                        
                    case "Contains":
                    	try {
                    		if (value == -1201) {
                        		out.println("Error in line: " + operation);
                        		out.flush();
                        		in.nextLine();
                        		break;
                        	}
	                    	// input validation for values between 1 and 99
	                    	if (value < 1 || value > 99)
	                    		throw new IllegalArgumentException();
	                    	out.println(bst.contains(value));
	                    	out.flush();
                    	}
                        catch (IllegalArgumentException j){
                            out.println("Error in insert: IllegalArgumentException raised");
                            out.flush();
                        }
                        break;
                        
                    case "PrintTree":
                    	try{ 
                    		out.println(bst.toString());
                    		out.flush();
                        break;
                    	}
                    	catch (NullPointerException n) {
                    		out.println("Error in print tree");
                    		out.flush();
                    	}
                        
                    case "Height":
                    	out.println(bst.height());
                    	out.flush();
                        break;
                        
                    case "Size":
                    	out.println(bst.size());
                    	out.flush();
                        break;

                    default:
                        out.println("Error in line: " + operation);
                        out.flush();
                        in.nextLine();

                } // end switch statement
                
            } // end while loop which reads and processes each line in input file

            // close input and output file objects
            in.close();
            out.close();

        }
        catch(Exception e){
        	if(e.getMessage() != "No line found")
        		System.out.print("Exception: " + e.getMessage());
        }

    } // end function main

} // end main function
