
/**
 * Write a description of class item here.
 *
 * @author Brandon Magistrado
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;

    /**
     * Constructor for objects of class item
     */
    public Item()
    {
        itemWeight = 10;
        itemDescription = "An unsharpened no.2 pencil";
    }
    
    /**
     * Constructor for objects of class item
     */
    public Item(String Description, int Weight)
    {
        itemWeight = Weight;
        itemDescription = Description;
    }

    /**
     * @return returns the item description
     */
    public String getDescription()
    {
        return itemDescription;
    }
    
    /**
     * @return returns the item weight
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
}
