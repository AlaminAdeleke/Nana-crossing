import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/*************************************************************************************
 * @title The Lane Class.
 * It includes visual components such as roads, sidewalks, and animated cars.
 * This class handles animating cars moving up and down within the lane
 *
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 ************************************************************************************/
public class Lane extends Pane
{
    //An array of file locations of car graphics
    protected String[] cars = {"/assets/car1.png", "/assets/car2.png", "/assets/car3.png", "/assets/car4.png", "/assets/car5.png", "/assets/car6.png" };
    
    //instance data
    protected Random rNG = new Random();
    protected int y;
    protected int x;
    protected double laneHeight = 384;
    protected double carHeight = 50;
    
    //nodes used in the lane
    protected ImageView road;
    protected ImageView side;
    protected ImageView car1;
    protected ImageView car2;
    protected ImageView end;
    
    /*******************************************************************
     * Constructor with parameters for Lane
     * 
     * @param y The y-coordinate of the lane
     * @param x The x-coordinate of the lane
     ********************************************************************/
    public Lane(int y, int x)
    {
        //initialising images
        road = new ImageView(new Image("/assets/road.png"));
        side = new ImageView(new Image("/assets/side.png"));
        car1 = new ImageView(new Image(cars[rNG.nextInt(cars.length)]));
        car2 = new ImageView(new Image(cars[rNG.nextInt(6)]));
        
        draw();
        //Add animations to the car
        animUp(car1);
        animDown(car2);
    }
    
    /***********************************************************
     * Method to draw the Lane
     **********************************************************/
    public void draw()
    {
        //Set the size positions of all the assets of the lane
        road.setX(x+30);
        road.setY(y);
        road.setFitHeight(laneHeight);
        road.setFitWidth(64);
        
        side.setX(x);
        side.setY(y);
        side.setFitHeight(laneHeight);
        side.setFitWidth(30.5);
        car1.setX(x+30);
        car1.setY(y);
        car1.setFitHeight(carHeight);
        car1.setFitWidth(30);
        
        car2.setX(x+62);
        car2.setY(y);
        car2.setFitHeight(carHeight);
        car2.setFitWidth(30);
        //turn the car around
        car2.getTransforms().add(new Rotate(180, 77, 25));
        
        this.getChildren().addAll(road, side, car1, car2);
    }
    
    /*******************************************************************************************************
     * Method to animate the motion of the cars downwards and back indefinitely
     * 
     * @param car The car to animate
     *******************************************************************************************************/
    public void animDown(ImageView car)
    {
        //Create a transition
        TranslateTransition transition = new TranslateTransition();
        //Duration set to a number with rng(multiple rng for wider range of possible speeds), to avoid having cars with same speed
        transition.setDuration(Duration.seconds(0.5+ 0.22*(1+rNG.nextInt(3)*(2+rNG.nextInt(4)*(1+rNG.nextInt(3)))))); 
        // Repeat indefinitely
        transition.setCycleCount(TranslateTransition.INDEFINITE); 
        // Make it move back and forth 
        // Apply the transition to the input object
        transition.setNode(car); 
        //set the motion
        transition.setByY(laneHeight - carHeight);
        
        //Play the animation
        transition.play();
    }
    
    /*******************************************************************************************************
     * Method to animate the motion of the cars upwards and back indefinitely
     * 
     * @param car The car to animate
     *******************************************************************************************************/   
    public void animUp(ImageView car)
    {
        //move the car to the bottom,
        car.setY(333);
        //Create a transition
        TranslateTransition transition = new TranslateTransition();
        //Duration set to a number with rng(multiple rng for wider range of possible speeds), to avoid having cars with same speed
        transition.setDuration(Duration.seconds(0.5+ 0.15*(2+rNG.nextInt(4)*(2+rNG.nextInt(4)*(1+rNG.nextInt(4)))))); 
        // Repeat indefinitely
        transition.setCycleCount(TranslateTransition.INDEFINITE); 
        // Make it move back and forth
        // Apply the transition to the input object
        transition.setNode(car); 
        //set the motion
        transition.setByY(-1* (laneHeight - carHeight));
        
        //Play the animation
        transition.play();
    }
}
