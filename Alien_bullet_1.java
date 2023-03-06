import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Alien_bullet_1 extends Bullet
{
    // VARIABLES
   
    // CONTRUCTOR
    public Alien_bullet_1(int x ,int y, int bullet_wdith, int bullet_height, int speed, boolean collision)
    {
        super(x,y,bullet_wdith,bullet_height,speed,collision);
        damage = 25;
    }


    // METHODS
    public void draw(Graphics g)
	{
		g.fillOval(xPos, yPos, bullet_wdith, bullet_height);
	}


}// END OF CLASS
