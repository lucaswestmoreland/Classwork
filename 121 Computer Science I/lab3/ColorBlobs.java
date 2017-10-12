import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Partial class for lab exercise using Random, Math and Color classes to draw
 * random color blobs.
 * 
 * @author mvail, amit
 */

public class ColorBlobs extends JPanel
{
	// The variables below  are instance variables, which are visible and usable from all the 
	// methods in this class. They represent the state of the object of this class.
	
	private final int TIMER_DELAY = 500; //milliseconds
	private Random rand;

	//Note: no other instance variables are necessary for this project

	/**
	 * Initialize the ColorBlobs class.
	 */
	public ColorBlobs()
	{
		// Note: no modifications to this method are necessary for this project
		rand = new Random();
		setBackground(Color.black);
		startAnimation();
		setPreferredSize(new Dimension(600, 600));
	}

	/**
	 * This method draws on the panel. It is called periodically by the
	 * animation thread.
	 */
	public void paintComponent(Graphics page)
	{
		int width = getWidth(); // width of the drawing panel
		int height = getHeight(); // height of the drawing panel
		
		int maxWidth = width/2;
		int minWidth = width/20;
		int widthRange = maxWidth - minWidth;
		int ovalWidth = rand.nextInt(widthRange) + minWidth;
		
		int maxHeight = height/2;
		int minHeight = height/20;
		int heightRange = maxHeight - minHeight;
		int ovalHeight = rand.nextInt(heightRange) + minHeight;
		
		int anchorXRange = width - ovalWidth;
		int anchorX = rand.nextInt(anchorXRange);
		
		int anchorYRange = height - ovalHeight;
		int anchorY = rand.nextInt(anchorYRange);
		
		int red = rand.nextInt(256);
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		Color myColor = new Color(red, green, blue);
		
		
		page.setColor(myColor);
		page.fillOval(anchorX, anchorY, ovalWidth, ovalHeight);
				
				
				
		

		// TODO: Now you fill in the rest

	}


	/**
	 * Create an animation thread that runs periodically. DO NOT MODIFY
	 */
	private void startAnimation()
	{
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				repaint(); // redraw the panel
			}
		};
		new Timer(TIMER_DELAY, taskPerformer).start();
	}

	/**
	 * The main method that starts up the application. DO NOT MODIFY.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Color Blobs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ColorBlobs());
		frame.pack();
		frame.setVisible(true);
	}
}