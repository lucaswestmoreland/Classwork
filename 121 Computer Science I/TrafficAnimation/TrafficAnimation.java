/* 
 * TrafficAnimation.java 
 * CS 121 Project 1: Traffic Animation
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animates a [put your description here]
 * @author [put your name here]
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel {
	//Note: This area is where you declare constants and variables that
	//	need to keep their values between calls	to paintComponent().
	//	Any other variables should be declared locally, in the
	//	method where they are used.

	//constant to regulate the frequency of Timer events
	// Note: 100ms is 10 frames per second - you should not need
	// a faster refresh rate than this
	private final int DELAY = 10; //milliseconds
	//anchor coordinate for drawing / animating
	private int x = 0;
	//pixels added to x each time paintComponent() is called
	private int stepSize = 1;
	
	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics canvas) 
	{
		//clears the previous image
		//super.paintComponent(canvas);
		//account for changes to window size
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		//Fill the canvas with the background color
		canvas.setColor(getBackground());
		canvas.fillRect(0, 0, width, height);
		
		final int MID = width/2;
		final int TOP = height/2;
		
		canvas.setColor(Color.orange);
		canvas.fillArc(-40, -40, 100, 100, 0, 360);
		
		Color myRoad = new Color(39,46,45);
		
		
		
		canvas.setColor(myRoad);
		canvas.fillRect(0, TOP, width, height);
		
		Color myColor = new Color(193, 193, 193);
		canvas.setColor(myColor);
		
		canvas.fillRect(0, TOP+height/3, width, height);
		
		
		canvas.setColor(Color.blue);
		canvas.fillRect(MID, TOP + height/4, 20, 10);
		canvas.drawLine(MID, TOP + height/4, MID, TOP + height/3);
		canvas.setColor(Color.red);
		
		canvas.fillRect(width/4, height/4, width/8, height/4);
				
		//Calculate the new position
		x = (x + stepSize) % width;
		canvas.setFont(new Font("Serif", Font.BOLD, 36));
		canvas.setColor(Color.blue);
		canvas.drawString("It's a Bus",width/2, height/4);
	
		
    	
		//Draw new square
		//TODO: replace this square with your drawing
		int squareSide = height/5;
		int y = height/2 - squareSide/2;
		canvas.setColor(Color.yellow);
		canvas.fillRect(x, y, height/4, squareSide);
		
		int radius = Math.min(width,height)/16;
		//int midx = width/2;
		int	midy = height/2;
		
		
		canvas.setColor(Color.black);
		canvas.drawLine(x, y+height/8, x+height/4, y+height/8);
		canvas.drawLine(x, y+height/6, x+height/4, y+height/6);
		
		
		canvas.fillArc(x - radius/8, midy - radius+height/7, radius, radius, 0, 360);
		canvas.fillArc(x + radius*3, midy - radius + height/7, radius, radius, 0, 360);
		
	}

	/**
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 * This method also sets up a Timer that will call
	 * paint() with frequency specified by the DELAY
	 * constant.
	 */
	public TrafficAnimation() 
	{
		Color mySky = new Color(67, 131, 222);
		setBackground(mySky);
		//Do not initialize larger than 800x600
		int initWidth = 800;
		int initHeight = 600;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);
		
		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/////////////////////////////////////////////
	// DO NOT MODIFY main() or startAnimation()
	/////////////////////////////////////////////
	
	/**
	 * Starting point for the TrafficAnimation program
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

   /**
    * Create an animation thread that runs periodically
	* DO NOT MODIFY this method!
	*/
    private void startAnimation()
    {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                repaint();
            }
        };
        new Timer(DELAY, taskPerformer).start();
    }
}
