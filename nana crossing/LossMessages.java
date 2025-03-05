import java.util.Random;

/**********************************************************************************************************
 * @title LossMessage Class
 * The Game class contains a collection of funny losing messages and a method to print a random one.
 *
 * @authors Chukwunonso Ekweaga, Ashlee Muichirahondo and Alamin Adeleke
 **********************************************************************************************************/
public class LossMessages
{
    // Array of losing messages
    protected static final String[] messages = 
    {
        " got turned into ketchup! Better luck next time!",
        //"Haha, now you have no grandparents! Nana's gone!",
        ", The old hag didn't make it! Splat!",
        " got yeeted by a semi-truck! Game over!",
        " is now a speed bump. You lose!",
        " got flattened like a pancake! All your VBucks are gone!",
        " got launched into orbit by a speeding car! You lose!",
        " got turned into road pizza! Better call the traffic warden next time!",
        " got vaporized by a rocket car! You lose!",
        " got hit so hard, she time-traveled back to 1923! Game over!",
        " got turned into a hood ornament! You lose!",
        " got hit by a UFO! Aliens took her! No VBucks for you!",
        " got hit by a parade float! Now she's part of the show!",
        " got hit by a food truck! Free tacos, but no VBucks!",
        " got hit by a Tesla on autopilot! Elon sends his condolences!.",
        " was turned from 3D to 2D!",
        " Became a fine red mist",
        " couldn't handle the A-train",
        " got hit by a drunk driver! Now they're seeing stars... permanently!",
        " got hit by a rogue bulldozer! Construction zone ahead, you lose!",
        " got hit by a runaway ice cream truck! Sweet revenge, but no VBucks for you!",
        " got hit by a rogue forklift! Forked up, you lose!"
    };
    
    /*******************************************************************************************
     * Method to get a random loss message
     * @return a random funny losing message from the MESSAGES array.
     *******************************************************************************************/
    public static String out() 
    {
        Random random = new Random();
        return(App.getName() + messages[random.nextInt(messages.length)]);
    }
}  