import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener
{
    // VARIABLES
    private Timer myTimer, heart_timer, speed_timer, speedDuration;
    private Player player;
	private ArrayList<Bullet> ammoPlayer;
	private ArrayList<Alien_bullet_1> ammoAlien1;
    private ArrayList<Alien_bullet_2> ammoAlien2, ammoAlien3;
    private ArrayList<Alien> alien;
    private ArrayList<Boss> boss;
    private ArrayList<Heart> heart;
    private ArrayList<Speed> speed;
	private int frameCount, frameCount2, frameCount3, frameCount4, frameCount5, frameCount6, frameCount7, frameCount8, speedcheck;
    private int alien_distance, boss_distance, random_temp, random_temp2;
    private boolean middle;
    public static boolean reset, spawn_heart, spawn_speed, heart_collect, speed_collect;
    public int temp;

    // CONTRUCTOR
    public Game()
    {
        player = new Player(GameTester.gamePanel);

        alien = new ArrayList<Alien>();
        boss = new ArrayList<Boss>();
        heart = new ArrayList<Heart>();
        speed = new ArrayList<Speed>();

        //Add first alien
        alien.add(new Alien(GameTester.gamePanel));

        this.setBackground(Color.BLACK);

        addKeyListener(this);
		addMouseListener(this);
        setFocusable(true);

		ammoPlayer = new ArrayList<Bullet>();
		ammoAlien1 = new ArrayList<Alien_bullet_1>();
        ammoAlien2 = new ArrayList<Alien_bullet_2>();
        ammoAlien3 = new ArrayList<Alien_bullet_2>();

        alien_distance = 0;
        boss_distance = 0;
        frameCount = 0;
        frameCount2 = 0;
        frameCount3 = 0;
        frameCount4 = 0;
        frameCount5 = 0;
        frameCount6 = 0;
        frameCount7 = 0;
        frameCount8 = 0;
        random_temp = 0;
        random_temp2 = 0;
        temp = 0;
        middle = false;
        reset = false;
        spawn_heart = false;
        spawn_speed = false;
        heart_collect = false;
        speed_collect = false;
        heart_timer = new Timer(5000,this);
        speed_timer = new Timer(5000,this);
		speedDuration = new Timer(6000,this);
        myTimer = new Timer(16,this); // new frame every 16 milliseconds (62.5fps)
        myTimer.start();
    } // End of contructor

    //METHODS


	//check if the user has pressed any keys to decide on how they should move
	//verrify the gaame started first to ensure they can't move in the menu
    public void keyPressed(KeyEvent e)
    {
        if(Menu.start_pressed)
        {
            if(e.getKeyText(e.getKeyCode()).equals("W"))
            {
                player.up();

            }
            if(e.getKeyText(e.getKeyCode()).equals("A"))
            {
                player.left();
            }
            if(e.getKeyText(e.getKeyCode()).equals("S"))
            {
                player.down();
            }
            if(e.getKeyText(e.getKeyCode()).equals("D"))
            {
                player.right();
            }
        }
    }

	//When the player releases the movement key we will set their vertical and horizontal velocities to 0 so they will stop moving
    public void keyReleased(KeyEvent e)
    {
        if(Menu.start_pressed)
        {
            if(e.getKeyText(e.getKeyCode()).equals("W") || e.getKeyText(e.getKeyCode()).equals("S"))
    		{
                player.stopY();
    		}
            else if(e.getKeyText(e.getKeyCode()).equals("A") || e.getKeyText(e.getKeyCode()).equals("D"))
    		{
                player.stopX();
    		}
        }
    }

    public void keyTyped(KeyEvent e)
    {

    }

	//When the user click left click we will allow them to shoot
	//We do this by adding a new bullet to the ammo of the player

	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
            // The bullet will start wherever the player is at the moment of the shot
    		ammoPlayer.add(new Bullet(player.getX() + 22, player.getY() + 22,10,7,5,false));
		}

	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {

    }

    public void actionPerformed(ActionEvent e)
    {
        heart_timer.start();
        speed_timer.start();
        speedDuration.start();
		//We will delete the heart spawns every 5 seconds
        if(e.getSource()==heart_timer && Menu.start_pressed && heart_collect)
        {
            if(heart.size()>0)
                heart.remove(0);
        }

		//We will delete the speed powerups every 5 seconds
        if(e.getSource()==speed_timer && Menu.start_pressed && speed_collect)
        {
            if(speed.size()>0)
                speed.remove(0);
        }

		//reset the players speed effect to the default
		if(e.getSource()==speedDuration && Menu.start_pressed)
        {
			player.setSpeed(0);
		}



        if (e.getSource()==myTimer && Menu.start_pressed)
        {
            //if the player is out of HP they are now dead so we show the game over sign
            if(player.getHP()<=0)
            {
                reset = true;
                reset_game();
                GameTester.cardlay.show(GameTester.cont, "GAME OVER");
                GameTester.gameOverPanel.requestFocus();
            }





            //spawn Powerups every 2 seconds (apx)
			//There is a 50% chance it will spawn as speed boost or heart
            if(frameCount6++ >= 2000/16.0)
			{
                temp = (int)(Math.random()*2)+1;
                if(temp==1)
                    heart.add(new Heart());
                else
                    speed.add(new Speed());

				frameCount6 = 0;
			}


            //add alien every 3 seconds
            if(frameCount++ >= 3000/16.0)
			{
				alien.add(new Alien(GameTester.gamePanel));
                //System.out.println("added new alien");
				frameCount = 0;
			}

			//After a 15 seconds we will spawn the boss
            if(frameCount3++ >= 15000/16.0)
			{
				boss.add(new Boss(GameTester.gamePanel));
                //System.out.println("added new alien");
				frameCount3 = 0;
			}

            // Add a new bullet to the alien's ammo every second
			if(frameCount2++ >= 1000/16.0)
			{
                // Add some bullets to the alien's ammo
                for (int i=0; i<alien.size(); i++)
                {
        		    ammoAlien1.add(new Alien_bullet_1(alien.get(i).getX() + 20, alien.get(i).getY() + 20,4,16,6, false));
        		    //System.out.println("Added new bullet");
    				frameCount2 = 0;
                }
			}


            // Boss side bullets
            if(frameCount4++ >= 500/16.0)
			{
                // Add some bullets to the boss's ammo
                for (int i=0; i<boss.size(); i++)
                {
        		    ammoAlien2.add(new Alien_bullet_2(boss.get(i).getX() + 20, boss.get(i).getY() + 159,2,18,8, 10,false));
                    ammoAlien2.add(new Alien_bullet_2(boss.get(i).getX() + boss.get(i).getWidth()-20, boss.get(i).getY() + 159,2,18,8, 10,false));
        		    //System.out.println("Added new boss bullet");
    				frameCount4 = 0;
                }
			}

            // Boss main bullets
            if(frameCount5++ >= 1000/16.0)
			{
                // Add some bullets to the alien's ammo
                for (int i=0; i<boss.size(); i++)
                {
                    //System.out.println("Added new boss bullet");
        		    ammoAlien3.add(new Alien_bullet_2(boss.get(i).getX() + boss.get(i).getWidth()/2-10, boss.get(i).getY()+boss.get(i).getHeight()-10,22,22,4,50,false));
    				frameCount5 = 0;
                }
			}


			// Move the player
            player.move();

            alien_distance = (int)(Math.random() * 160)+50;
            boss_distance = (int)(Math.random() * 200)+50;
            random_temp = (int)(Math.random() * 5)+1;
            random_temp2 = (int)(Math.random() * (90-50+1))+50;

            // alien movement
            for (int i=0; i<alien.size(); i++)
            {

                alien.get(i).random_speed();

				//The aliens movement is designed to follow the player keeping a random distance between them

                if (alien.get(i).onRight(player.getX()))
                {
                    alien.get(i).move_left();
                }
    			else if (alien.get(i).onLeft(player.getX()))
                {
    				alien.get(i).move_right();
                }
                else
                {
                    if (random_temp==1)
                        alien.get(i).tp_left();
                    else if (random_temp==2)
                        alien.get(i).tp_right();
                    else
                        alien.get(i).stop();
                }
                if (alien.get(i).getY()+alien.get(i).getHeight() < player.getY() - alien_distance)
                {
        			alien.get(i).move_down();
                }
                else
                {
                    alien.get(i).move_up();
					alien.get(i).stopBorder();
                }
            }

			//make sure the player does not go through any alien by checking to see if there bounding boxes intersect
			for (int j = 0; j<alien.size(); j++)
			{
				    if((player.getX() < alien.get(j).getX() + alien.get(j).getWidth())
					&&(player.getX() + player.getWidth() > alien.get(j).getX())
					&&(player.getY() <alien.get(j).getY() + alien.get(j).getHeight())
					&& (player.getY() + player.getHeight() > alien.get(j).getY()))
                {
					player.touchesAlien(alien.get(j).getY() + alien.get(j).getHeight(), alien.get(j).getX(), alien.get(j).getX()+alien.get(j).getWidth());
				}
			}

            // player won't go through boss
            for (int j = 0; j<boss.size(); j++)
			{
				    if((player.getX() < boss.get(j).getX() + boss.get(j).getWidth())
					&&(player.getX() + player.getWidth() > boss.get(j).getX())
					&&(player.getY() <boss.get(j).getY() + boss.get(j).getHeight())
					&& (player.getY() + player.getHeight() > boss.get(j).getY()))
                {
					player.touchesAlien(boss.get(j).getY() + boss.get(j).getHeight(), boss.get(j).getX(), boss.get(j).getX()+boss.get(j).getWidth());
				}
			}


			//create a bounding box for the speed and player and check to see if the player has collided anywhere with the speed
			//if yes then we will add 25 hp to the player and remove the speed they hit
			for(int j = 0; j < speed.size(); j++)
			{

                if((player.getX() < speed.get(j).getX() + speed.get(j).getWidth())
					&&(player.getX() + player.getWidth() > speed.get(j).getX())
					&&(player.getY() < speed.get(j).getY() + speed.get(j).getHeight())
					&& (player.getY() + player.getHeight() > speed.get(j).getY()))
                {

					player.setSpeed(speed.get(j).addBoost());
                    speed_collect = true;
					speed.remove(j);
				}
			}

			//create a bounding box for the heart and player and check to see if the player has collided anywhere with the heart
			//if yes then we will add 25 hp to the player and remove the heart they hit
			for(int j = 0; j < heart.size(); j++)
            {
                if((player.getX() < heart.get(j).getX() + heart.get(j).getWidth())
					&&(player.getX() + player.getWidth() > heart.get(j).getX())
					&&(player.getY() < heart.get(j).getY() + heart.get(j).getHeight())
					&& (player.getY() + player.getHeight() > heart.get(j).getY()))
                {

                    player.setHP(heart.get(j).heal(player.getHP()));
                    heart_collect = true;
                    heart.remove(j);
                }
            }

            // boss movement. similar to the aliens he will follow the player
            for (int i=0; i<boss.size(); i++)
            {
                boss.get(i).random_speed();

                if (boss.get(i).onRight(player.getX()))
                {
                    boss.get(i).move_left();
                }
    			else if (boss.get(i).onLeft(player.getX()))
                {
    				boss.get(i).move_right();
                }
                else
                {
                    if (random_temp==1)
                        boss.get(i).tp_left();
                    else if (random_temp==2)
                        boss.get(i).tp_right();
                    else
                        boss.get(i).stop();
                }
                if (boss.get(i).getY()+boss.get(i).getHeight() < player.getY() - boss_distance)
                {
        			boss.get(i).move_down();
                }
                else
                {
                    boss.get(i).move_up();
					boss.get(i).stopBorder();
                }
            }



			// Make sure they don't go outside the border
            player.stopBorder();


            // This for loop checks for player bullet colliison on aliens
			//we do this by seeing if the bullet is anywhere within the aliens hitbox
			for(int i = 0; i < ammoPlayer.size(); i++)
			{
                for (int j=0; j<alien.size(); j++)
                {
					if((ammoPlayer.get(i).getX() > alien.get(j).getX())
					&& (ammoPlayer.get(i).getX() < alien.get(j).getX() + alien.get(j).getWidth())
					&& (ammoPlayer.get(i).getY() > alien.get(j).getY())
					&& (ammoPlayer.get(i).getY() < alien.get(j).getY() + alien.get(j).getHeight())
					&& (ammoPlayer.get(i).checkCollision() == false))
					{
						ammoPlayer.get(i).setCollision(true);
						alien.get(j).setHP(ammoPlayer.get(i).getDamage());
						//System.out.println("Collision!!!!!!!!!");
						ammoPlayer.remove(i);
                        i--;

                        if (alien.get(j).getHP()<=0)
                        {
                            alien.remove(j);
                            j--;
                        }
                        break;
					}
                }
            }

            // player bullet collision on boss
            for(int i = 0; i < ammoPlayer.size(); i++)
            {
                for (int k=0; k<boss.size(); k++)
                {
                    if((ammoPlayer.get(i).getX() > boss.get(k).getX())
					&& (ammoPlayer.get(i).getX() < boss.get(k).getX() + boss.get(k).getWidth())
					&& (ammoPlayer.get(i).getY() > boss.get(k).getY())
					&& (ammoPlayer.get(i).getY() < boss.get(k).getY() + boss.get(k).getHeight())
					&& (ammoPlayer.get(i).checkCollision() == false))
					{
						ammoPlayer.get(i).setCollision(true);
						boss.get(k).setHP(ammoPlayer.get(i).getDamage());
						//System.out.println("Collision!!!!!!!!!");
						ammoPlayer.remove(i);
                        i--;

                        if (boss.get(k).getHP()<=0)
                        {

							System.out.println("You win!!!!!");
							GameTester.cardlay.show(GameTester.cont, "WIN");
							GameTester.winPanel.requestFocus();
                            boss.remove(k);
							reset_game();
                            k--;
                        }
                        break;
                    }
                }

			}


            // This for loop checks if the bullet has collided with the top of the pannel
			for(int i = 0; i < ammoPlayer.size(); i++)
			{
				// Check if bullet reaches the top of the panel and remove it
				if(ammoPlayer.get(i).getY()<=0)
				{
					ammoPlayer.remove(i);

				}
				// Otherwise keep moving the bullet up by its speed amount
				else
				{
					ammoPlayer.get(i).travel(Bullet.DIR_UP);
				}


			}

            // alien bullet collision on player
			for(int j = 0; j < ammoAlien1.size(); j++)
			{
				if((ammoAlien1.get(j).getX() > player.getX())
				&& (ammoAlien1.get(j).getX() < player.getX() + player.getWidth())
				&& (ammoAlien1.get(j).getY() > player.getY())
				&& (ammoAlien1.get(j).getY() < player.getY() + player.getHeight())
				&& (ammoAlien1.get(j).checkCollision() == false))
				{
					ammoAlien1.get(j).setCollision(true);
					player.setHP(ammoAlien1.get(j).getDamage());
					//System.out.println("PLAYER GOT HIT");
					ammoAlien1.remove(j);
				}
			}

            // The next two loops control all of the boss' different bullets collision on the player
            for(int j = 0; j < ammoAlien2.size(); j++)
			{
				if((ammoAlien2.get(j).getX() > player.getX())
				&& (ammoAlien2.get(j).getX() < player.getX() + player.getWidth())
				&& (ammoAlien2.get(j).getY() > player.getY())
				&& (ammoAlien2.get(j).getY() < player.getY() + player.getHeight())
				&& (ammoAlien2.get(j).checkCollision() == false))
				{
					ammoAlien2.get(j).setCollision(true);
					player.setHP(ammoAlien2.get(j).getDamage());
					//System.out.println("PLAYER GOT HIT");
					ammoAlien2.remove(j);
				}
			}

            for(int j = 0; j < ammoAlien3.size(); j++)
			{
				if((ammoAlien3.get(j).getX() > player.getX())
				&& (ammoAlien3.get(j).getX() < player.getX() + player.getWidth())
				&& (ammoAlien3.get(j).getY() > player.getY())
				&& (ammoAlien3.get(j).getY() < player.getY() + player.getHeight())
				&& (ammoAlien3.get(j).checkCollision() == false))
				{
					ammoAlien3.get(j).setCollision(true);
					player.setHP(ammoAlien3.get(j).getDamage());
					//System.out.println("PLAYER GOT HIT");
					ammoAlien3.remove(j);
				}
			}

			// MOVE bulllets
			for(int j = 0; j < ammoAlien1.size(); j++)
			{

				// check if bullet reaches the bottom of the panel
				if(ammoAlien1.get(j).getY()>=720)
				{
					ammoAlien1.remove(j);

				}
				else
				{
					ammoAlien1.get(j).travel(Bullet.DIR_DOWN);
				}
			}

            for(int j = 0; j < ammoAlien2.size(); j++)
			{

				// check if bullet reaches the bottom of the panel
				if(ammoAlien2.get(j).getY()>=720)
				{
					ammoAlien2.remove(j);

				}
				else
				{
					ammoAlien2.get(j).travel(Bullet.DIR_DOWN);
				}
			}

            for(int j = 0; j < ammoAlien3.size(); j++)
			{

				// check if bullet reaches the bottom of the panel
				if(ammoAlien3.get(j).getY()>=720)
				{
					ammoAlien3.remove(j);

				}
				else
				{
					ammoAlien3.get(j).travel(Bullet.DIR_DOWN);
				}
			}

        }// timer if statement

		// Redraw everything
		repaint();
    }// END OF ACTION PERFORMED

    public void paintComponent(Graphics g)
    {
        myTimer.start();
        super.paintComponent(g);

        for (int i=0; i<heart.size(); i++)
        {
            heart.get(i).draw(g);
        }
        for (int i=0; i<speed.size(); i++)
        {
            speed.get(i).draw(g);
        }

        for (int i=0; i<alien.size(); i++)
        {
            alien.get(i).draw(g);
        }


        for (int i=0; i<boss.size(); i++)
        {
            boss.get(i).draw(g);
        }

        player.draw(g);

        // drawing bullets
        g.setColor(Color.RED);
        for(int j = 0; j < ammoAlien1.size(); j++)
		{
			ammoAlien1.get(j).draw(g);
		}

        g.setColor(new Color(255,0,255));
        for(int j = 0; j < ammoAlien2.size(); j++)
		{
			ammoAlien2.get(j).draw(g);
		}

        g.setColor(Color.GREEN);
        for(int j = 0; j < ammoAlien3.size(); j++)
		{
			ammoAlien3.get(j).draw(g);
		}

		g.setColor(Color.CYAN);
		for(int  i= 0; i < ammoPlayer.size(); i++)
		{
			ammoPlayer.get(i).draw(g);
		}

    }// END OF PAINTCOMPONENT


//clear all the ingame values incase the user chooses to play the game again
    public void reset_game()
    {
        myTimer.stop();

        Menu.start_pressed = false;

        heart.clear();
        speed.clear();
        ammoPlayer.clear();
        ammoAlien1.clear();
        ammoAlien2.clear();
        ammoAlien3.clear();
        alien.clear();
        boss.clear();
        player.reset();
    }

}// END OF CLASS
