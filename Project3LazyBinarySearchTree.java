import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 9/28/2019
//Assignment:      Project 3 - Binary Search Trees with Lazy Deletion
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements a binary search tree with lazy deletion. Other functions include insert(value), delete(value), findMin(not deleted),
// 			   findMax(not deleted), contains(value), toString(preorder traversal), height(longest length), and size(number of nodes). 

public class LazyBinarySearchTree {
	
	// create root attribute
	private TreeNode root = null;

//	should insert a new element to a leaf node. The valid set of keys is all integers in the
//	range [1,99]. If the new element would be a duplicate of a non-deleted element already in the
//	tree, then insert should do nothing. However, if the new element is not a duplicate of a nondeleted element, but is a duplicate of a deleted element, then insert should “undelete” the
//	deleted element in-place rather than physically inserting a new copy of the element. The return
//	value of insert should indicate whether insert logically (as opposed to physically) inserted
//	a new element.
	public boolean insert(int key) 
	{
		TreeNode current = root;
		if (root == null) { //list is empty
			TreeNode newNode = new TreeNode(key); 
			root = newNode;
			return true; // insert sucessful
		}
		
		while (current != null) {
			if (key < current.getKey()) {
				if (current.getLeftChild() == null) { // add element
					current.setLeftChild(new TreeNode(key));
					return true; // insert successful
				}
				current = current.getLeftChild(); // continue traversing tree
			}
			
			else if (key > current.getKey()) {
				if (current.getRightChild() == null) { // add element
					current.setRightChild(new TreeNode(key));
					return true; // insert successful
				}
				current = current.getRightChild();
			}
			
			else { // key == current.key
				current.setDeleted(false); // ensure element is not deleted
				return true; // insert successful
			}
		} // end while loop
		
		return false;
	}
	
//	should not physically remove an element from the tree. Rather, it should mark the
//	specified element as logically deleted. If the specified element is not in the tree or is already
//	marked as deleted, then delete should do nothing. The return value of delete should
//	indicate whether delete logically deleted an element.
	public boolean delete(int key) 
	{
		TreeNode current = root;
		
		while (current != null) {
			if (key < current.getKey()) 
				current = current.getLeftChild(); // continue traversing tree
			
			else if (key > current.getKey()) 
				current = current.getRightChild(); // continue traversing tree
			
			else if (key == current.getKey()){ // key == current.key
				current.setDeleted(true); // ensure element is deleted
				return true; // delete successful
			}
			else
				return false; // did not find element to delete
		} // end while loop
		
		return false;
	}
	
//	should return the value of the minimum non-deleted element, or -1 if none exists.
	public int findMin()
	{
		TreeNode current = root;
		int returnValue = -1;
		
		if (current == null) // if list empty
			return returnValue;
		
		else {
			if (current.isDeleted() == false)
				returnValue = root.getKey(); // set max as root's key
			
			while (current.getLeftChild() != null) { // move to left-most node
				if (current.getLeftChild().isDeleted() == false)
					returnValue = current.getLeftChild().getKey(); // set max to left-most element which is not deleted
				current = current.getLeftChild();
			}
		}
		
		return returnValue;
	}
	
//	 should return the value of the maximum non-deleted element, or -1 if none exists.
	public int findMax()
	{
		TreeNode current = root;
		int returnValue = -1;
		
		if (current == null) // if list empty
			return returnValue;
		
		else {
			if (current.isDeleted() == false)
				returnValue = root.getKey(); // set max as root's key
			
			while (current.getRightChild() != null) { // move to right-most node
				if (current.getRightChild().isDeleted() == false)
					returnValue = current.getRightChild().getKey(); // set max to rightmost element which is not deleted
				current = current.getRightChild();
			}
		}
		
		return returnValue;
	}
	
//	should return whether the given element both exists in the tree and is non-deleted.
	public boolean contains(int key)
	{
		TreeNode current = root;
		
		while (current != null) {
			if (key < current.getKey()) 
				current = current.getLeftChild(); // continue traversing tree
			
			else if (key > current.getKey()) 
				current = current.getRightChild(); // continue traversing tree
			
			else if (key == current.getKey()){ // key == current.key
				if (current.isDeleted() == false)
					return true; // key exists and is not deleted
				
				return false; // key exists but is deleted
			}
			else // 
				return false; // did not find key
		} // end while loop
		
		return false; 
	}
	
//	should perform an pre-order traversal of the tree and print the value of each
//	element, including elements marked as deleted. However, elements that are marked as deleted
//	should be preceded by a single asterisk. Every pair of adjacent elements should be separated by
//	whitespace in the printing, but no whitespace should occur between an asterisk and the element
//	with which it is associated. Leading and trailing whitespace is tolerable, but it will be ignored.
//	(no additional messages should be printed, either)
	public String toString()
	{
		StringBuilder returnString = new StringBuilder();
		TreeNode current = root;

		Stack<TreeNode> stacksOnStacks = new Stack<TreeNode>();
		stacksOnStacks.push(root);
		
		while (stacksOnStacks.empty() == false) {
			
			current = stacksOnStacks.peek();
			
			if (current.isDeleted() == true)
				returnString.append("*"); 		// * if deleted value
			returnString.append(current.getKey() + " "); // append key to string
			stacksOnStacks.pop(); // remove current node from stack
			
			if (current.getRightChild() != null) // push right subtree onto stack
				stacksOnStacks.push(current.getRightChild());
			if (current.getLeftChild() != null) // push right subtree onto stack
				stacksOnStacks.push(current.getLeftChild());
			
		} // end while loop
		
		return returnString.toString();
	}
	
//	should return the height of the tree, including “deleted” elements.
	public int height() { // by level-order traversal of tree
		int returnValue = 0;
		
		if (root == null)
			return returnValue;
	
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int queueSize = queue.size();
		
		while (!queue.isEmpty()) {
			queueSize = queue.size();
			
			while (queueSize > 0) { 
				TreeNode current = queue.removeFirst(); // returns and removes first element of linked list
				
				if (current.getLeftChild() != null) // add left subtree to queue
					queue.add(current.getLeftChild());
				if (current.getRightChild() != null) // add right subtree to queue
					queue.add(current.getRightChild());
				
				queueSize--; // decrement size of queue
			} // end second while
			
			if (queue.size() != 0) // if queue is empty, there were no subtrees added and don't increment height
				returnValue++;

		} // end first while
		
		return returnValue;
	} // end method height
	
//	should return the count of elements in the tree, including “deleted” ones
	public int size()
	{
		int returnValue = 1;
		
		if (root == null)
			return 0;
	
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
	
		while (queue.size() != 0) {

				TreeNode current = queue.remove();
				
				if (current.getLeftChild() != null) {
					queue.add(current.getLeftChild());
					returnValue++;
				}
				
				if (current.getRightChild() != null) {
					queue.add(current.getRightChild());
					returnValue++;
				}

		
		} // end first while
		
		return returnValue;
	}
	
	private class TreeNode {
		private int key;
		private TreeNode leftChild = null;
		private TreeNode rightChild = null;
		private boolean deleted = false;
		
		// default constructor
		TreeNode()
		{
			key = 0;
			leftChild = null;
			rightChild = null;
			deleted = false;
		}
		
		// overload constructors
		TreeNode(int k, TreeNode left, TreeNode right, boolean delete)
		{
			key = k;
			leftChild = left;
			rightChild = right;
			deleted = delete;
		}
		TreeNode(int k)
		{
			key = k;
			leftChild = null;
			rightChild = null;
			deleted = false;
		}
		
		// accessors
		int getKey() { return key;}
		TreeNode getLeftChild() { return leftChild; }
		TreeNode getRightChild() { return rightChild; }
		boolean isDeleted() { return deleted; }
		// mutators
		void setKey(int k) { key = k; }
		void setLeftChild(TreeNode left) { leftChild = left; }
		void setRightChild(TreeNode right) { rightChild = right; }
		void setDeleted(boolean delete) { deleted = delete; }
	}
	
}
