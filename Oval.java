 

import javax.swing.*;
import java.awt.*;

/** Oval Supplier Class 
 * Author: David D. Riley
 * Date: April, 2004
 */
public class Oval extends JComponent  {

    /** post:   getX() == x  and  getY() == y
     *          and  getWidth() == w  and getHeight() == h
     *          and  getBackground() == Color.black
     */
    public Oval(int x, int y, int w, int h)  {
        super();
        setBounds(x, y, w, h);
        setBackground(Color.black);
    }
    public void changeSize(int w,int h) {
        setBounds(getX(),getY(),w,h);
    }
    public void setColor(int r, int b, int g) {
        Color temp = new Color(r,b,g);
        setBackground(temp);
    }
    public void setCenter(int x,int y) {//fix this junk
        setBounds((int)(x-getWidth()/2.0),(int)(y-getHeight()/2.0),getWidth(),getHeight());
        //setBounds(x,y,getWidth(),getHeight());
    }
    /** post:   this method draws a filled Oval
     *          and  the upper left corner of the bounding rectangle is (getX(), getY()) 
     *          and  the oval's dimensions are getWidth() and getHeight()
     *          and  the oval's color is getBackground()
     */
    public void paint(Graphics g)  {
        g.setColor( getBackground() );
        g.fillOval(0, 0, getWidth()-1, getHeight()-1);
        paintChildren(g);
   }

}