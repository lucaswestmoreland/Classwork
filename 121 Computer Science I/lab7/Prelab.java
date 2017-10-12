import java.util.Scanner;


public class Prelab {

	private int i1;
	private int i2;
	private double d1;
	private double d2;
	
	public int maxOfTwo(){
			
	if(i1 > i2)
		return i1;
	else
		return i2;
	}
	
	public boolean larger(boolean isBigger){
		if(d1 > d2)
		{
			isBigger = true;
			return isBigger;
		}
		else
		{
			isBigger = false;
			return isBigger;
		}
	}
		
		Scanner scan = new Scanner(System.in);
		String word = scan.nextLine();
		
		String reverseString(String word)
		{
			String string = "";
			for(int i = word.length()-1; i >= 0; i--)
			{
				string += word.charAt(i);
			}
			return string;
		}
		
}

