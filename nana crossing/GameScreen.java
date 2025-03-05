import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.effect.Glow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.Random;

/*********************************************************************************
 * @title The GameScreen class.
 * The main game screen class, where all components of the game are managed.
 *
 * @author Alamn Adeleke, Chuckwunonso Ekweaga, Ashlee Muchirahondo
 * @version 1
 *********************************************************************************/
public class GameScreen extends Pane
{
    //Instance Data and objects
    protected BorderPane holder = new BorderPane();
    protected ImageView BG = new ImageView(new Image("/assets/GameBG.jpg"));
    protected Random random = new Random();
    
    //left and bottom views
    protected Input inputPane = new Input();
    protected Output outputPane = new Output();
    
    //Arithmetic of the game
    protected GameLogic backEnd = new GameLogic();
    
    //Center view
    protected MainView lanes = new MainView();
    protected boolean gameWon = false;
    protected boolean canCross = true;
    
    //Sound effects instances
    protected Media clickSound = new Media(getClass().getResource("/assets/sounds/click.mp3").toExternalForm());
    protected MediaPlayer click = new MediaPlayer(clickSound);
    protected Media loseSound = new Media(getClass().getResource("/assets/sounds/lose sound.mp3").toExternalForm());
    protected MediaPlayer lose = new MediaPlayer(loseSound);
    
    protected List<MediaPlayer> mediaPlayers = new ArrayList<>();
    protected Timeline timeline;
    
    /************************************************************************************************
     * Default COnstructor for The GameScreen Class
     ************************************************************************************************/ 
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
        update();
        listen();
        music();
    }
    
    
    /************************************************************************************************
     * Method to update the output to that in the backend
     ************************************************************************************************/        
    public void update()
    {
        outputPane.setOutput(backEnd.getLane(), backEnd.getVBucks(), backEnd.getOdds());
        inputPane.reset.setDisable(!canReset());
    }
    
    public Input getInput()
    {
        return inputPane;
    }
    
    /************************************************************************************************
     *  Method to determine whether the player should be allowed to return to the homeScreen
     ************************************************************************************************/    
    public boolean canReset()
    {
        boolean can;
        //If the game has either been won or lost
        if((backEnd.lane == 8) || gameWon || !canCross)
        {
            can = true;
        }
        else
        {
            can = false;
        }
        return can;
    }
    
    //methods to play horn sounds randomly
    public void music()
    {
        // Load sound files into MediaPlayer objects
        String[] soundFiles = {"/assets/sounds/horns/horn1.mp3","/assets/sounds/horns/horn2.mp3","/assets/sounds/horns/horn3.mp3",
                                "/assets/sounds/horns/horn4.mp3", "/assets/sounds/horns/horn5.mp3","/assets/sounds/horns/horn6.mp3",
                                "/assets/sounds/horns/horn7.mp3"
        };
        //for each file in the array,
        for (String file : soundFiles) 
        {
            //import the sound and set the volume
            Media sound = new Media(getClass().getResource(file).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayers.add(mediaPlayer);
            mediaPlayer.setVolume(0.2);
        }
    }
    
    public void startPlayingSounds() 
    {
        //only if the timeline is null, (to avoid multiple instances)
        if (timeline == null) 
        {
            // Create a Timeline to play sounds at regular intervals
            timeline = new Timeline(new KeyFrame(Duration.seconds(5), 
            event -> {
                // Select a random sound
                int randomIndex = random.nextInt(mediaPlayers.size());
                MediaPlayer mediaPlayer = mediaPlayers.get(randomIndex);
                mediaPlayer.setVolume(0.2);
                // Reset and play the sound
                mediaPlayer.seek(mediaPlayer.getStartTime());
                mediaPlayer.play();
            }));
        }
        // Set the timeline to repeat indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    /**********************************************************************
     *  Method to stop playing horn sounds
     **********************************************************************/    
    public void stopPlayingSounds() 
    {
        //if the timeline not set to null,
        if (timeline != null) 
        {
            //stop it then set to null
            timeline.stop();
            timeline = null;
        }
        
        // Stop all media players
        for (MediaPlayer mediaPlayer : mediaPlayers) 
        {
            mediaPlayer.stop();
        }
    }
    
    /**********************************************************************
     *  Method to reset the game to starting state
     **********************************************************************/
    public void reset()
    {        
        gameWon = false;
        lanes.setGame();
        canCross = true;
        inputPane.cross.setDisable(false);
        inputPane.cross.setOpacity(1.0);
        inputPane.stop.setDisable(false);
        inputPane.stop.setOpacity(1.0);         
    }
    
    /****************************************************************************
     *  Method to set function for all the buttons used in the game
     ***************************************************************************/    
    public void listen()
    {
    //Events for when the cross button is clicked
    inputPane.cross.setOnMouseClicked(e ->
    {
        //if on the final lane
        if(backEnd.lane == 8)
        {
            gameWon = true;
            lanes.setWin();
            disableAllButtonsExceptReset();
        }
        
        //if player hasn't lost, and successfully crossed
        if(backEnd.cross() && canCross)
        {
            //play click sound
            click.seek(click.getStartTime());
            click.play();
            //play nana walking animation
            lanes.walk.play();
            lanes.distance += 91;
            //go to the next lane
            backEnd.update();
            //If on the final lane
            if(backEnd.lane == 8)
            {
                //display win
                lanes.setWin();
                gameWon = true;
                disableAllButtonsExceptReset();
            }
        }
        //else if the game has not already been won yet, display the losing screen
        else if(!gameWon)
        {
            //play losing sound effect
            lose.seek(lose.getStartTime());
            lose.play();
            
            //set the losing screen
            lanes.setLose();
            canCross = false;
            disableAllButtonsExceptReset();
        }
        //update the output
        update();
    });
        
          //Events for when the call warden button is clicked
        inputPane.stop.setOnMouseClicked(e ->
        {
            //If the game is still in session,
            if(canCross)
            {
                click.seek(click.getStartTime());
                click.play();
                canCross = false;
                //Set the game screen to output a win message
                gameWon = true;
                lanes.setWin();
                update();
                inputPane.stop.setDisable(true);
                inputPane.stop.setOpacity(0.5);                
            }
        });
        //event for reset button found in app class
        
        //glow effects for the buttons(when hovered)
        Glow crossGlow = new Glow();
        Glow stopGlow = new Glow();
        Glow resetGlow = new Glow();
        
        // Set the intensity of the glow to high(0.86) when mouse hovering on it
        inputPane.cross.setOnMouseEntered(e ->
        {
            //if the button should be clickable,
            if(canCross)
            {
                crossGlow.setLevel(0.86); 
                inputPane.cross.setEffect(crossGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not on it
        inputPane.cross.setOnMouseExited
        (e ->{
            crossGlow.setLevel(0.0); 
            inputPane.cross.setEffect(crossGlow);
        });
        
        // Set the intensity of the glow to high(0.86) when mouse hovering on it
        inputPane.stop.setOnMouseEntered(e ->
        {
            //if the button should be clickable,
            if(canCross)
            {
                stopGlow.setLevel(0.86); // Set the intensity of the glow
                inputPane.stop.setEffect(stopGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not on it
        inputPane.stop.setOnMouseExited
        (e ->{
            stopGlow.setLevel(0.0); 
            inputPane.stop.setEffect(stopGlow);
        });
        
        // Set the intensity of the glow to high(0.86) when mouse hovering on it
        inputPane.reset.setOnMouseEntered(e ->
        {
            //if the button should be clickable,
            if(canReset())
            {
                resetGlow.setLevel(0.86); // Set the intensity of the glow
                inputPane.reset.setEffect(resetGlow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not on it
        inputPane.reset.setOnMouseExited
        (e ->{
            resetGlow.setLevel(0.0); 
            inputPane.reset.setEffect(resetGlow);
        });
    } 
    
    /************************************************************************************
     * Method to disable all buttons except reset button when player has ended the game
     ***********************************************************************************/
    public void disableAllButtonsExceptReset() 
    {
        inputPane.cross.setDisable(true);
        inputPane.cross.setOpacity(0.5);
        inputPane.stop.setDisable(true);
        inputPane.stop.setOpacity(0.5);

    }
    }
    
