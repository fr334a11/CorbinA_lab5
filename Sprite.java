package testing;

import java.util.Random;
/**
 * @Anthony Corbin
 * Neumont University
 * Class: Intro to Computer Sciences [section]
 * Quarter: 1
 * [Date]
 * [Assignment Name]
 * Description of the class: this class does stuff
 */
public class Sprite extends Oval {
    private int speed = 4;
    private int world_x = 700;
    private int world_y = 500;
    private int accX = speed;
    private int accY = speed;
    public Sprite (int x, int y) {
        super(x,y,10,10);
    }
    int random() {
        Random rand = new Random();
        return rand.nextInt(20)+5;
    }
    void act() {
        if ( getX()+getWidth() >=world_x )  { accX = -random(); } else if (getX()<=0 ) { accX = random(); }
        if ( getY()+getHeight()>=world_y )  { accY = -random(); } else if (getY()<=0 ) { accY = random(); }
        
        while(accX == accY && accY == 0) {//so the ball doesn't halt 100%
            accX = random();
            accY = random();
        }
        this.setLocation(getX()+accX,getY()+accY);//update the ball's location after initial calcuation
        //the following is to check to make sure the ball is not out of bounds
        if ( getX()+getWidth()>=world_x )  {
            this.setLocation(world_x-getWidth(),getY());
        } else if (getX()<=0 ) {
            this.setLocation(0,getY());
        }
        if ( getY()+getHeight()>=world_y )  {
            this.setLocation(getX(),world_y-getHeight());
        } else if (getY()<=0 ) {
            this.setLocation(getX(),0);
        }
    }
}
