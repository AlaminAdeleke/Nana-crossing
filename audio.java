import javax.sound.sampled.*;
import java.io.File;
import java.util.Scanner;

/**
 * Write a description of class audio here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class audio
{
    public static void main(String[] args) throws LineUnavailableException,java.io.IOException,UnsupportedAudioFileException
    {
        File file = new File("/assets/sounds/click.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
