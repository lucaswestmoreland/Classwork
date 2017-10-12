import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please input a string: ");
		String phrase = scan.nextLine();
		
		
		int start = 0;
		
		for(int length = phrase.length()-1; length >= start; length-- )
		System.out.print(phrase.charAt(length));
		// TODO Auto-generated method stub
		scan.close();
	}

}
