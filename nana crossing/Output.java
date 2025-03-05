import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/***************************************************************
 * @title The Output Class
 * The class of all the inputs of the game screen
 *
 * @author Alamn Adeleke, Chukwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 **************************************************************/
public class Output extends Pane
{
    //background object
    protected Rectangle BG = new Rectangle(-10, 0, 930, 130);
    //graphical lane indicator
    protected LaneIndecator laneI = new LaneIndecator(103, 35);
    
    protected Text laneHeader = new Text(25,35,"Current Lane;");
    protected Text vBucksHeader = new Text(295,35,"Current VBucks;");
    protected Text oddsHeader = new Text(545,35,"Odds of success;");
    
    //Text lane = new Text(80,70,"Lane 1 of 8");
    protected Text vBucks = new Text(295,75,"XX VBucks");
    protected Text odds = new Text(545,75,"XX%");
    /****************************************************
     * Default Constructor for the Output Class
     ***************************************************/
    public Output()
    {
        //Cusomizing the background rectangle
        BG.setArcWidth(15);
        BG.setArcHeight(15);
        BG.setFill(Color.BLACK);
        BG.setOpacity(0.6);
        
        //Customizing the text objects
        laneHeader.setFont(new Font(30));
        vBucksHeader.setFont(new Font(30));
        oddsHeader.setFont(new Font(30));
        //lane.setFont(new Font(27));
        vBucks.setFont(new Font(27));
        odds.setFont(new Font(27));
        
        this.getChildren().addAll(BG, /*lane, */laneHeader, vBucks, vBucksHeader, odds, oddsHeader, laneI);
    }
    
    /*******************************************************************************
     Method to set the objects of the class and set based on the input
       
     @param - lane, player's current vbucks, odds of success
    *******************************************************************************/
    public void setOutput(int lane, int vBucks, double odds)
    {
        //this.lane.setText("Lane " + lane + " of 8");
        laneI.setOn(lane);
        this.vBucks.setText(vBucks + " VBucks");
        this.odds.setText(odds + "%");
    }
}
