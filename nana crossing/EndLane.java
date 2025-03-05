import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/*********************************************************************************
 * @title The EndLane class.
 * It includes the visual elements and behavior for the game's endpoint.
 *
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *********************************************************************************/
public class EndLane extends Lane
{
    //An array of file locations of car graphics
    protected String[] cars = {"/assets/car1.png", "/assets/car2.png", "/assets/car3.png", "/assets/car4.png", "/assets/car5.png", "/assets/car6.png" };
    
    //instance data
    protected Random rNG = new Random();
    protected int y;
    protected int x;
    
    //nodes used in the lane
    protected ImageView end;
    
    
    /*********************************************
     * Constructor with parameters for EndLane
     * 
     * @param y The y-coordinate of the lane
     * @param x The x-coordinate of the lane
     *********************************************/
    public EndLane(int y, int x)
    {
        super(x,y);
        end = new ImageView(new Image("/assets/end.png"));
        
        //Set the size positions of the assets of the lane
        end.setX(x);
        end.setY(y);
        end.setFitHeight(laneHeight);
        end.setFitWidth(55);
        
        this.getChildren().clear();
        this.getChildren().add(end);
    }
    
}
