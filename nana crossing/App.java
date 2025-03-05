import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/*********************************************************************************
 * @ The App Class
 * The main app class
 *
 * @author Alamn Adeleke, Chuckwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *********************************************************************************/
public class App extends Application
{
    //static Variables used by the whole program
    protected static int pay;
    protected static double odds;
    public static String name;
    
    //customized scene class
    protected ChangingScene scene = new ChangingScene();
    
    /**************************************
     * Method to start the application
     *************************************/
    public void start(Stage stage)  
    { 
        stage.setScene(scene);
        stage.setTitle("Nana Crossing");
        stage.getIcons().add(new Image("/assets/nana.png"));
        stage.setResizable(false);
        stage.setX(20);
        stage.setY(20);
        
        //Method to use buttons in the used classes
        listen(stage);
                
        //display the stage
        stage.show();
    }
    
    /********************************************************
     * Method to set up event listeners
     * 
     * @param stage The stage to attach event listeners to.
     *******************************************************/
    public void listen(Stage stage)
    {
        //Set up play button listener
        scene.homeScreen.playB.setOnMouseClicked(e ->
        {
            //if the conditions to go to the game screen is met,
            if(scene.homeScreen.canGoToGameScreen())
            {
                //import the values from the textfields
                scene.homeScreen.setValues();
                
                //set the game to a begining state
                scene.gameScreen.reset();
                
                // Set the root to the game screen
                scene.setRoot(scene.gameScreen); 
                stage.sizeToScene();
                
                //play a click sound effect
                scene.gameScreen.click.seek(scene.gameScreen.click.getStartTime());
                scene.gameScreen.click.play();
                
                //Start playing horn sounds
                scene.gameScreen.startPlayingSounds();
                
                //Stopping the background music
                scene.homeScreen.stopMusic();
            }
        });
        
        //Set up reset button listener in the gameScreen class
        scene.gameScreen.inputPane.reset.setOnMouseClicked(e ->
        {
            //If the condition to reset the games are met,
            if(scene.gameScreen.canReset())
            {
                //set the game to a begining state
                scene.gameScreen.backEnd.reset();
                scene.gameScreen.update();
                scene.gameScreen.lanes.reset();
                scene.gameScreen.lanes.distance = 0;
                //play the background music
                scene.homeScreen.playNextTrack();
                
                
                //play a click sound
                scene.gameScreen.click.seek(scene.gameScreen.click.getStartTime());
                scene.gameScreen.click.play();
                //stop playing horn sounds
                scene.gameScreen.stopPlayingSounds();
                
                
                //setting the scene back to the home screen
                scene.setRoot(scene.homeScreen);
                stage.sizeToScene();
            }
        });
    }
   
    //Mutator methods to set the instance data of the class
    //(Used mainly in the homescreen class)
    public static void setPay(int set)
    {
        pay = set;
    }
    public static void setOdds(double set)
    {
        odds = set;
    }
    public static void setName(String set)
    {
        name = set;
    }
    
    //Accesor methods to get the instance data of the class
    //(Used mainly in the GameLogic class)
    public static int getPay()
    {
        return pay;
    }
    public static String getName()
    {
        return name;
    }
    public static double getOdds()
    {
        return odds;
    }
}
