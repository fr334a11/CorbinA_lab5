import java.awt.Color;
public class SpriteWorld extends ButtonWindow
{
    public static final int world_x = 700;
    public static final int world_y = 500;
    private ButtonWindowFrame win;
    private Sprite sprite1;
    public SpriteWorld()
    {
        win = new ButtonWindowFrame("Bouncing Ball");
        win.addAnimateButton(775,130,75,25);
        Rectangle spriteContainer = new Rectangle(30,30,world_x,world_y);
        spriteContainer.setBackground(Color.blue);
        win.add(spriteContainer,0);
        sprite1 = new Sprite(150, 400);
        spriteContainer.add(sprite1,0);
    }
   
    public void act()
    {
        sprite1.act();
    }
    
    public static void main(String[]args)
    {
        SpriteWorld theWorld = new SpriteWorld();
    }

}