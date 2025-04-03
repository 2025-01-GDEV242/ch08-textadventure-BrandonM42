import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author Brandon Magistrado
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    
    private Room playerRoom = new Room("hi");
    private int playerAge;
    private String playerInfo;
    ArrayList<Item> playerItemList = new ArrayList<>();

    /**
     * Constructor for objects of class Player
     */
    public Player(String info, int age, Room room)
    {
        playerRoom = room;
        playerAge = age;
        playerInfo = info;
    }
    
    /**
     * @return - returns a players information
     */
    public String getInfo()
    {
        return playerInfo;
    }
    
    /**
     * @return - returns a players age
     */
    public int getAge()
    {
        return playerAge;
    }
    
    /**
     * @return - returns a players current room location
     */
    public Room getRoom()
    {
        return playerRoom;
    }
    
    /**
     * @return - returns a players inventory arraylist
     */
    public ArrayList getPlayerArray()
    {
        return playerItemList;
    }
}
