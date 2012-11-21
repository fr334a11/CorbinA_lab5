import java.util.Random;
import java.awt.Color;
enum SpriteType { STRAIGHT, GRAVITY}
public class SpriteWorld extends ButtonWindow
{
    public static final int world_x = 700;
    public static final int world_y = 500;
    //public static final int world_x = 1850;
    //public static final int world_y = 950;
    private ButtonWindowFrame win;
    private Sprite[] leSprites = new Sprite[10];//array of sprites
    
    public SpriteWorld() {
        win = new ButtonWindowFrame("Bouncing Ball");
        Rectangle spriteContainer = new Rectangle(30,30,world_x,world_y);
        spriteContainer.setBackground(Color.blue);
        win.add(spriteContainer,0);
        //placing sprites
        Random rand = new Random();//used to random placement of each
        for (int i=0;i<leSprites.length;i++) {
            
            leSprites[i] = new Sprite(rand.nextInt(world_x-20),rand.nextInt(world_y-20),rand.nextInt(700)+300, SpriteType.GRAVITY);
            spriteContainer.add(leSprites[i],0);
        }
        //creating buttons
        win.addAnimateButton(0,0,75,25,"Start");//creating button
        win.addActButton(100,0,100,25,"One Step");
    }
    public void act() {
        //loops through each sprite and calls act method
        for (int i=0;i<leSprites.length;i++) {
            leSprites[i].act();
        }
    }
    
    public static void main(String[]args) {
        SpriteWorld theWorld = new SpriteWorld();
    }

}