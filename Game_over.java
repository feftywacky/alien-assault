import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.DefaultButtonModel;

public class Game_over extends JPanel implements ActionListener
{
    private JButton goToNext;
    private JLabel title;
    private Font font;
    public static boolean start_pressed;
    private static ImageIcon imgCont1, imgCont2;

    public Game_over()
    {
        this.setBackground(Color.BLACK);

        imgCont1 = new ImageIcon(new ImageIcon("cont1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imgCont2 = new ImageIcon(new ImageIcon("cont2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        goToNext = new JButton(imgCont1);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0,100)));
		title = new JLabel("GAME OVER");
        font = new Font("Monospaced", Font.PLAIN, 80);
        // set sizes
        title.setMaximumSize(new Dimension(800,100));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(font);
        title.setForeground(Color.RED);
        add(title);
        add(Box.createRigidArea(new Dimension(0, 75)));


        //goToNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToNext.setBackground(Color.BLACK);
        goToNext.setOpaque(false);
        goToNext.setBorderPainted(false); // removes outer border on button
        goToNext.setFocusPainted(false); // removes border on button
        goToNext.setRolloverEnabled(false); // removes the mouse over effect on button
        goToNext.setMaximumSize(new Dimension(100,100));
        goToNext.setRolloverIcon(imgCont2);
        add(goToNext);
        goToNext.addActionListener(this);
	}// END OF CONTRUCTOR

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==goToNext)
        {
            GameTester.cardlay.first(GameTester.cont);
            GameTester.gamePanel.requestFocus();
        }
    }

}// END OF CLASS
