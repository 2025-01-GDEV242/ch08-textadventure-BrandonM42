import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Brandon Magistrado
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
    private Stack<Room> roomsVisited = new Stack<>();
    private ArrayList<Item> roomItems = new ArrayList<>();
    private ArrayList<Item> playerItems = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private Player bob = new Player("Bob", -30, currentRoom);
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        //add items
        outside.addItem("flower",5);
        outside.addItem("rock", 15);
        theater.addItem("smelly cheeseburger",5000);
        theater.addItem("microphone", 10);
        pub.addItem("water",10);
        pub.addItem("My friend Jonathan", 10000);
        office.addItem("pen", 8);
        office.addItem("Alchohol", 15);
        lab.addItem("highly corrosive substance", 25);
        lab.addItem("fish carcass", 18);
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
    
        currentRoom = outside;  // start game outside
        
        roomItems = currentRoom.getItemList();
        playerItems = bob.getPlayerArray();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                 look();
                 break;
                 
            case TAKE:
                 take();
                 break;
                 
            case DROP:
                 drop();
                 break;
                 
            case BACK:
                 back();
                 break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            lastRoom = currentRoom;
            roomsVisited.push(lastRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * prints out current location when typing "look".
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /*private void back()
    {
        if (lastRoom == null) 
        {
            System.out.println("There is nowhere to go back from!");
        }
        else 
        {
            currentRoom = lastRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    */
   
   /**
     * goes back a room if it is available
     * if its not then it will say a message
     */
   private void back()
   {
       if(roomsVisited.isEmpty() == true)
       {
           System.out.println("You are at the starting point!");
       }
       else
       {
           System.out.println(roomsVisited.pop().getLongDescription());
       }
   }
   
   /**
     * allows the player to take an item from a room if it is a valid spot
     * prints out their inventory
     */
   private void take()
   {
       int spotNum;
       int itemNum = 0;
       System.out.println("Which spot do you want to take from?");
       roomItems = currentRoom.getItemList();
       for(Item rItems : roomItems)
       {
           System.out.println(itemNum + ")" + (rItems.getDescription() + " Weight: " + rItems.getWeight() + ",\n"));
           itemNum +=1;
       }
       spotNum = scan.nextInt();
       
       if(spotNum > roomItems.size() || spotNum < 0 || roomItems.size() == 0)
       {
           System.out.println("Theres nothing in that spot");
       }
       else
       {
           bob.getPlayerArray().add(roomItems.get(spotNum));
           roomItems.remove(spotNum);
       }
       
       
       
       System.out.println("Your items are:");
       
       for(Item pItems : playerItems)
       {
           System.out.println(pItems.getDescription() + " Weight: " + pItems.getWeight() + ",\n");
       }
       System.out.println(currentRoom.getLongDescription());
       
       
   }
  
   /**
     * allows the player to throw away an item
     * prints out their inventory
     */
   private void drop()
   {
       int dropNum;
       int itemNum = 0;
       System.out.println("Which spot do you want to throw away?");
       
       for(Item pItems : playerItems)
       {
           System.out.println(itemNum + ")" + (pItems.getDescription() + " Weight: " + pItems.getWeight() + ",\n"));
           itemNum +=1;
       }
       dropNum = scan.nextInt();
       
       if(playerItems.size() != 0 && dropNum >= 0 && dropNum < playerItems.size())
       {
           playerItems.remove(dropNum);
       }
       else System.out.println("Cant remove that");
       
       System.out.println("Your items are:");
       
       for(Item pItems : playerItems)
       {
           System.out.println(pItems.getDescription() + " Weight: " + pItems.getWeight() + ",\n");
       }
       System.out.println(currentRoom.getLongDescription());
       
   }
}
