import java.util.Random;

/**
 * The main game screen class
 *
 * @author Alamin Adeleke
 * @version 1
 */
public class GameLogic
{
    Random rNG = new Random();
    protected int lane;
    protected int odds;
    protected int vBucks;
    
    public GameLogic()
    {
        reset();
    }
    
    public void update()
    {
        if(lane<8)
        {
            lane++;
            vBucks += App.getPay();
            odds -= App.getOdds();
        }
        
    }
    
    //A method to reset the instance data
    public void reset()
    {
        lane = 0;
        vBucks = 0;
        odds = 90;
    }
    
    public int getLane()    {return lane;}
    public int getVBucks()  {return vBucks;}
    public int getOdds()    {return odds;}
    
    public boolean cross(int probability)
    {
        boolean crossed;
        if(probability >= rNG.nextInt(100) + 1)
        {
            crossed = true;
        }
        else
        {
            crossed = false;
            vBucks = 0;
        }
        return crossed;
    }
    
    public boolean cross()
    {
        boolean crossed;
        if(odds >= rNG.nextInt(100) + 1)
        {
            crossed = true;
        }
        else
        {
            crossed = false;
            vBucks = 0;     
        }
        return crossed;
    }
}
