import java.util.Random;

/******************************************************************************************************
 * @title The GameLogic class.
 * Controls the game's core mechanics, tracking player progress and chances of crossing lanes.
 * 
 * @author Alamn Adeleke, Chuckwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *****************************************************************************************************/
public class GameLogic
{
    //Instance variables
    protected Random rNG = new Random();
    protected int lane;
    protected double odds;
    protected int vBucks;
    
    
    /*******************************************
     * Default constructor for GameLogic
     *******************************************/
    public GameLogic()
    {
        reset();
    }
    
    /*************************************************
     * Method to update game state(cross a lane)
     *************************************************/
    public void update()
    {
        //If there are still lanes left to cross
        if(lane<8)
        {
            //increment the relevant data based on 
            lane++;
            vBucks += App.getPay();
            odds -= App.getOdds();
        }
        
    }
    
    /*************************************************
     * Method to reset game state
     *************************************************/
     public void reset()
    {
        lane = 0;
        vBucks = 0;
        odds = 90;
    }
    
    //Accesor methods to return instance data
    public int getLane()    {return lane;}
    public int getVBucks()  {return vBucks;}
    public double getOdds()    {return odds;}
    
    /***********************************************************************************
     * Method to check if crossing is successful based on probability
     * 
     * @param probability The probability to check against
     * @return true if the crossing is successful, false otherwise.
     ************************************************************************************/
    public boolean cross(int probability)
    {
        boolean crossed;
        //if a random value(between 0 and 100) is within the range of 0 to the probability of success,
        if(probability >= rNG.nextInt(100) + 1)
        {
            //set output to success(true)
            crossed = true;
        }
        //otherwise, 
        else
        {
            //set output to failure(false)
            crossed = false;
            //reset the player's money
            vBucks = 0;
        }
        return crossed;
    }
    
    /*********************************************************************
     * Method to check if crossing is successful based on current odds
     * If unsuccessful, the player's money (vBucks) is reset to zero.
     *
     * @return true if the crossing is successful, false otherwise.
     *********************************************************************/
    public boolean cross()
    {
        boolean crossed;
        //if a random value(between 0 and 100) is within the range of 0 to the probability of success,
        if(odds >= rNG.nextInt(100) + 1)
        {
            //set output to success(true)
            crossed = true;
        }
        //otherwise, 
        else
        {
            //set output to failure(false)
            crossed = false;
            //reset the player's money
            vBucks = 0;
        }
        return crossed;
    }
}
