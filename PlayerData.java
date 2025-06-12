// for Scanning
import java.util.Scanner;
import java.util.NoSuchElementException;

// for Files
import java.io.File;
import java.io.FileNotFoundException;

// for FileWriting
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Manages loading/saving player save files.
 * Uses Singleton pattern (just like GameManager)
 * 
 * @author Julia
 * @version Jun 2025
 */
public class PlayerData  
{
    // Private static instance (PlayerData creates its own instance, other classes use getter to access this instance)
    private static PlayerData instance;
    
    // Variables are public
    public int highScoreCoins;
    public int highScore;
    
    private int[] dataArray; // array containing player save data
    private boolean dataLoaded = false;
    
    // Constructor is private - prevents direct instantiation, no one else can make a new constructor
    private PlayerData() {
        // Create dataArray, set default values as 0
        dataArray = new int[]{0, 0};
        // index 1: highScoreCoins
        // index 2: highScore
    }
    
    /**
     * Public getter for one-and-only instance
     * Other classes access GameManager's variables/methods through this getter
     */
    public static PlayerData getInstance() {
        if(instance == null) {
            instance = new PlayerData();
        }
        
        return instance;
    }
    
    /**
     * For testing purposes - print out dataArray and all saved variable values.
     */
    public void printData() {
        // Print dataArray
        for(int i = 0; i < dataArray.length; i++) {
            System.out.println(dataArray[i]);
        }
        
        // Print all saved variables
        System.out.println("highScoreCoins: " + highScoreCoins);
        System.out.println("highScore: " + highScore);
    }
    
    public void saveData() {
        // Prevent saving data if data hasn't been loaded yet!
        // (Bc without loadData(), variables are null, so saveData would error)
        if(!dataLoaded) {
            System.out.println("Attempted to save data before data was loaded. Not happening!");
            return;
        }
        try {
            FileWriter out = new FileWriter("player_save.txt");
            PrintWriter output = new PrintWriter(out);
            
            // Print data to save
            output.println(highScoreCoins);
            output.println(highScore);
            
            output.close();
            
            System.out.println("Data is saved!");
        } catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void loadData() {
        // TESTING
        System.out.println("inside loadData() method now");
        
        try {
            Scanner scan = new Scanner(new File("player_save.txt"));
            
            // If file is empty (aka first time player opened/played game, no data stored yet), all variables are set to their default value from when
            // array was created (0)
            
            // Retrieve data, store each line in array
            int index = 0;
            while(scan.hasNext() && index < dataArray.length) { // prevents NoSuchElementException and ArrayIndexOutOfBoundsException - no error if either file or array is too short/missing values
                try {
                    dataArray[index] = Integer.parseInt(scan.nextLine()); // each piece of data (index in data array) is one line on save file
                } catch(NumberFormatException e) {
                    // If line is not an int (ex: "hello" instead of "5"), use default variable value (0)
                    System.out.println("Incorrect data type on line " + index + ", using default value of 0.");
                    dataArray[index] = 0;
                }
                index++;
            }
            
            scan.close();
            
            // Set variables to array values
            highScoreCoins = dataArray[0];
            highScore = dataArray[1];
            
            dataLoaded = true;
            
            // TESTING
            printData();
        } catch(FileNotFoundException e) {
            System.out.println("Save file not found.");
            dataLoaded = false;
        }
    }
}
