import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
 /**********************************************************************************************************
 * @title The LaneIndecator class.
 * A class with simplistic visual representation of the lanes
 * 
 * @author Alamn Adeleke, Chuckwunonso Ekweaga, Ashlee Muchirahondo
 * @version version 1
 **********************************************************************************************************/
public class LaneIndecator extends Pane
{
    //positon values
    protected int x,y;
    //array of rectangles, 
    protected Rectangle r1 = new Rectangle(23, 60);
    protected Rectangle r2 = new Rectangle(23, 60);
    protected Rectangle r3 = new Rectangle(23, 60);
    protected Rectangle r4 = new Rectangle(23, 60);
    protected Rectangle r5 = new Rectangle(23, 60);
    protected Rectangle r6 = new Rectangle(23, 60);
    protected Rectangle r7 = new Rectangle(23, 60);
    protected Rectangle r8 = new Rectangle(23, 60);
    protected Rectangle r9 = new Rectangle(23, 60);
    protected Rectangle[] r = {r1,r2,r3,r4,r5,r6,r7,r8,r9}; 
    
    /*******************************************************************************************************
     * Two Parameter constructor for LaneIndecator
     * 
     * @param x The x-coordinate of the indicator
     * @param y The y-coordinate of the indicator
     *******************************************************************************************************/
    public LaneIndecator(int x, int y)
    {
        //for each of the rectangles,
        for(int i = 0 ; i <9 ; i++)
        {
            //set the position, spaced based on where  in the array
            r[i].setX(y+i*26);
            r[i].setY(y+5);
            
            //Customize the rectangle
            r[i].setStrokeWidth(1);
            r[i].setStroke(Color.BLACK);
            r[i].setFill(Color.DARKCYAN);
            
            //Add to the class
            this.getChildren().add(r[i]);
        }
        //Set the first to be turned "on"
        setOn(0);
    }
    
    /*********************************************************
     * Method to set the current lane
     * 
     * @param lane The current lane to highlight
     *********************************************************/
    public void setOn(int lane)
    {
        //if input is in valid(has a corresponding rectangle),
        if(lane<=8 || lane>=0)
        {
            //Turn all "off"
            for(int i = 0 ; i <9 ; i++)
            {
                r[i].setFill(Color.CYAN);
            }
            //then turn that which matches the input "on"
            r[lane].setFill(Color.LIGHTCYAN);
        }   
    }
}
