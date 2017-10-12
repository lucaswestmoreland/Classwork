import java.util.Random;
import java.util.Scanner;


public class DiceRoller {
	
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		PairOfDice myDicePair = new PairOfDice(6, rand.nextInt(),rand.nextInt());
		PairOfDice compDicePair = new PairOfDice(6, rand.nextInt(),rand.nextInt());
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Would you like to play the dice game?(y/n)");
		char playAgain = scan.nextLine().charAt(0);
		int myWins = 0;
		int compWins = 0;
		int ties = 0;
		
		while(playAgain == 'y')
		{
			int myRollValue = myDicePair.roll();
			int compRollValue = compDicePair.roll();
			
			
			
			System.out.println("Your roll: " + myRollValue + " (" 
					+ myDicePair.getFaceValue1() + " + " + myDicePair.getFaceValue2() + ")");
			System.out.println("Computer's Roll: " + compRollValue + " (" 
					+ compDicePair.getFaceValue1() + " + " + compDicePair.getFaceValue2() + ")");
			
		
			
			if(myRollValue > compRollValue)
			{
				System.out.println("You've won!");
				myWins++;
				System.out.println("Your Wins: " + myWins + "\t Computer's wins: " + compWins + 
						"\t Ties: " + ties + ".");
			}
			else if(myRollValue < compRollValue)
			{
				System.out.println("You've lost!");
				compWins++;
				System.out.println("Your Wins: " + myWins + "\t Computer's wins: " + compWins + 
						"\t Ties: " + ties + ".");
			}
			else
			{
				System.out.println("You've tied!");
				ties++;
				System.out.println("Your Wins: " + myWins + "\t Computer's wins: " + compWins + 
						"\t Ties: " + ties + ".");
			}
			
			System.out.println("Would you like to play again?(y/n)");
			playAgain = scan.nextLine().charAt(0);
		}
		
			System.out.println("Thanks for Playing!");
			
		

		
		
		scan.close();
		
	}

}
