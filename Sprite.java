import java.util.Random;
import java.awt.Color;
import java.lang.Math;
/**
 * @Anthony Corbin
 * Neumont University
 * Class: Intro to Computer Sciences A
 * Quarter: 1
 */
public class Sprite extends Oval {
	private final double GRAV = .3867;//higher gravity will cause GRAVITY balls to accelerate to the bottom of the screen faster
	private double accX=0;
	private double accY=0;
	private int worldX = SpriteWorld.world_x;
	private int worldY = SpriteWorld.world_y;
	private int mass = 0;//holds the mass of the ball which at all times should be the width*height
	private double widthHeight=0;//hold the square root of mass, so Math.sqrt(mass) doesn't have to be called each time
	SpriteType spriteType = SpriteType.STRAIGHT;
	
	public Sprite (int x, int y, int mass, SpriteType spriteType) {
		super(x,y,(int)Math.sqrt(mass),(int)Math.sqrt(mass));
		this.mass = mass;
		this.widthHeight = Math.sqrt(mass);
		this.spriteType = spriteType;
		errorAccelerationPatch();//starts acceleration
	}
	/**
	 * the overloaded function of random that will give a range between 5 and 25
	 */
	int random() {
		Random rand = new Random();
		return rand.nextInt(20)+5;
	}
	/**
	 * returns a random value from 0 through given max
	 */
	int random(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}
	/**
	 * find the absoulte value of a double to return a double
	 */
	double abs(double num) {
		if (num<0) { return -num; }
		return num;
	}
	/**
	 * rounds a doulbe into an int
	 */
	int roundInt(double num) {
		num = (int)(num*10);
		if ( num-((int)(num/10)*10.0)>=5 ) { return (int)((num/10)+1); }
		return (int)(num/10);
	}
	/**
	 * Slowly averages the width and height of the ball.
	 * It will never reach a 1:1 ratio again, but it will quickly spring from being very wide/long and then slow
	 */
	void roundTheBall() {
		this.changeSize( (int)(this.getWidth()+(this.widthHeight-this.getWidth())/8) , (int)(this.getHeight()+(this.widthHeight-this.getHeight())/8) );
	}
	/**
	 * returns the current position of the ball relitive to the center
	 */
	Coordinate getCenter() {
		return new Coordinate(getX()+getWidth()/2,getY()+getHeight()/2);
	}
	/**
	 * changes the height and width of the circle based off the acceleration, while keeping the Width*Height=Mass ratio
	 */
	void bounce(boolean changeX) {
		if (changeX) {//to seperate updaing the x and y
			double a = abs(accX/3);//update size based off acceleration
			this.changeSize(roundInt(this.widthHeight/a),roundInt(a*this.widthHeight));
		} else {
			double a=abs(accY/3);//update size based off acceleration
			this.changeSize(roundInt(a*this.widthHeight),roundInt(this.widthHeight/a));
		}
	}
    
	/**
	 * patch to make sure that a ball won't get stuck.
	 * if the acceleration of x or y is between -1 and 1 the ball will not move because it holds it's position as an int
	 */
	void errorAccelerationPatch() {
		while(-1 < accX && accX < 1) { accX = random()-random(); }
		while(-1 < accY && accY < 1) { accY = random()-random(); }
	}
	/**
	 * Finaly check to make sure that a ball (post squash) will not exceed the bounds, should be called as a FINAL check
	 */
	void errorEdgePatch() {
		/*
		 * the acceleration patch is placed before the rest
		 * because the acceleration produced might not be compatable with the current loction of the ball
		 * so if the acceleration is positive when it should be negitive the following with fix this
		 */
		errorAccelerationPatch();
		
		//check X bounds
		if (getX()+getWidth() >= worldX) {//if ball crosses the right wall
			accX = -abs(accX);            //acceleration should be negitive
			this.setCenter(worldX-getWidth()/2,getCenter().y);
		} else if(getX()+accX<=0) {//if the ball cross the left wall
			accX = abs(accX);     //acceleration should be positive
			this.setLocation(0,getY());
		}
		//check  Y bounds
		if (getY()+getHeight() >= worldY) {//if ball extends below the screen
			accY = -abs(accY);             //acceleration should be negitive
			this.setLocation(getX(),worldY-getHeight());
		} else if(getY()+accY<=0) {//if ball extends above the screen
			accY = -abs(accY);     //acceleration should be positive
			this.setLocation(getX(),0);
		}
	}
	void act() {
		//MaxPreportion is the max preportion that can be between the width and height
		double ballMaxPreportion = 20;//should never be below 1
		if( getX()+getWidth()+accX >= worldX ) {//if over right
			double difference = abs( abs(getX()+getWidth()+accX) - worldX );//the lenght that the sprite will exceed past the bounds
			int w = roundInt(getWidth()-2*difference);                      //the new width it would have to compress to fit on the screen
			int h = roundInt(this.mass/(getWidth()-2*difference));          //the required height to go with the width to maintain the preportion and fit on the screen
			//this is to make sure nothing gets divided by 0
			if(w==0) w=1;   if(h==0) h=1;
			
			if (h/w<=ballMaxPreportion || getCenter().x>=worldX) {//ball is too squashed or center is over bounds
				this.setColor ( random(256),random(256),random(256) );
				
				bounce(true);
				
				accX = -random();
				this.setCenter(worldX-getWidth()/2,getCenter().y);//update to Right side minus width
			} else {//compress ball to edge
				this.changeSize(w,h);
				this.setCenter(worldX-getWidth(),getCenter().y);//update to Right side minus width
			}
		} else if(getX()+accX<=0) {//if next move is over left
			double difference = abs(abs(getX()+accX)-0);
			int w = roundInt(getWidth()-2*difference);
			int h = roundInt(this.mass/(getWidth()-2*difference));
			if(w==0) w=1;   if(h==0) h=1;
			if(h/w<=ballMaxPreportion || getCenter().x<=0) {//if ball is too squashed or center is out of bounds
				this.setColor ( random(256),random(256),random(256) );
				
				bounce(false);
				
				accX = random();
				this.setCenter(getWidth()/2,getCenter().y);//update to left side plus width
			} else {//compress and set to wall
				this.changeSize(w,h);
				this.setCenter(getWidth()/2,getCenter().y);//setting new location
			}
		} else {
			this.setCenter(roundInt(getCenter().x+accX),getCenter().y);
			roundTheBall();
		}
		
		
		
		
		//update y center
		if( getY()+getHeight()+accY >= worldY ) {//hits bottom
			double difference = abs( abs(getX()+getWidth()+accY) - worldX );//how much the ball extends past border
			int w = roundInt(this.mass/(getWidth()-2*difference));
			int h = roundInt(getWidth()-2*difference);
			if(w==0) w=1;   if(h==0) h=1;
			if (w/h<=ballMaxPreportion || getCenter().y>=worldY) {//ball is too squashed or center is over bounds
				this.setColor ( random(256),random(256),random(256) );
				
				bounce(false);
				
				accY = -random();
				this.setCenter(getCenter().x-getWidth()/2,worldY-getHeight()/2);//update to bottom minus height
			} else {//compress and set to wall
				this.changeSize(w,h);
				this.setCenter(getCenter().x,worldY);
			}
			
		} else if(getY()+accY<=0) {
			double difference = abs( abs(getY()+accY) - 0 );
			int w = roundInt(this.mass/(getWidth()-2*difference));
			int h = roundInt(getWidth()-2*difference);
			if(w==0) w=1;   if(h==0) h=1;
			if (w/h<=ballMaxPreportion || getCenter().y>=worldY) {//ball is too squashed or center is over bounds
				this.setColor ( random(256),random(256),random(256) );
				
				bounce(false);
				
				accY = random();
				this.setCenter(getCenter().x,getCenter().y);//update to top plus height
			} else {//compress and set to wall
				this.changeSize(w,h);
				if(spriteType==SpriteType.GRAVITY) { accY+=GRAV; }//so we don't defy gravity just because it starts to hit a wall
				this.setCenter(getCenter().x,0+getCenter().y);//set to wall
			}
		} else {//no wall hit/no compression basic movement
			switch (spriteType) {
				case GRAVITY:
					accY+=GRAV;
				case STRAIGHT:
					this.setCenter(getCenter().x,roundInt(getCenter().y+accY));
					break;
			}
			roundTheBall();
		}
		
		
		errorEdgePatch();//after resizing ball and movements this is to prevent glitch were balls had gotten stuck in lower right
	}
}
