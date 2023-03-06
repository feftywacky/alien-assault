import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player
{
    private static ImageIcon imgPlayer;
    private int width, height, HP;
    public int speedX, speedY, boost;
    public int xPos, yPos, xPos_right;
	//public static final int HP = 100;

    public Player(JPanel gamePanel)
    {
        imgPlayer = new ImageIcon("Player Spaceship 230x256.png");

        // starting postition of player
        xPos = (int)(Math.random()*1280);
        yPos = 550;

        width = 56;
        height = 64;

        speedX = 0;
        speedY = 0;
		HP = 100;
    }

    public void draw(Graphics g)
    {
        g.drawImage(imgPlayer.getImage(), xPos, yPos, width, height, null);
		g.setColor(Color.RED);
		g.fillRect(xPos-20, yPos+height+10, 100, 5);
		g.setColor(Color.GREEN);
        if (HP>=0)
		      g.fillRect(xPos-20, yPos+height+10, HP, 5);

    }

    public void move()
    {
        xPos += speedX;
        yPos += speedY;
    }

	public void setSpeed(int k)
	{
		boost = k;

	}

	public int getboost()
	{
		return boost;
	}


	public void setHP(int k)
	{
		HP = HP-k;
	}

	public int getHP()
	{
		return HP;
	}

    public void up()
    {
        speedY = -4 - boost;
        speedX = 0;
    }

    public void left()
    {
        speedY = 0;
        speedX = -4 - boost;
    }

    public void down()
    {
        speedY = 4 + boost;
        speedX = 0;
    }

    public void right()
    {
        speedY = 0;
        speedX = 4 + boost;
    }

    public void stopY()
    {
        speedY = 0;
    }

    public void stopX()
    {
        speedX = 0;
    }

	//player is stopped when they reach the ends of the border by setting velocities to 0
    public void stopBorder()
    {
        if(yPos < 0)
        {
            speedY = 0;
            yPos = 0;
        }

		if(xPos<0)
        {
            speedX = 0;
            xPos = 0;
        }

		//graphically readjusted to stay in the border
        if(yPos + height > GameTester.gamePanel.getHeight())
        {
            speedY = 0;
            yPos = GameTester.gamePanel.getHeight() - height;
        }

		if(xPos + width > GameTester.gamePanel.getWidth())
        {
            speedX = 0;
            xPos = GameTester.gamePanel.getWidth() - width;
        }


    }

	public void touchesAlien(int a, int b, int c)
	{
		//Whenever the player intersects with the bottom of the alien he will be sent one unit down and speed wil be 0
		if (yPos >= a)
		{
			yPos = a + 1;
			speedY = 0;
		}

		else if(xPos > b)
		{
			//player is on right
			xPos = c + 1;
			speedX = 0;
		}

		else if(xPos + width >= b)
        {
		//player is on left
        System.out.println("asd");
		xPos = b - width-1;
		speedX = 0;
        }

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

    public void reset()
    {
		//when new game is started we want all the players stats to reset
        speedX = 0;
        speedY = 0;
        xPos = (int)(Math.random()*1280);
        HP = 100;
		boost =0;
    }
}
