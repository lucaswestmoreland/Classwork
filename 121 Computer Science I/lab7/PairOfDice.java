
public class PairOfDice {
	private Die die1, die2;
	
	
	/**
	 * Accepts an integer specifying the maximum 
	 * face value (sides) and creates 2 Die objects.
	 * @param int sides
	 */
	public PairOfDice(int sides)
	{
		die1 = new Die(sides);
		die2 = new Die(sides);
	}
	
	
	/**
	 * Accepts an integer parameter specifying the maximum face value (sides),
	 * a long parameter specifying the seed for the first die, and a long parameter
	 * specifying the seed for the second die.
	 * Creates 2 Die objects with the given number of sides and corresponding seeds.
	 * @param int sides
	 * @param int seed1
	 * @param int seed2
	 */
	public PairOfDice(int sides, long seed1, long seed2)
	{
		die1 = new Die(sides, seed1);
		die2 = new Die(sides, seed2);
	}
	
	
	/**
	 * Rolls both Die objects and returns the sum of their face values.
	 * @return int faceValues of two dice
	 */
	public int roll()
	{
		return die1.roll() + die2.roll();	
	}
	
	
	/**
	 * Returns the current sum of the face values.
	 * @return int totalDice
	 */
	public int getTotal()
	{
		return die1.getFaceValue() + die2.getFaceValue();
	}
	
	/**
	 * Returns the current face value of the first Die only.
	 * @return int faceValue
	 */
	public int getFaceValue1()
	{
		return die1.getFaceValue();
	}
	
	
	/**
	 * Returns the current face value of the second Die only.
	 * @return faceValue
	 */
	public int getFaceValue2()
	{
		return die2.getFaceValue();
	}
	
	
}
