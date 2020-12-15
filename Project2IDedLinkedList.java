/*Design a generic singly linked list java class IDedLinkedList to hold objects of the generic
type <AnyType>. This AnyType should extend IDedObject. You have to design your own
class that does not use any other Java collection API. The linked list class must
implement following member functions:
*/

import org.omg.CORBA.Any;

public class IDedLinkedList<AnyType extends IDedObject> {

    // constructor
    public IDedLinkedList()
    {
        size = 0;
        beginMarker = endMarker = null;
    }

    // empties the list
    public void makeEmpty()
    {
        // points beginning and end markers to null and sets size to 0
        beginMarker = endMarker = null;
        size = 0;
    }

    // get the generic type to get the particular ID and returns AnyType. Don't remove object from list. Returns null
    // if list empty or ID not found
    public AnyType findID(int ID)
    {
        AnyType current = beginMarker;
        while (current != null){
            // if ID found, return the Item
            if (current.getID() == ID)
                return current;
        }

        // ID not found so return null
        return null;
    }

    // insert at front of list or return false if that ID already exists
    public boolean insertAtFront(AnyType x)
    {
        // check if ID already exists
        if (this.findID(x.getID()) != null)
            return false;
        // ID not found so insert at front and return true;
        x.setNext(beginMarker);
        beginMarker = x;

        return true;
    }

    // delete and return the record at the front of the list or return null if list is empty
    public AnyType deleteFromFront(){
        // ensure list not empty
        if (beginMarker != null)
            return null;

        // else
        AnyType deleteThis = beginMarker;
            beginMarker = (AnyType)beginMarker.getNext(); // move begin marker forward
            deleteThis.setNext(null); // first node now points to nothing


        // return deleted Item
        return deleteThis;
    }

    // find and delete the record with the given ID or returns null if not found
    public AnyType delete(int ID){

        if (findID(ID) == null || beginMarker == null) // if not found or empty list, return null
            return null;

        // declare variable which will point to desired Item to delete, and one to transverse list
        AnyType deleteThis = findID(ID);
        AnyType current = beginMarker;

        if(current == deleteThis) // if beginning node should be deleted
            return deleteFromFront();

        // transverse through loop until current.next is the Item to be deleted
        while (current.getNext() != deleteThis)
            current = (AnyType) current.getNext();

        current.setNext(deleteThis.getNext()); // node before deleteThis connects to deleteThis->next
        deleteThis.setNext(null); // point deleted node to null

        return deleteThis;

    }

    // returns the sum of IDs of all elements currently in the list. If list is empty, print -1
    public int printTotal()
    {
        AnyType current = beginMarker;
        int total = 0;

        // add sum for all elements in array
        while (current != null)
            total += current.getID();

        return total;
    }

    // declare variables for IDedLinkedList
    private int size;
    private AnyType beginMarker;
    private AnyType endMarker;

} // end class IDedLinkedList
