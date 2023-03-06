import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.DefaultButtonModel;

public class Instructions extends JPanel implements ActionListener
{
    private JButton back;
    private JLabel title, shoot, move;
    private Font font;
    public static boolean start_pressed;
    private static ImageIcon imgkeys, imgclick;
    //private static ImageIcon bckg;
    //public static Timer myTimer;

    public Instructions()
    {
		setLayout(null);
		this.setBackground(Color.BLACK);
		//Create jlabels
		title = new JLabel("Instructions");
		shoot=new JLabel("Use left click to shoot");
		move= new JLabel("Use WASD to move your hero");
        font = new Font("Monospaced", Font.PLAIN, 80);
		
		
        // set sizes
        title.setMaximumSize(new Dimension(800,100));
        title.setBounds(330,100,600,100);
        title.setFont(font);
        title.setForeground(Color.WHITE);
        add(title);
	//set bounds adjust the location of the labels abd pics
		
		move.setForeground(Color.WHITE);
		move.setMaximumSize(new Dimension(400,100));
		shoot.setBounds(1000,300,400,100);
		move.setBounds(20,300,400,100);
		shoot.setForeground(Color.WHITE);
		shoot.setMaximumSize(new Dimension(400,100));
		
		add(shoot);
		add(move);
        add(Box.createRigidArea(new Dimension(0, 50)));
		
		back= new JButton("Return to Menu");
		back.setBounds(0, 0,200,200 );
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE); // change text colour
		add(back);
		imgclick = new ImageIcon("imgclick.png");
		imgkeys = new ImageIcon("imgkeys.png");
		
		
		
	
	
	}
	
	
	  public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		System.out.println("img");
		 g.drawImage(imgclick.getImage(),960, 400, 250, 200, null);
        g.drawImage(imgkeys.getImage(), 20, 400, 200, 200, null);
       
	}
	
	//if the back button is hit call the menu panel
	    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==back)
        {
            GameTester.cardlay.first(GameTester.cont);
            GameTester.menuPanel.requestFocus();
		}
          
    }
}