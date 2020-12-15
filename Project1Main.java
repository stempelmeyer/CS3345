//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 8/21/2019
//Assignment:      Project 1 - Sieve of Erotosthenes
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: This program reads in user input value for N and outputs all prime numbers before N

import java.util.Scanner;
import java.util.*;


public class Main {

	public static void main(String[] args) {
		
		// declare scanner for console input and output
		Scanner console = new Scanner(System.in);

		// read in and validate a user input for N which is greater than 1
		int n = 0; // N will determine the size of the array - the end limit to all prime numbers we want to find
		do {
			System.out.print("Please enter an integer greater than 1: ");
			n = console.nextInt();
		} while(n <= 1);
		
		// declare and initialize array of size N with T or F values on if the index is prime
		boolean [] array = new boolean [n+1]; // create an array with an index INCLUDING the number the user inputted
		// initialize all values to true
		Arrays.fill(array, true);
		array[0] = false;
		array[1] = false;
		
		// this for loop sets all multiples (composite numbers) as false
		// remaining prime values are kept as the defaulted true value
		for(int index = 2; (index) * (index) <= n; index++) {
			// if index already determined to be composite, continue to next iteration
			if (array[index] == false)
				continue;
			// set index as true and all multiples of that index as false 
			
			else {
				array[index] = true;
				for (int i = 2; i*index <= n; i++)
					if (array[index*i]) // if array[index*i is true (if a prime number)
						array[index*i] = false;
			} // end of else statement
			
		} // end of for loop
		
		// output prime array (if values are TRUE)
		for (int index = 2; index <= n; index++) {
			if (array[index]) // if array[index] is true (if a prime number)
				System.out.print(index + " ");
		} // end for loop output 
		
	} // end function main
} // end class Main
