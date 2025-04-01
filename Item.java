
/**
 * Write a description of class item here.
 *
 * @author (your name)
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
    
    public Item(String Description, int Weight)
    {
        itemWeight = Weight;
        itemDescription = Description;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getDescription()
    {
        return itemDescription;
    }
    
    public int getWeight()
    {
        return itemWeight;
    }
    
}
