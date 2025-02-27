import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Write a description of class Lane here.
 *
 * @author Alamin Adeleke
 * @version (a version number or a date)
 */
public class Lane extends Pane
{
    String[] cars = {"/assets/car1.png", "/assets/car2.png", "/assets/car3.png", "/assets/car4.png", "/assets/car5.png", "/assets/car6.png" };
    
    Random rNG = new Random();
    int y;
    int x;
    ImageView road;
    ImageView side;
    ImageView nana;
    ImageView car1;
    ImageView car2;
    ImageView end;
    
    boolean specialLane;
    
    static TranslateTransition walk = new TranslateTransition();
    public Lane(int y, int x)
    {
        specialLane = false;
        road = new ImageView(new Image("/assets/road.png"));
        side = new ImageView(new Image("/assets/side.png"));
        nana = new ImageView(new Image("/assets/nana.png"));
        car1 = new ImageView(new Image(cars[rNG.nextInt(6)]));
        car2 = new ImageView(new Image(cars[rNG.nextInt(6)]));
        
        draw();
        anim(car1);
        anim(car2);
    }
    
    public Lane(int x, int y, int a)
    {
        specialLane = true;
        nana = new ImageView(new Image("/assets/nana.png"));
        end = new ImageView(new Image("/assets/end.png"));
        
        end.setX(x);
        end.setY(y);
        end.setFitHeight(384);
        end.setFitWidth(55);
        
        nana.setX(x+4);
        nana.setY(y+164);
        nana.setFitHeight(57);
        nana.setFitWidth(28);
        
        this.getChildren().addAll(end, nana);
    }
    
    public void draw()
    {
        road.setX(x+30);
        road.setY(y);
        road.setFitHeight(384);
        road.setFitWidth(64);
        
        side.setX(x);
        side.setY(y);
        side.setFitHeight(384);
        side.setFitWidth(30.5);
        
        nana.setX(x+4);
        nana.setY(y+164);
        nana.setFitHeight(57);
        nana.setFitWidth(28);
        
        car1.setX(x+36);
        car1.setY(y);
        car1.setFitHeight(50);
        car1.setFitWidth(23);
        
        car2.setX(x+68);
        car2.setY(y);
        car2.setFitHeight(50);
        car2.setFitWidth(23);
        
        this.getChildren().addAll(road, side, nana, car1, car2);
    }
    
    public void hideNana()
    {
        this.getChildren().clear();
        if(!specialLane)
        {
            this.getChildren().addAll(road, side, car1, car2);
        }
        else
        {
            this.getChildren().addAll(end);
        }
    }
    
    public void showNana()
    {
        this.getChildren().clear();
        if(!specialLane)
        {
            this.getChildren().addAll(road, side, car1, car2, nana);
        }
        else
        {
            this.getChildren().addAll(end, nana);
        }
    }
    
    public void anim(ImageView car)
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5+ 0.02*(1+rNG.nextInt(3)*(2+rNG.nextInt(4)*(1+rNG.nextInt(3)))))); // duration of one move
        transition.setCycleCount(TranslateTransition.INDEFINITE); // Repeat indefinitely
        transition.setAutoReverse(true); // Make it move back and forth
        transition.setNode(car); // Apply the transition to the rectangle
        transition.setByY(332);
        transition.play();
    }
    
    public static void walk(ImageView nana)
    {
        walk.setDuration(Duration.seconds(0.45)); // duration of one move
        walk.setCycleCount(0);
        walk.setAutoReverse(false); // Make it move back and forth
        walk.setNode(nana); // Apply the transition to the rectangle
        walk.setByX(100);
        walk.play();
    }
}
