import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.text.Font;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class HomeScreen here.
 *
 * @author Alamin Adeleke
 * @version (a version number or a date)
 */
public class HomeScreen extends Pane
{
    ImageView BG = new ImageView(new Image("/assets/BG.png"));
    public ImageView playB = new ImageView(new Image("/assets/play.png"));
    
    
    Text t1 = new Text(30,190   ,"Type your \nnana's name");
    Text t2 = new Text(370,185,"Probability Difference\n(5% recomended)");
    Text t3 = new Text(370,253,"Reward per Lane\n(100 Recomended)");
    Text t4 = new Text(440,230,"%");
    Text t5 = new Text(436,290,"VBucks");
    
    TextField nameField = new TextField("");
    TextField oddsField = new TextField("5");
    TextField payField = new TextField("125"); 
    
    private List<String> musicFiles = new ArrayList<>(); // List of music file paths
    private static  MediaPlayer mediaPlayer; // MediaPlayer for background music
    private int currentTrackIndex = 0; // Index of the current track

    public static void playMusic(String filePath) 
    {
        try 
        {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Stop the previous track if it's playing
            }
    
            Media media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


    public HomeScreen()
    {
        setPrefWidth(600);  // Set a preferred width for the screen
        setPrefHeight(400);
        
        draw();
        loadMusicFiles("C:/Users/HP USER/Desktop/Indonisea/nana crossing/assets/sounds/background music"); // Load background music    
    }
    
    public void draw()
    {
        //Customize the assets of the Home page        
        playB.setLayoutX(60);
        playB.setLayoutY(260);
        playB.setFitWidth(35);
        playB.setFitHeight(35);
        //Animation
        glow(playB);
        
        
        t2.setFont(new Font(9));
        t3.setFont(new Font(9));
        
        
        
        nameField.setLayoutX(30);
        nameField.setLayoutY(215);
        nameField.setPrefWidth(100);
        
        oddsField.setLayoutX(370);
        oddsField.setLayoutY(210);
        oddsField.setPrefWidth(65);
        
        payField.setLayoutX(370);
        payField.setLayoutY(275);
        payField.setPrefWidth(65);
        
        Rectangle holder1 = new Rectangle(360,170,120,140);
        holder1.setOpacity(0.65);
        holder1.setArcWidth(20);
        holder1.setArcHeight(20);
        holder1.setFill(Color.CORNFLOWERBLUE);
        Rectangle holder2 = new Rectangle(20,170,120,140);
        holder2.setOpacity(0.65);
        holder2.setArcWidth(20);
        holder2.setArcHeight(20);
        holder2.setFill(Color.CORNFLOWERBLUE);
        
        this.getChildren().addAll(BG, holder1, holder2, t1, t2, t3, t4, t5, nameField, oddsField, payField, playB);
    }
    
    //Method to create the hovering effect of a play button, as long as all fields have been filled
    public void glow(ImageView img)
    {
        Glow glow = new Glow();
        // Set the intensity of the glow to high(0.8) when mouse hovering on it, as long as all fields have been filled
        img.setOnMouseEntered(
        e ->{
            if(canGoToGameScreen())
            {
                glow.setLevel(0.86); // Set the intensity of the glow
                img.setEffect(glow);
            }
        });
        // Set the intensity of the glow back to normal when mouse not highlited
        img.setOnMouseExited(
        e ->{
            glow.setLevel(0.0); 
            img.setEffect(glow);
        });
    }
    
    //Method to set the pay and odds and name (used by the whole program) to the values inputted on the home screen
    public void setValues()
    {
        App.setOdds(Integer.valueOf(oddsField.getText()));
        App.setPay(Integer.valueOf(payField.getText()));
        App.setName(nameField.getText());
    }
    
    public boolean canGoToGameScreen()
    {
        if((nameField.getText().length() > 1)&&(payField.getText().length() > 0)&&(oddsField.getText().length() > 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void loadMusicFiles(String directoryPath) 
    {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".mp3")); // Filter for .mp3 files
        if (files != null) 
        {
            for (File file : files) 
            {
                musicFiles.add(file.getAbsolutePath()); // Add file paths to the list
            }
        }
        playNextTrack(); // Start playing the first track
    }
    
    public void playNextTrack() 
    {
        if (currentTrackIndex >= musicFiles.size()) 
        {
            currentTrackIndex = 0; // Loop back to the first track
        }
        if (!musicFiles.isEmpty()) 
        {
            String nextTrack = musicFiles.get(currentTrackIndex); // Get the next track
            Media media = new Media(new File(nextTrack).toURI().toString()); // Create a Media object
            mediaPlayer = new MediaPlayer(media); // Create a MediaPlayer
            mediaPlayer.setOnEndOfMedia(this::playNextTrack); // Set the next track to play
            mediaPlayer.play(); // Play the track
            currentTrackIndex++; // Increment the track index
        }
    }


    public void stopMusic() 
    {
        if (mediaPlayer != null) 
        {
            mediaPlayer.stop(); // Stop the music
        }
    }

}
