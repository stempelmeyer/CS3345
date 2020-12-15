import java.util.List;

public class MyItem implements IDedObject {
    private int itemID = 0;
    private int itemPrice = 0;
    private List<Integer> itemDescription;
    private IDedObject next;

    // constructor
    public MyItem()
    {
        itemID = 0;
        itemPrice = 0;
        List<Integer> itemDescription;
        next = null;
    }

    // overload constructor
    public MyItem(int id, int p, List<Integer> descr, IDedObject n)
    {
        itemID = id;
        itemPrice = p;
        List<Integer> itemDescription = descr;
        next = n;
    }

    // print details of the item in one line
    public String printID()
    {
        // print details of the item in one line
        return ("ID = " + itemID + ", price = $" + itemPrice + ", description = " + itemDescription);
    }


    // accessors
    public int getID() { return itemID; } // returns the itemID
    public int getItemPrice() {return itemPrice;} // returns the itemPrice
    public List<Integer> getItemDescription() {return itemDescription;} // returns the itemDescription
    public IDedObject getNext() {return next;} // returns the next MyItem
    // setters
    public void setID(int id) {itemID = id;} // sets the itemID
    public void setItemPrice(int p) {itemPrice = p;} // sets the itemPrice
    public void setItemDescription(List<Integer> descr) {itemDescription = descr;} // sets the itemDescription
    public void setNext(IDedObject n) {next = n;}


} // end class MyItem