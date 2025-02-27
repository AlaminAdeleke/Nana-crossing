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
import javafx.scene.effect.InnerShadow;
/**
 * Write a description of class MainView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainView extends Pane
{
    //Create the lanes of the main game view
    Rectangle border = new Rectangle(-5,-8, 795, 400);
    HBox lanes = new HBox(-3);
    Lane l1 = new Lane(0,0);
    Lane l2 = new Lane(0,0);
    Lane l3 = new Lane(0,0);
    Lane l4 = new Lane(0,0);
    Lane l5 = new Lane(0,0);
    Lane l6 = new Lane(0,0);
    Lane l7 = new Lane(0,0);
    Lane l8 = new Lane(0,0);
    //Special lane in the last lane
    Lane l9 = new Lane(0,0,0);
    Lane[] l = {l1, l2, l3, l4, l5, l6, l7, l8, l9}; 
    
    //Instance of the win ond lose screen
    Text message = new Text(20,377, "This is an easter Egg");
    

    ImageView loseIMG = new ImageView(new Image("/assets/lose.jpeg"));
    ImageView winIMG = new ImageView(new Image("/assets/win.jpeg"));
    Pane loseScreen = new Pane();
    Pane winScreen = new Pane();
    
    
    public MainView()
    {
        //create the lanes and set nana to the first lane
        lanes.getChildren().addAll(l1, l2, l3 ,l4, l5, l6, l7, l8, l9);
        setNanaLane(0);
        
        //Customize the assets of the pane
        border.setArcWidth(10);
        border.setArcHeight(10);
        border.setOpacity(0.5);
            
        loseIMG.setFitWidth(784);
        loseIMG.setFitHeight(384);
        winIMG.setFitWidth(778);
        winIMG.setFitHeight(384);
        
        loseScreen.getChildren().addAll(loseIMG, message);
        winScreen.getChildren().addAll(winIMG);
        
        message.setFont(Font.font("DejaVu Math TeX Gyre", FontWeight.BOLD, 18));
        message.setFill(Color.DARKGRAY.darker().darker());
        InnerShadow shadow = new InnerShadow();
        shadow.setRadius(2.0);
        shadow.setColor(Color.LIGHTGRAY);
        message.setEffect(shadow);
        message.setStroke(Color.GRAY);
        message.setStrokeWidth(1.5);
        message.setStyle("-fx-font-smoothing-type: gray;");
        //message.setTextAlignment(TextAlignment.CENTER);


        
        setGame();
    }
    
    //A method to set which lane nana is on based on input
    public void setNanaLane(int lane)
    {
        //Clear all the lanes
        if(lane<=8 || lane>=0)
        {
            for(int i = 8 ; i >= 0 ; i--)
            {
                l[i].hideNana();
            }
            //add nana to the input lane
            l[lane].showNana();
        }
    }
    
    
    //Methods to set the main game view, between the game, winning screen and loseing screen(after clearing the previous pane)
    public void setGame()
    {
        this.getChildren().clear();
        this.getChildren().addAll(border, lanes);
    }
    public void setWin()
    {
        this.getChildren().clear();
        this.getChildren().addAll(border, winScreen);        
    }
    public void setLose()
    {
        message.setText(LossMessages.out());
        this.getChildren().clear();
        this.getChildren().addAll(border, loseScreen);
    }
}
