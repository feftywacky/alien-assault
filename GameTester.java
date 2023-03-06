import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameTester extends JFrame
{
	public static CardLayout cardlay;
	public static Container cont;
	public static Menu menuPanel;
	public static Game gamePanel;
	public static Game_over gameOverPanel;
	public static Win winPanel;
	public static Instructions instructionsPanel;

	public GameTester()
	{
		cont = getContentPane();
		cardlay = new CardLayout();
		cont.setLayout(cardlay);
		//the panels 
		menuPanel = new Menu();
		instructionsPanel = new Instructions();
		gameOverPanel = new Game_over();
		winPanel = new Win();
		gamePanel = new Game();
		menuPanel.setOpaque(false);

		cont.add("MENU PANEL", menuPanel);
		cont.add("INSTRUCTIONS", instructionsPanel);
		cont.add("GAME OVER", gameOverPanel);
		cont.add("WIN", winPanel);
		cont.add("GAME PANEL", gamePanel);
	}

	public static void main(String [] args)
	{
		GameTester frame = new GameTester();
		frame.setSize(1280,720);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
