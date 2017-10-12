//
//BouncyBall.java
//
//Example using Random and conditional statements
//CS 121
//

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Animated program with a ball bouncing off the program boundaries
 * @author mvail
 */
public class BouncyBall extends JPanel {
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 400;
	private final int DELAY = 50; // milliseconds between Timer events
	private Random rand; //random number generator
	private int x, y, r; //anchor point coordinates
	private int xDelta, yDelta, rDelta; //change in x and y from one step to the next
	private final int DELTA_RANGE = 10; //range for xDelta and yDelta
	private final int RADIUS = 10 ; //circle radius
	private Color randColor;
	
	/**
	 * Draws a filled oval with random color and dimensions.
	 * 
	 * @param g Graphics context
	 * @return none
	 */
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		//clear canvas
		//g.setColor(getBackground());
		//g.fillRect(0, 0, width, height);
		
	//CALCULATE NEW X
		r += rDelta;
		if (r > 25)
		{
			rDelta = rDelta * -1;
		}
		
		if (r < 10 )
		{
			rDelta = rDelta * -1;
		}			
		
	x += xDelta;
	if ( (x + r) > width)
		{
		xDelta *= -1;
		x = width - r;
		}
	else 
	{}
	//CALCULATE NEW Y
	y += yDelta;
	if ( (y + r) > height)
	{
		
		yDelta *= -1;
		y = height - r;
	}
	
	if (y <= 0 + r )
	{
		yDelta *= -1;
		y=r;
	}
		
	if (x <= 0 + r)
	{
		xDelta *= -1;
		x=r;
	}
	
		//NOW PAINT THE OVAL
randColor = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
g.setColor(randColor);
g.fillOval(x-r, y-r, r*2, r*2);

		//Makes the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 * This method also sets up a Timer that will call
	 * paint() with frequency specified by the DELAY
	 * constant.
	 */
	public BouncyBall() 
	{
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		this.setDoubleBuffered(true);
		setBackground(Color.black);

		rand = new Random(); //instance variable for reuse in paint()
		randColor = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
		
		//initial ball location within panel bounds
		x = rand.nextInt(600)+1 / 2;
		y = rand.nextInt(400)+1 / 2;
		r = RADIUS;

		//deltas for x and y
		
		xDelta = rand.nextInt(10)+2;
		yDelta = rand.nextInt(10)+2;
		rDelta = 2;

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically
	 * DO NOT MODIFY
	 */
	private void startAnimation() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		};
		new Timer(DELAY, taskPerformer).start();
	}
	
	/**
	 * Starting point for the BouncyBall program
	 * DO NOT MODIFY
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Bouncy Ball");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BouncyBall());
		frame.pack();
		frame.setVisible(true);
	}

}