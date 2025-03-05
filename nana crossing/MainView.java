import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
/*********************************************************************************
 * @title The MainView Class
 * A pane consisting of a winning screen, a loosing screen, and the main game view
 *
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *********************************************************************************/
public class MainView extends Pane
{
    //Create the lanes of the main game view
    protected Rectangle border = new Rectangle(-5,-8, 795, 400);
    protected HBox lanes = new HBox(-3);
    protected Lane l1 = new Lane(0,0);
    protected Lane l2 = new Lane(0,0);
    protected Lane l3 = new Lane(0,0);
    protected Lane l4 = new Lane(0,0);
    protected Lane l5 = new Lane(0,0);
    protected Lane l6 = new Lane(0,0);
    protected Lane l7 = new Lane(0,0);
    protected Lane l8 = new Lane(0,0);
    
    //Special lane in the last lane
    protected EndLane l9 = new EndLane(0,0);
    
    //An array to easilly modify the lanes in a for loop
    protected Lane[] l = {l1, l2, l3, l4, l5, l6, l7, l8, l9}; 
    
    //moving nana characer
    protected ImageView nana;
    
    //Animation instances
    protected TranslateTransition walk = new TranslateTransition();
    protected  double distance = 0;
    
    //Instance of the win ond lose screen
    protected Text message = new Text(20,377, "This is an easter Egg");
    
    //Win and lose screens
    protected ImageView loseIMG = new ImageView(new Image("/assets/lose.jpeg"));
    protected ImageView winIMG = new ImageView(new Image("/assets/win.jpeg"));
    protected Pane loseScreen = new Pane();
    protected Pane winScreen = new Pane();
    
    
    /*******************************************************************************************************
     * Default constructor for MainView
     *******************************************************************************************************/
    public MainView()
    {
        //create the lanes and set nana to the first lane
        lanes.getChildren().addAll(l1, l2, l3 ,l4, l5, l6, l7, l8, l9);
        //setNanaLane(0);
        
        //
        nana = new ImageView(new Image("/assets/nana.png"));
        nana.setX(3);
        nana.setY(165);
        nana.setFitHeight(55);
        nana.setFitWidth(26);
        //Apply animation to nana
        walk(nana);
        
        
        //Customize the assets of the panes
        border.setArcWidth(10);
        border.setArcHeight(10);
        border.setOpacity(0.5);
            
        loseIMG.setFitWidth(784);
        loseIMG.setFitHeight(384);
        winIMG.setFitWidth(778);
        winIMG.setFitHeight(384);
        
        loseScreen.getChildren().addAll(loseIMG, message);
        winScreen.getChildren().addAll(winIMG);
        
        //Customizing the failure display message
        message.setFont(Font.font("DejaVu Math TeX Gyre", FontWeight.BOLD, 18));
        message.setFill(Color.RED.darker());
        message.setStroke(Color.BLACK);


        //add the lanes to the centerview
        setGame();
    }
    
    /*********************************************************************
     * Method to set the main game view
     *********************************************************************/
    public void setGame()
    {
        this.getChildren().clear();
        this.getChildren().addAll(border, lanes, nana);
    }
    
    /**********************************************************
     * Method to set the win screen
     **********************************************************/
    public void setWin()
    {
        this.getChildren().clear();
        this.getChildren().addAll(border, winScreen);        
    }
    
    /***********************************************************
     * Method to set the lose screen
     ***********************************************************/
    public void setLose()
    {
        message.setText(LossMessages.out());
        this.getChildren().clear();
        this.getChildren().addAll(border, loseScreen);
    }
    
    /***********************************************************
     * Method to reset position of character
     ***********************************************************/    
    public void reset()
    {
        TranslateTransition walk = new TranslateTransition();
        
        walk.setDuration(Duration.seconds(0.1)); // duration of one move
        walk.setCycleCount(0);
        walk.setAutoReverse(false); // Make it move back and forth
        walk.setNode(nana); // Apply the transition to the rectangle
        walk.setByX(distance * -1);
        walk.play();
    }
    
    
    /***********************************************************
     * Method to move character across one lane
     ***********************************************************/       
    public void walk(ImageView nana)
    {
        walk.setDuration(Duration.seconds(0.3)); // duration of one move
        walk.setCycleCount(0);
        walk.setAutoReverse(false); // Make it move 
        walk.setNode(nana); // Apply the transition to nana
        walk.setByX(91);
    }
}
