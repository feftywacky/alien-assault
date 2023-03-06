import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Alien_bullet_2 extends Bullet
{
    // VARIABLES

    // CONTRUCTOR
    public Alien_bullet_2(int x ,int y, int bullet_wdith, int bullet_height, int speed, int damage, boolean collision)
    {
        super(x,y,bullet_wdith,bullet_height,speed,collision);
        this.damage = damage;
    }


    // METHODS
    public void draw(Graphics g)
	{
		g.fillOval(xPos, yPos, bullet_wdith, bullet_height);
	}


}// END OF CLASS
