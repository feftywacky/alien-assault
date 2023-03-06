import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Alien extends Player
{
    private static ImageIcon imgAlien;
    private int xPos, yPos, width, height, speedX, speedY, HP;

	//public static final int HP = 50;


    public Alien(JPanel gamePanel)
    {
        super(gamePanel);

        imgAlien = new ImageIcon("Alien_1 48x44.png");

        xPos = (int)(Math.random()*1280);
        yPos = -90;

        width = 48;
        height = 44;

        speedX = 0;
        speedY = 0;
		HP = 100;
    }

    public void draw(Graphics g)
    {
        g.drawImage(imgAlien.getImage(), xPos, yPos, width, height, null);
		g.setColor(Color.RED);
		g.fillRect(xPos-15, yPos+height+10, 100, 5);
		g.setColor(Color.GREEN);
        if (HP>=0)
		      g.fillRect(xPos-15, yPos+height+10, HP, 5);
	}

	    public void stopBorder()
    {
        if(yPos < 0)
        {
            speedY = 0;
            yPos = 0;
        }
	}
	public void setHP(int k)
	{
		HP = HP-k;
	}

	public int getHP()
	{
		return HP;
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

    public boolean onRight(int k)
    {
        if (xPos > k)
            return true;
        else
            return false;
    }
    public boolean onLeft(int k)
    {
        if (xPos < k)
            return true;
        else
            return false;
    }
    public boolean above(int k)
    {
        if (yPos < k)
            return true;
        else return false;
    }
    public boolean below()
    {
        if (yPos > super.yPos)
            return true;
        else return false;
    }

    public void move_up()
    {
        yPos-=speedY;
    }

    public void move_down()
    {
        yPos+=speedY;
    }

    public void move_right()
    {
        xPos+=speedX;
    }

    public void move_left()
    {
        xPos-=speedX;
    }

    public void tp_right()
    {
        xPos+=90;
    }

    public void tp_left()
    {
        xPos-=90;
    }

    public void stop()
    {
        xPos = xPos;
        yPos = yPos;
    }

    public void random_speed()
    {
        speedX = (int)(Math.random() * 2)+1;
        speedY = (int)(Math.random() * 2)+1;
    }


}// END OF CLASS
