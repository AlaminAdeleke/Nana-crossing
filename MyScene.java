import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

public class MyScene extends Scene {
    public HomeScreen homeScreen = new HomeScreen();
    public GameScreen gameScreen = new GameScreen();
    
    public MyScene() 
    {
        super(new Pane());  // Call the superclass constructor to set the root  since it has no default cunstroctor, and instance cannot be called before cunstructor
        this.setRoot(homeScreen);//Setting the default scene
        
    }
}