import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.DefaultButtonModel;

public class Menu extends JPanel implements ActionListener
{
    private JButton btnStart1, btnExit, btnInstructions;
    private Font font;
    public static boolean start_pressed;
    private static ImageIcon imgPlay1, imgPlay2, imgIntruct1, imgInctruct2, imgExit1, imgExit2;
    private static ImageIcon bckg;
    //public static Timer myTimer;

    public Menu()
    {
		//intialize the variabls
        imgPlay1 = new ImageIcon(new ImageIcon("play_button1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgPlay2 = new ImageIcon(new ImageIcon("play_button2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgIntruct1 = new ImageIcon(new ImageIcon("info_button1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgInctruct2 = new ImageIcon(new ImageIcon("info_button2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgExit1 = new ImageIcon(new ImageIcon("exit_button1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgExit2 = new ImageIcon(new ImageIcon("exit_button2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        //imgPlay2 = new ImageIcon(new ImageIcon("play_button2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        bckg = new ImageIcon("bg.png");
		//use booleean start pressed for error handling with the timer starting in menu (too early)
        start_pressed = false;
        btnStart1 = new JButton(imgPlay1);
		btnInstructions = new JButton(imgIntruct1);
        btnExit = new JButton(imgExit1);

        //this.setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0,200)));

        //add(Box.createRigidArea(new Dimension(0, 50)));

        // editing visual effects of start button
        btnStart1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStart1.setBackground(Color.BLACK);
        btnStart1.setOpaque(false);
        btnStart1.setBorderPainted(false); // removes outer border on button
        btnStart1.setFocusPainted(false); // removes border on button
        btnStart1.setRolloverEnabled(false); // removes the mouse over effect on button
        btnStart1.setModel(new FixedStateButtonModel()); // remoes mouse click effect on button
        btnStart1.setMaximumSize(new Dimension(100,100));
        //btnStart1.setFont(new Font("Monospaced", Font.PLAIN, 36));
        btnStart1.setRolloverIcon(imgPlay2);
        add(btnStart1);
        add(Box.createRigidArea(new Dimension(0, 20)));
        btnStart1.addActionListener(this);

		//adjusting the size, visual appeal and alignment of instrucction button
        btnInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInstructions.setBackground(Color.BLACK);
        btnInstructions.setOpaque(false);
        btnInstructions.setBorderPainted(false); // removes outer border on button
        btnInstructions.setFocusPainted(false); // removes border on button
        btnInstructions.setRolloverEnabled(false); // removes the mouse over effect on button
        btnInstructions.setModel(new FixedStateButtonModel()); // remoes mouse click effect on button
        btnInstructions.setMaximumSize(new Dimension(100,100));
        //btnInstructions.setFont(new Font("Monospaced", Font.PLAIN, 36));
        btnInstructions.setRolloverIcon(imgInctruct2);
        add(btnInstructions);
        add(Box.createRigidArea(new Dimension(0, 20)));
        btnInstructions.addActionListener(this);


        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setBackground(Color.BLACK);
        btnExit.setOpaque(false);
        btnExit.setBorderPainted(false); // removes outer border on button
        btnExit.setFocusPainted(false); // removes border on button
        btnExit.setRolloverEnabled(false); // removes the mouse over effect on button
        btnExit.setModel(new FixedStateButtonModel()); // remoes mouse click effect on button
        btnExit.setMaximumSize(new Dimension(100,100));
        btnExit.setRolloverIcon(imgExit2);
        add(btnExit);
        btnExit.addActionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bckg.getImage(),0,0,getWidth(),getHeight(),null);
        g.setColor(Color.GREEN);
        g.drawString("BY: FEIYU LIN AND ARUN PARTHIPAN", 520, 620);
    }


    // this class disables the default JButton press effect
    public class FixedStateButtonModel extends DefaultButtonModel
    {
        public boolean isPressed()
        {
            return false;
        }
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==btnStart1)
        {
			//means the game started so it calls the game panel
            GameTester.cardlay.last(GameTester.cont);
            GameTester.gamePanel.requestFocus();
            start_pressed = true;
        }

		else if(e.getSource() == btnInstructions)
		{
			//pulls up instruction pannel
			 GameTester.cardlay.next(GameTester.cont);
             GameTester.instructionsPanel.requestFocus();
		}
		//The program will close if exit is choosen
        else if (e.getSource()==btnExit)
            System.exit(0);
    }
}
