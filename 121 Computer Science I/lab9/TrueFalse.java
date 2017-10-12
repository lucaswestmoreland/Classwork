import java.util.Scanner;


public class TrueFalse {

	public static void main(String[] args) {
		

	Scanner scan = new Scanner(System.in);	
	
	System.out.println("Enter array size: ");
	int size = scan.nextInt();
	
	boolean[] flags = new boolean[size];
	
	
	flags[0] = true; 
	for (int i=1; i < flags.length; i++) 
	{ 
	flags[i] = !flags[i-1];	
	} 
	for (int i=0; i<flags.length; i++) 
	{ 
	System.out.println(flags[i]); 
	} 
	} 
		
		
		
		
		
		
	}

