import java.util.Scanner;
import java.util.Random;

/**
 * PP 4.13: Rock, Paper, Scissors Game.
 *
 * @author
 */
public class RockPaperScissors {
	public enum Player {
		COMPUTER, USER, TIE
	};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Gesture computerGesture = null, userGesture = null;
		Player winner = null;

		int userWins = 0, userLosses = 0, ties = 0;
		boolean again = false;
		do {
			// computer chooses
			// TODO: Change the following statement so it makes
			// a random choice. (Hint: You can get the number of
			// gestures to choose from using Gesture.values().length).
			Random rand = new Random();
			int choice = rand.nextInt(Gesture.values().length); // use Random
																// here

			// TODO: Use a switch statement to assign corresponding
			// Gesture enum value to the computerGesture variable
			switch (choice) {
			case 0:
				computerGesture = Gesture.ROCK;
				break;
			case 1:
				computerGesture = Gesture.PAPER;
				break;
			case 2:
				computerGesture = Gesture.SCISSORS;
				break;
			}

			// TODO: Print a menu of all available gestures using a
			// for-each loop that iterates over Gesture.values()
			System.out.println("Select your gesture:");
			for (Gesture move : Gesture.values()) {
				System.out.println(move.ordinal() + ")" + move);
			}

			// player chooses
			choice = scan.nextInt();
			// TODO: Use a switch statement to assign corresponding
			// Gesture enum value to the userGesture variable
			switch (choice) {
			case 0:
				userGesture = Gesture.ROCK;
				break;
			case 1:
				userGesture = Gesture.PAPER;
				break;
			case 2:
				userGesture = Gesture.SCISSORS;
				break;
			}

			// clear the scan buffer
			scan.nextLine();

			System.out.println("You chose " + userGesture + ".");
			System.out.println("Computer chose " + computerGesture + ".");

			// TODO: Determine the winner. Set the winner variable
			// to the correct Player.
			// You can use nested if-else statements or nested
			// switch statements
			if (userGesture == Gesture.ROCK
					&& computerGesture == Gesture.SCISSORS
					|| userGesture == Gesture.SCISSORS
					&& computerGesture == Gesture.PAPER
					|| userGesture == Gesture.PAPER
					&& computerGesture == Gesture.ROCK) {
				userWins++;
				System.out.println("You've Won! You have won: " + userWins
						+ " time(s)!");

			} else if (userGesture == Gesture.ROCK
					&& computerGesture == Gesture.PAPER
					|| userGesture == Gesture.PAPER
					&& computerGesture == Gesture.SCISSORS
					|| userGesture == Gesture.SCISSORS
					&& computerGesture == Gesture.ROCK) {
				userLosses++;
				System.out.println("You Suck! The computer has won: "
						+ userWins + " time(s)!");
			} else {
				ties++;
				System.out.println("You've tied! Ties: " + ties + ".");
			}

			System.out.println("Do you want to play again?(y/n)");
			String answer = scan.nextLine();
			if (answer.charAt(0) == 'y') {
				again = true;
			}
			if (answer.charAt(0) == 'n') {
				again = false;
			}
		} while (again == true);

		// TODO: Print who won and increment appropriate counter.
		// Use a switch statement on the value of winner.
		// (e.g. switch(winner) { ... }).

		// TODO: Ask if they want to play again and loop back to the
		// top if they do. Print a table of final results before
		// exiting if they decide they are done playing.
		System.out.println("You've won " + userWins + " times.");
		System.out.println("You've lost " + userLosses + " times.");
		System.out.println("We tied " + ties + " times.");
	}
}
