import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Brandon Magistrado
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;// stores exits of this room.
    ArrayList<Item> itemList = new ArrayList<>();
    
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + printAllItems() + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * adds a new item to a certain room
     * @param ItemDescription - the items description
     * @param ItemWeight - the items weight
     */
    public void addItem(String ItemDescription, int ItemWeight)
    {
        itemList.add(new Item(ItemDescription, ItemWeight));
    }
    
    /**
     * adds a predetermined item to a room
     */
    public void addItem()
    {
        itemList.add(new Item("Pencil: A standard yellow no.2 pencil", 30));
    }
    
    /**
     * prints all the items in a room
     */
    public String printAllItems()
    {
        String allItems = "The items in this room are:\n";
        for(Item oneItem : itemList)
        {
            allItems +=  oneItem.getDescription() + " Weight: " + oneItem.getWeight() + ",\n";
        }
        return allItems;
        
    }
    
    /**
     * returns the item list of a room
     * @return returns an itemList
     */
    public ArrayList getItemList()
    {
        return itemList;
    }
    
    
    
    
    
}

