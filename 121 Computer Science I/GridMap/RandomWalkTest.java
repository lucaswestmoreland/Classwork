import java.util.Scanner;


/**
 * @author 
 */
public class RandomWalkTest
{
	private static int gridSize = 0;
	private static long seed = 0;
	
	
	
	/**
	 * 
	 */
	private static void getInput() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the grid size: ");
		gridSize = scan.nextInt();
		while(gridSize < 0)
		{
			System.out.println("That was negative, enter a POSITIVE grid size: ");
			gridSize = scan.nextInt();
		}
		
		System.out.println("Enter the seed value: ");
		seed = scan.nextLong();
		while(seed < 0)
		{
			System.out.println("That was negative, enter a POSITIVE seed: ");
			seed = scan.nextLong();
		}
		scan.close();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RandomWalkTest.getInput();
		// call getInput to process user input
		RandomWalk myWalk = new RandomWalk(gridSize, seed);
		// create RandomWalk object using the appropriate constructor
		myWalk.createWalk();
		System.out.println(myWalk.toString());
		// create the random walk and then print it
	}
	
}