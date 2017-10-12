import java.text.NumberFormat;
import java.util.Scanner;

/**
 * A simple application to test use of String, Math, DecimalFormat and NumberFormat classes.
 * @author 
 */
public class MyNameIs 
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("First name: ");
		String firstName = keyboard.nextLine();
		char firstInitial = firstName.charAt(0);
		
		System.out.print("Last name: ");
		String lastName = keyboard.nextLine();
		
		System.out.print("Enter a number: ");
		double n1 = keyboard.nextDouble();
		
		System.out.print("Enter another number (between 0 and 1): ");
		double n2 = keyboard.nextDouble();
		
		System.out.println("\nHi, my name is " + firstName + " " + lastName + ".");
		System.out.println("You'll find me under \"" + lastName + ", " + firstName + ".\"");
		System.out.println("My name badge: \"" + firstInitial + ". " + lastName + ".\"");
		
		NumberFormat percent = NumberFormat.getPercentInstance(); 
		System.out.println(percent.format(n2) + " of " + n1 + " is " + (n1 * n2));
	}
}