import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Bullet
{
	public int xPos, yPos, speed, damage;
    public int bullet_wdith, bullet_height;
	private boolean collision;
	public static final int DIR_UP = 0, DIR_DOWN = 1;

	public Bullet(int x ,int y, int bullet_wdith, int bullet_height, int speed, boolean collision)
	{
		xPos = x;
		yPos = y;
        this.bullet_wdith = bullet_wdith;
        this.bullet_height = bullet_height;
		this.speed = speed;
		this.collision = collision;
		damage = 50;
	}

	//alien bullets travel down
	//player bullets go upward
	public void travel(int dir)
	{
		if(dir == DIR_UP)
		{
			yPos -= speed;
		}
		else
		{
			yPos += speed;
		}
	}

	
	public int getDamage()
	{
		return damage;
	}
	
	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}
	//since the bullets travle trhough the entire player model it will register as a hit multiple times
	//we use this method to error handle that logic error
	public boolean checkCollision()
	{
		return collision;
	}
	public void setCollision(boolean k)
	{
		collision = k;
	}

	public void draw(Graphics g)
	{
		//g.fillOval(xPos, yPos, 10, 7);
        g.fillOval(xPos, yPos, bullet_wdith, bullet_height);
	}
}
