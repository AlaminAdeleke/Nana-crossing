import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.effect.Glow;

/**
 * The main game screen class
 *
 * @author Alamin Adeleke
 * @version 1
 */
public class GameScreen extends Pane
{
    
    BorderPane holder = new BorderPane();
    ImageView BG = new ImageView(new Image("/assets/GameBG.jpg"));
    
    Input inputPane = new Input();
    Output outputPane = new Output();

    GameLogic backEnd = new GameLogic();
    
    MainView lanes = new MainView();
    boolean wardenCalled = false;
    boolean canCross = true;
    public GameScreen()
    {
        holder.setLeft(inputPane);
        holder.setBottom(outputPane);
        holder.setCenter(lanes);
        holder.setLayoutX(20);
        holder.setLayoutY(20);
        
        BG.setFitHeight(560);
        BG.setFitWidth(955);
        BG.setOpacity(0.7);
        
        //this.setPadding(new Insets(10));
        this.getChildren().addAll(BG, holder);
        listen();
    }
    
    //A method to update the output to that in the backend
    public void update()
    {
        outputPane.setOutput(backEnd.getLane(), backEnd.getVBucks(), backEnd.getOdds());
        lanes.setNanaLane(backEnd.getLane());
    }
    
    public Input getInput()
    {
        return inputPane;
    }
    
    public boolean canReset()
    {
        boolean can;
        if((backEnd.lane == 8) || wardenCalled || !canCross)
        {
            can = true;
        }
        else
        {
            can = false;
            
        }
        return can;
    }
    
    //Method to reset the game after the 
    public void reset()
    {
        wardenCalled = false;
        lanes.setGame();
        canCross = true;
    }
    
    public void listen()
    {
        //Events for when the cross button is clicked
        inputPane.cross.setOnMouseClicked(e ->
        {
            if(backEnd.lane == 8)
            {
                //canCross = false;
                lanes.setWin();
            }
            
            //if Succesfully crossed
            if(backEnd.cross() && canCross)
            {
                //Lane.walk(lanes.l[backEnd.getLane()].nana);
                backEnd.update();
                if(backEnd.lane == 8)
                {
                    lanes.setWin();
                }
            }
            //else display the loseing screen
            else
            {   
                lanes.setLose();
                canCross = false;
            }
            update();
        });
          //Events for when the call warden button is clicked
        inputPane.stop.setOnMouseClicked(e ->
        {
            //If the game is still in session,
            if(canCross)
            {
                canCross = false;
                //Set the game screen to output a win message
                wardenCalled = true;
                lanes.setWin();
                update();
            }
        });
        
        Glow crossGlow = new Glow();
        Glow stopGlow = new Glow();
        Glow resetGlow = new Glow();
        // Set the intensity of the glow to high(0.8) when mouse hovering on it
        inputPane.cross.setOnMouseEntered(e ->
        {
            if(canCross)
            {
                crossGlow.setLevel(0.86); // Set the intensity of the glow
                inputPane.cross.setEffect(crossGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not highlited
        inputPane.cross.setOnMouseExited
        (e ->{
            crossGlow.setLevel(0.0); 
            inputPane.cross.setEffect(crossGlow);
        });
        
        
        // Set the intensity of the glow to high(0.8) when mouse hovering on it
        inputPane.stop.setOnMouseEntered(e ->
        {
            if(canCross)
            {
                stopGlow.setLevel(0.86); // Set the intensity of the glow
                inputPane.stop.setEffect(stopGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not highlited
        inputPane.stop.setOnMouseExited
        (e ->{
            stopGlow.setLevel(0.0); 
            inputPane.stop.setEffect(stopGlow);
        });
        
        
        // Set the intensity of the glow to high(0.8) when mouse hovering on it
        inputPane.reset.setOnMouseEntered(e ->
        {
            if(canReset())
            {
                resetGlow.setLevel(0.86); // Set the intensity of the glow
                inputPane.reset.setEffect(resetGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not highlited
        inputPane.reset.setOnMouseExited
        (e ->{
            resetGlow.setLevel(0.0); 
            inputPane.reset.setEffect(resetGlow);
        });
    } 
    }
    