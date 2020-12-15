// Design java interface that has the following abstract functions

public interface IDedObject {

    // returns ID of the object
    int getID();

    // prints the details of the ID
    String printID();

    void setNext(IDedObject ob);

    IDedObject getNext();


}
