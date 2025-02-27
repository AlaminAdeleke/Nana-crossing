import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Write a description of class LaneIndecator here.
 *
 * @author Alamin Adeleke
 * @version (a version number or a date)
 */
public class LaneIndecator extends Pane
{
    protected int x,y;
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
    
    public LaneIndecator(int x, int y)
    {
        
        for(int i = 0 ; i <9 ; i++)
        {
            r[i].setX(y+i*26);
            r[i].setY(y+5);
            r[i].setStrokeWidth(1);
            r[i].setStroke(Color.BLACK);
            r[i].setFill(Color.DARKCYAN);
            this.getChildren().add(r[i]);
        }
        
        setOn(0);
    }
    
    public void setOn(int lane)
    {
        if(lane<=8 || lane>=0)
        {
            for(int i = 0 ; i <9 ; i++)
            {
                r[i].setFill(Color.CYAN);
            }
            r[lane].setFill(Color.LIGHTCYAN);
        }   
    }
    
}
