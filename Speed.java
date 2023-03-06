import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Speed
{
    public static ImageIcon powerup;
    protected int xPos, yPos, width, height, giveSpeed;

    public Speed()
    {
        powerup = new ImageIcon("speed2.png");

        xPos = (int)(Math.random()*1200);
        yPos = (int)(Math.random()*620);

        width = 22;
        height = 30;
        giveSpeed = 2;
    }

    public void draw(Graphics g)
    {
        g.drawImage(powerup.getImage(), xPos, yPos, width, height, null);
	}

	

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	} 
	
	//we alter the boost value which can be seen in the player class
	public int addBoost()
	{
		return giveSpeed;
	}

}// END OF CLASS
