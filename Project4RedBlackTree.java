import java.util.Stack;

//Author:          Sarah G. Tempelmeyer
//Course:          CS 3345.003
//Date:            Semester 1 - 10/16/2019
//Assignment:      Project 4 - Simplified Red-Black Trees
//Compiler:        Eclipse 2018_12
//Net ID:          sgt170030

//Description: Implements a red-black tree data structure. Other functions include insert(value), contains(value), 
//             toString(preorder traversal), rebalance(Node), singleRight(Node) rotation, singleLeft(Node) rotation, 
//             doubleRight(Node) rotation, doubleLeft(Node) rotation.

@SuppressWarnings("unchecked")
public class RedBlackTree <E extends Comparable<E>>{
	
	static boolean red = false;
	static boolean black = true;
	private Node<E> root = null;
	
//	insert should insert the given element into the tree at the correct position, and then rebalance
//	the tree if necessary. The correct position is defined by the properties of a binary search tree,
//	and the rebalancing procedure should enforce the properties of a red-black tree. Regarding input
//	validation, insert should immediately throw a NullPointerException with a descriptive
//	message if the given element is null. Alternatively, if the given element is a duplicate of an
//	element already in the tree, then insert should not insert the given element. The return value
//	should indicate whether the given element was inserted into the tree or not
	
	// throw null pointer exception 
	public boolean insert(E element){
		
		try {
			if (element == null)
				throw new NullPointerException();
			else {
				Node<E> current = root;
				if (root == null) { //list is empty
					root = new Node<E>(element);
					root.color = black;
					return true; // insert sucessful
				}
				
				while (current != null) {
					// element less than transveral node
					if (element.compareTo((E)current.element) < 0 ) {
						
						// add element if at leaf node
						if (current.leftChild == null) { 
							current.leftChild = new Node<E>(element); // new node defaulted to red color
							current.leftChild.parent = current; // set parent
							// rebalance if double red situation
							if (current.color == red) // if parent red and child red
								rebalance(current.leftChild);
							return true; // insert successful
						}
						
						current = current.leftChild; // continue traversing tree
					}
					
					// element greater than transveral node
					else if (element.compareTo((E)current.element)  > 0 ) {
						
						// add element if at leaf node
						if (current.rightChild == null) { 
							current.rightChild = new Node<E>(element); // new node defaulted to red color
							current.rightChild.parent = current; // set parent
							// rebalance if double red situation
							if (current.color == red)  // if parent red and child red
								rebalance(current.rightChild);
							return true; // insert successful
						}
						
						current = current.rightChild;
					}
					
					// attempted added element already exists
					else if (element.compareTo((E)current.element) == 0) { 
						return false; 
					}
					
				} // end while loop
				
				return false;
			} // end of else (element was not null/exception was not thrown)
			
		} // end of try
		catch (NullPointerException n) {
			System.out.println("Error in insert: NullPointerException raised");
			// return false if attempted to insert null node to tree
			return false;
		}
		
	}

//	contains should return whether the tree contains any element that compares equal to the
//	given object using the compareTo method of the object. This means that you should always
//	do object.compareTo(element) but never do element.compareTo(object). 
//	However, if the given object is null, then contains should not throw an exception but rather
//	should return false
	public boolean contains(Comparable<E> element) {
		Node<E> current = root;
		
		if (element == null) {
			return false;
		}
		
		while (current != null) {
			if (element.compareTo((E)current.element) < 0 )  // search < node element
				current = current.leftChild; // continue traversing tree
			
			else if (element.compareTo((E)current.element) > 0 ) // search > node element
				current = current.rightChild; // continue traversing tree
			
			else if (element.compareTo((E)current.element) == 0 ) // search = node element
				return true; // key exists but is deleted
			
			else // did not find key
				return false; 
		} // end while loop
		
		return false; // reached if root = null
	}
	
//	toString should override the eponymous method of Object and return a string representing
//	the pre-order traversal of this tree. The returned string should be the ordered concatenation of
//	invoking the toString method of each element in the traversal, where every two adjacent
//	elements should be separated by a single space character (“ ”). If an element is located in a red
//	node, then it should be preceded by a single asterisk character (“*”) in the output string.
//	Otherwise, an element located in a black node should not be preceded by an asterisk.
	public String toString() {
		
		if (root == null) // if empty tree, return empty string
			return "";
			
		// declare variable which elements will be added to in pre-order traversal
		StringBuilder returnString = new StringBuilder();
		Node<E> current = root;

		Stack<Node> stacksOnStacks = new Stack<Node>();
		stacksOnStacks.push(root);
		
		while (stacksOnStacks.empty() == false) {
			
			current = stacksOnStacks.peek();
			
			if (current.color == red) 
				returnString.append("*"); // append asterisk to string if red
			
			returnString.append(current.element + " "); // append key to string
			stacksOnStacks.pop(); // remove current node from stack
			
			if (current.rightChild != null) // push right subtree onto stack
				stacksOnStacks.push(current.rightChild);
			if (current.leftChild != null) // push right subtree onto stack
				stacksOnStacks.push(current.leftChild);
			
		} // end while loop
		
		return returnString.toString();
	} // end function toString
	
	// this method rebalances red-black tree after inserting element
	private void rebalance(Node<E> child) {
		// declare variables for child node parameter and it's family (parent, grandparent, great grandparent)
		Node<E> p = child.parent;
		Node<E> gp = null;
		Node<E> ggp = null;
		Node<E> newGP = null;

		// check to make sure problem did not propagate upwards
		while (child.color == red && p.color == red) {
		
		// check and assign grandparent if it exists
		if (p.parent != null)
			gp = p.parent;
		else 
			return;
		
		// check and assign great grandparent if it exists
		if (gp.parent != null)
			ggp = gp.parent;
		
		Node<E> uncle = null;
		// uncle is parent's sibling (either left or right)
		if (p == gp.rightChild)
			uncle = gp.leftChild; 
		else if (p == gp.leftChild)
			uncle = gp.rightChild;
		
		// RESTRUCTURE if uncle is black
		if(uncle == null || uncle.color == black) {
			if (p == gp.rightChild) {
				if (child == p.rightChild)  // RIGHT RIGHT CASE
					newGP = singleRight(gp);
				else                        // RIGHT LEFT CASE
					newGP = doubleRight(gp);
			}
			else if (p == gp.leftChild) {
				if (child == p.leftChild)   // LEFT LEFT CASE
					newGP = singleLeft(gp);
				else                        // LEFT RIGHT CASE
					newGP = doubleLeft(gp);
			}
			
			// recolor new grand parent to black and its children to red
			newGP.color = black;
			if (newGP.rightChild != null)
				newGP.rightChild.color = red;
			if (newGP.leftChild != null)
				newGP.leftChild.color = red;
			
			if (gp == root) { // then grandparent is the root, at the top of the tree
				root = newGP;
				root.parent = null;
			}
			else {
				if(ggp.rightChild == gp) { 			// if changed the right subtree, reconnect parent and child connections
					ggp.rightChild = newGP;
					newGP.parent = ggp;
				}
				else {								// if changed the lwft subtree, reconnect parent and child connections
					ggp.leftChild = newGP;
					newGP.parent = ggp;
				}
				
				// reset child and parent pointers to be checked in while loop condition
				child = newGP;
				p = ggp;
			}
			
		} // end case if uncle is black
		
		// RECOLOR if uncle is red
		// grandparent = red, and uncle and parent = black, child stays red
		if(uncle != null && uncle.color == red) {
				gp.color = red;
				if (gp.rightChild != null)
					gp.rightChild.color = black;
				if (gp.leftChild != null)
					gp.leftChild.color = black;
				
				// reset child and parent pointers to be checked in while loop condition
				if (p.parent != null)
					child = p.parent;
				if (child.parent != null)
					p = child.parent;
			
		} // end case if uncle red
		
		// ensure root is black
		root.color = black;
		
		} // end while loop 

	} // end function rebalance
	
	// rotates argument with it's left child
	// returns left child, which is now the parent
	private Node<E> singleLeft(Node<E> oldParent) {
		
		Node<E> gp = oldParent.parent;
		Node<E> newParent = oldParent.leftChild;
		oldParent.leftChild = newParent.rightChild;
		newParent.rightChild = oldParent;
		newParent.parent = gp;
		oldParent.parent = newParent;
		
		return newParent;
	}
	
	// rotates argument with it's right child
	// returns right child, which is now the parent
	private Node<E> singleRight(Node<E> oldParent) {
		
		Node<E> gp = oldParent.parent;
		Node<E> newParent = oldParent.rightChild;
		oldParent.rightChild = newParent.leftChild;
		newParent.leftChild = oldParent;
		newParent.parent = gp;
		oldParent.parent = newParent;
		
		return newParent;
	}
	
	// LEFT RIGHT double rotation
	// returns the parent
	private Node<E> doubleLeft(Node<E> oldParent) {
		oldParent.leftChild = singleRight(oldParent.leftChild);
		
		return singleLeft(oldParent);
	}
	
	// RIGHT LEFT double rotation
	// returns the parent
	private Node<E> doubleRight(Node<E> oldParent) {
		oldParent.rightChild = singleLeft(oldParent.rightChild);
		
		return singleRight(oldParent);
	}
	
	// nested Node Class for nodes in red-black tree
	// properties: element, pointer to left and right children and parent, and color
	private static class Node <E> {
		private E element;
		private Node<E> leftChild = null;
		private Node<E> rightChild = null;
		private Node<E> parent = null;
		private boolean color = red;
		
		// overload constructor
		Node(E elem)
		{
			element = elem;
			leftChild = null;
			rightChild = null;
			parent = null;
			color = red; // color defaulted to red because every new node added to tree is initially red
		}
		 
	} // end Node Class
	
} // end Red Black Tree Class
