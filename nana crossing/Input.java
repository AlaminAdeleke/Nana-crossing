import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**********************************************************************************************************
 * @title The Input class.
 * A pane containing all the buttons used in the game.
 * 
 * 
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version version 1
 **********************************************************************************************************/
public class Input extends Pane
{
    //Background nodes
    Rectangle BG = new Rectangle(-10,-10,130,400);
    VBox holder = new VBox();
    
    //Instance of the buttons(action methods found in GameScreen Class)
    ImageView cross = new ImageView(new Image("/assets/cross.png"));
    ImageView stop = new ImageView(new Image("/assets/call warden.png"));
    ImageView reset = new ImageView(new Image("/assets/reset.png"));
       
    
    /****************************************************
     * Default Constructor for Input class
     *****************************************************/
    public Input()
    {
        //Setting the dimensions of all the buttons
        cross.setFitHeight(50);
        cross.setFitWidth(90);
        stop.setFitHeight(50);
        stop.setFitWidth(90);
        reset.setFitHeight(50);
        reset.setFitWidth(90);
        
        //Customizing background nodes
        holder.setLayoutX(10);
        holder.setLayoutY(10);
        holder.setSpacing(70);
        BG.setArcWidth(15);
        BG.setArcHeight(15);
        BG.setFill(Color.BLACK);
        BG.setOpacity(0.6);
        
        //add to pane
        holder.getChildren().addAll(cross, stop, reset);
        this.getChildren().addAll(BG, holder);
    }    
}
