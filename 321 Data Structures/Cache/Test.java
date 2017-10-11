import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * This is the driver class for the Cache class. Depending on the user's preference, this class will create
 * a one-level or two-level cache system that will be used to store temporary but fast data. This driver will
 * print out the needed values based on the project specifications.
 * 
 * @author lwestmorel
 *
 */
public class Test {

	private static int wordCount = 0;
	private static int hitCount, levelOneHits, levelTwoHits;


	public static void main(String[] args) {
		if(args[0].equals("1") && args.length == 3)
		{
			createCacheOne(Integer.parseInt(args[1]), args[2]); 
			levelOneValues();

		}

		else if(args[0].equals("2") && args.length == 4)
		{

			if(Integer.parseInt(args[1]) >= Integer.parseInt(args[2])) //if the size of cache one is greater than size two
			{
				System.out.println("Cache One must be smaller than cache two.");
				System.exit(1);
			}

			createCacheTwo(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
			levelTwoValues();



		}
		else
		{
			System.out.println("Incorrect input.");
			System.out.println("Usage: java Test 1 <cache size> input textfile name>");
			System.out.println("If you would like two caches then:");
			System.out.println("Usage: java Test 2 <1st-level cache size> <2nd-level cache size> <input textfile name>");

			System.exit(1);

		}

	}

	/**
	 * This method creates a One-level Cache system.
	 * @param size
	 * @param arg
	 */
	public static void createCacheOne(int size, String arg) 
	{
		Cache<String> lvlOne = new Cache<>(size);
		File fileName = new File(arg);

		try
		{
			Scanner fileScan = new Scanner(fileName);

			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, " \t");

				while(tokenizer.hasMoreTokens())
				{
					String token = tokenizer.nextToken();
					lvlOne.addObject(token);
					wordCount++;
				}
			}

			levelOneHits = lvlOne.numHits();
			fileScan.close();
		}

		catch(Exception e)
		{
			e.getMessage();
			System.exit(1);
		}
		System.out.println("Level One cache created. Entries: " + size);

	}


	/**
	 * This method creates a Two-level Cache system.
	 * @param levelOneSize
	 * @param levelTwoSize
	 * @param arg
	 */
	public static void createCacheTwo(int levelOneSize, int levelTwoSize, String arg)
	{
		Cache<String> lvlOne = new Cache<>(levelOneSize);
		Cache<String> lvlTwo = new Cache<>(levelTwoSize);
		File fileName = new File(arg);

		try
		{
			Scanner fileScan = new Scanner(fileName);

			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, " \t");

				while(tokenizer.hasMoreTokens())
				{
					String token = tokenizer.nextToken();
					lvlOne.addObject(token);
					lvlTwo.addObject(token);
					wordCount++;
				}



			}

			hitCount = lvlTwo.numHits();
			levelOneHits = lvlOne.numHits();
			levelTwoHits = (hitCount - levelOneHits);

			fileScan.close();

		}
		catch(Exception e)
		{
			e.getMessage();
			System.exit(1);
		}

		System.out.println("Level One cache created. Entries: " + levelOneSize);
		System.out.println("Level Two cache created. Entries: " + levelTwoSize);

	}



	/**
	 * This method prints out the calculated values of the level one cache to the console.
	 */
	public static void levelOneValues()
	{
		System.out.println("Total References: " + wordCount);
		System.out.println("Total Cache Hits: " + levelOneHits);
		System.out.println("Global hit Ratio: " + ((double)levelOneHits / (double)wordCount));
	}

	/**
	 * This method prints out the calculated values of the level two cache to the console.
	 */
	public static void levelTwoValues()
	{
		System.out.println("Total References: " + wordCount);
		System.out.println("Total Cache Hits: " + hitCount);
		System.out.println("Global Hit Ratio: " + ((double)hitCount / (double)wordCount));

		System.out.println("Level One Cache Hits: " + levelOneHits);
		System.out.println("Level Two Cache Hits: " + levelTwoHits);

		System.out.println("Level One Cache Hit Ratio: " + ((double)levelOneHits / (double)wordCount));
		System.out.println("Level Two Cache Hit Ratio: " + ((double)levelTwoHits / ((double)wordCount - levelOneHits)));

	}

}
