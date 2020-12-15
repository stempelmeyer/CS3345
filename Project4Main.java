//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 10/16/2019
//Assignment:      Project 4 - Simplified Red-Black Trees
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements a red-black tree data structure. Other functions include insert(value), contains(value), 
//             toString(preorder traversal), rebalance(Node), singleRight(Node) rotation, singleLeft(Node) rotation, 
//             doubleRight(Node) rotation, doubleLeft(Node) rotation.


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
            RedBlackTree rbt = new RedBlackTree();
            
            // read in first line
            String operation = in.next(); 
            
            // output error if reading in Objects that are not Integers or Strings
            if (operation.compareTo("Integer") != 0 && operation.compareTo("String") != 0) { 
            	out.print("Only works for objects Integers and Strings");
            	out.flush();
            	return;
            }
 
            int value = 0;
            if (operation.compareTo("Integer") == 0)
            	value = -1201;
            // create variables for each item read in from the file

            // if reading in integers
            if (value == -1201) { 

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
	                    	if (value == -1201) {
	                    		out.println("Error in Line: " + operation);
	                    		out.flush();
	                    		in.nextLine();
	                    		break;
	                    	}
	                    	
	                    	out.println(rbt.insert(value));
	                    	out.flush();
	                        
	                        break;
	                        
	                    case "Contains":
	                		if (value == -1201) {
	                    		out.println("Error in Line: " + operation);
	                    		out.flush();
	                    		in.nextLine();
	                    		break;
	                    	}
	                		
	                    	out.println(rbt.contains(value));
	                    	out.flush();
	
	                        break;
	                        
	                    case "PrintTree":
	                    	out.println(rbt.toString());
	                    	out.flush();
	                        break;
	
	                    default:
	                        out.println("Error in Line: " + operation);
	                        out.flush();
	                        in.nextLine();
	                } // end switch statement
	                
	            } // end while loop which reads and processes each line in input file
            } // end of if statement using Integers
            
         // comparing strings
            else { 
            	
            	String name = null;
            	
            	while (in.hasNext()) {
            		
	            	name = null;
	                operation = in.next();
	
	                // find if line also has a number to be read in
	                if(operation.contains(":")) {
	                	name = operation.substring(operation.indexOf(":")+1);
	                	operation = operation.substring(0, operation.indexOf(":"));
	                }
	                
	                // preceding actions depend on command
	                switch (operation) {
	
	                    // insert reads in integers for ID and price
	                    case "Insert":
	                    	if (name == null) {
	                    		out.println("Error in Line: " + operation);
	                    		out.flush();
	                    		in.nextLine();
	                    		break;
	                    	}
	                    	
	                    	out.println(rbt.insert(name));
	                    	out.flush();
	                        
	                        break;
	                        
	                    case "Contains":
	                		if (name == null) {
	                    		out.println("Error in Line: " + operation);
	                    		out.flush();
	                    		in.nextLine();
	                    		break;
	                    	}
	                		
	                    	out.println(rbt.contains(name));
	                    	out.flush();
	
	                        break;
	                        
	                    case "PrintTree":
	                    	out.println(rbt.toString());
	                    	out.flush();
	                        break;
	
	                    default:
	                        out.println("Error in Line: " + operation);
	                        out.flush();
	                        in.nextLine();
	                } // end switch statement
	                
	            } // end while loop which reads and processes each line in input file
            	
            } // end of else statement using Strings

            // close input and output file objects
            in.close();
            out.close();

        } // end of try statement
        catch(Exception e){
        	if(e.getMessage() != "No line found")
        		System.out.print("Exception: " + e.getMessage());
        } // end of catch

    } // end function main

} // end main function
