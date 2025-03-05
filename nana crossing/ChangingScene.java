import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/*********************************************************************************************
 * A scene with the ability to change its root pane
 *
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *********************************************************************************************/
public class ChangingScene extends Scene 
{
    //Initialising the two scenes that are swapped between
    protected HomeScreen homeScreen = new HomeScreen();
    protected GameScreen gameScreen = new GameScreen();
    
    /*********************************************************************************************
     * Default Constructor for The ChangingScene Class
    *********************************************************************************************/
    public ChangingScene() 
    {
        // Call the superclass constructor to set the root  since it has no default cunstroctor, and instance cannot be called before cunstructor
        super(new Pane());  
        //Setting the default scene
        this.setRoot(homeScreen);
    }
    

}
