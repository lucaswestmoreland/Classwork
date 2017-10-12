import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class will create a RandomWalk 
 * object with different methods
 * @author Lucas Westmoreland
 *
 */
public class RandomWalk {

	private int gridSize = 0;
	private Random rand = null;
	private boolean done = false;
	private ArrayList<Point> path = null;
	private int choice;
	
	/**
	 * Initializes the RandomWalk class asking the user for 
	 * the size of the grid. (constructor)
	 * @param size
	 */
	public RandomWalk(int size)
	{
		gridSize = size;
		rand = new Random(0);
		done = false;
		path = new ArrayList<Point>();
		path.add(new Point(0,0));
	}
	
	/**Initializes the RandomWalk class asking the user for 
	 * the size of the grid as well as the seed for the random
	 * number. (constructor)
	 * @param size
	 * @param seed
	 */
	public RandomWalk(int size, long seed)
	{
		gridSize = size;
		rand = new Random(seed);
		done = false;
		path = new ArrayList<Point>();
		path.add(new Point(0,0));	
	}
	
	
	/**
	 * Makes the walk go one more step.
	 */
	public void step()
	{
		int size = path.size();
		Point p = path.get(size - 1);
		choice = rand.nextInt(2);	
		
			if((p.x == gridSize - 1) && (p.y == gridSize - 1))
				{
					done = true;
				}
		
			else if(choice == 0)
				{
					
					if(p.y == gridSize - 1)
					{
						path.add(new Point (p.x + 1, p.y));
					}
					else
						path.add(new Point(p.x, p.y + 1));
				}
		
			else
				{
					if(p.x == gridSize - 1)
					{
						path.add(new Point (p.x, p.y + 1));
					}
					else
						path.add(new Point(p.x + 1, p.y));
				}	
	}
	
	/**
	 * Creates the entire walk in one call by internally using the step() method.
	 */
	public void createWalk()
	{
		while(done == false) step();
	}
	
	/**
	 * Returns the current value of the done variable.
	 * @return boolean
	 */
	public boolean isDone()
	{
		return done;
	}
	
	/**
	 * Getter to get reference to the random walk path.
	 * @return ArrayList
	 */
	public ArrayList<Point> getPath()
	{
		return path;
	}
	
	/**
	 * Returns the path as a nicely formatted string
	 * @return String
	 */
	public String toString()
	{
		String str = "";
		for(Point p : path)
			str += "[" + p.x + "," + p.y + "] ";
		return str;
	}
	
}
	
