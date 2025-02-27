import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * The main app class
 *
 * @author Alamin Adeleke
 * @version 1
 */
public class App extends Application
{
    //Variables used by the whole program
    protected static int pay;
    protected static int odds;
    public static String name;
    
    MyScene scene = new MyScene();
    
    public void start(Stage stage)  
    { 
        //HomeScreen i = new HomeScreen();
        
        stage.setScene(scene);
        stage.setTitle("Nana Crossing");
        stage.setHeight(530);
        stage.setWidth(513);
        listen(stage);
        
        stage.getIcons().add(new Image("/assets/nana.png"));
        
        //String musicFile = "C:/Users/r65a3/Downloads/OneDrive_2025-02-25/nana crossing/assets/sounds/background music/piano.mp3";
        //HomeScreen.playMusic(musicFile);
        
        stage.setResizable(false);
        stage.setX(10);
        stage.setY(10);
        stage.show();
    }
    
    public void listen(Stage stage)
    {
        scene.homeScreen.playB.setOnMouseClicked(e ->
        {
            if(scene.homeScreen.canGoToGameScreen())
            {
                scene.homeScreen.setValues();
                scene.gameScreen.reset();
                
                scene.setRoot(scene.gameScreen); // Set the root to the game screen
                scene.homeScreen.stopMusic();
                stage.sizeToScene();
            }
        });
        
        scene.gameScreen.inputPane.reset.setOnMouseClicked(e ->
        {
            if(scene.gameScreen.canReset())
            {
                scene.gameScreen.backEnd.reset();
                scene.gameScreen.update();

                scene.homeScreen.playNextTrack();
                stage.setHeight(530);
                stage.setWidth(513);
                scene.setRoot(scene.homeScreen);
            }
        });
    }
   
    public static void setPay(int set)
    {
        pay = set;
    }
    
    public static int getPay()
    {
        return pay;
    }
    
    public static void setOdds(int set)
    {
        odds = set;
    }
   
    public static int getOdds()
    {
        return odds;
    }
    
    public static void setName(String set)
    {
        name = set;
    }
    
    public static String getName()
    {
        return name;
    }
}
