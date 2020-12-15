//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 9/28/2019
//Assignment:      Project 3 - Binary Search Trees with Lazy Deletion
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements a binary search tree with lazy deletion. Other functions include insert(value), delete(value), findMin(not deleted),
// 			   findMax(not deleted), contains(value), toString(preorder traversal), height(longest length), and size(number of nodes). 

Input file with: 
Insert:98
Insert:67
Insert:55
Insert:45
PrintTree
Delete:84
Delete:45
Contains:45
FindMin
FindMax
PrintTree
Height
Size
Insert:84
Insert:32
Insert:132
PrintTree
Insert:980
Insert
hih

Should output:
True
True
True
True
98 67 55 45
False
True
False
55
98
98 67 55 *45
3
4
True
True
Error in insert: IllegalArgumentException raised
98 67 55 *45 32 84
Error in insert: IllegalArgumentException raised
Error in Line: Insert
Error in Line: hih