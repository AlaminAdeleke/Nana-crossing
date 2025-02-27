import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The class of all the inputs of the game screen
 *
 * @author Alamin Adeleke
 * @version 1
 */
public class Output extends Pane
{
    Rectangle BG = new Rectangle(-10, 0, 930, 130);
    LaneIndecator laneI = new LaneIndecator(103, 30);
    
    Text laneHeader = new Text(20,30,"Current Lane;");
    Text vBucksHeader = new Text(285,30,"Current VBucks;");
    Text oddsHeader = new Text(525,30,"Odds of success;");
    
    //Text lane = new Text(70,70,"Lane 1 of 8");
    Text vBucks = new Text(285,70,"100 VBucks");
    Text odds = new Text(525,70,"85%");
    public Output()
    {
        BG.setArcWidth(15);
        BG.setArcHeight(15);
        BG.setFill(Color.BLACK);
        BG.setOpacity(0.6);
        
        laneHeader.setFont(new Font(30));
        vBucksHeader.setFont(new Font(30));
        oddsHeader.setFont(new Font(30));
        //lane.setFont(new Font(27));
        vBucks.setFont(new Font(27));
        odds.setFont(new Font(27));
        
        this.getChildren().addAll(/*lane,*/ BG, laneHeader, vBucks, vBucksHeader, odds, oddsHeader, laneI);
    }
    
    /*************************************************
       Method to take input and set the 
       @param - 
    ***************************************************/
    public void setOutput(int lane, int vBucks, int odds)
    {
        //this.lane.setText("Lane " + lane + " of 8");
        laneI.setOn(lane);
        this.vBucks.setText(vBucks + " VBucks");
        this.odds.setText(odds + "%");
    }
}
