import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Heart
{
    public static ImageIcon powerup;
    protected int xPos, yPos, width, height, healDamage;

    public Heart()
    {
        powerup = new ImageIcon("heart.png");

        xPos = (int)(Math.random()*1200);
        yPos = (int)(Math.random()*620);

        width = 30;
        height = 30;
        healDamage = -25;
    }

    public void draw(Graphics g)
    {
        g.drawImage(powerup.getImage(), xPos, yPos, width, height, null);
	}

	//getters and setters
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
	//ends here
	
	public int heal(int p)
	{
		//if the player has full hp the heart will have no effect
		if (p == 100)
		{
			return 0;
		}
		
		//if they can be healed less than the heart provides we will find the exact difference
		//value is negative since our player.setHP subtracts the int value we pass in
		else if( p > 75 && p < 100)
		{
			return (100 - p)*-1 ;
			
		}
		
		//if they are less than 75 they get the full 25 heal
		else
		{
			
		return healDamage;
		}
	}


}// END OF CLASS
