import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ParseAndRewrap {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the filename: ");
		String fileName = scan.nextLine();
		
		System.out.println("Enter the max characters for each line: ");
		int maxChars = scan.nextInt();
		
		File file = new File(fileName);		
		String str = ""; 
		int shortestLine = maxChars;
		int longestLine = 0;
		try 
		{
			Scanner fileScan = new Scanner(file);
			
			
			System.out.println(fileName + ".txt reformatted with a max line length of " + maxChars + ".\n");
			

			while (fileScan.hasNextLine()) {
				
				String line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				
		

				while (lineScan.hasNext()) {
					
					String token = lineScan.next();
					
					if (str.length() + token.length() == maxChars)
					{
						str += token;
					}
					else
	
					if(str.length() + token.length() < maxChars)
					{
						str += token + " ";
						
					}	
					
					
					
					else
					{
						
						if(str.length() > longestLine)
							longestLine = str.length();
						
						
						if(str.length() < shortestLine)
							shortestLine = str.length();
						
						System.out.println(str);
						
						if(str.length() + token.length() != maxChars)
							str = token + " ";
						
						
					}
				
					
				}
				
				lineScan.close();
			}
			fileScan.close();
			System.out.println(str);
			if(str.length() > longestLine)
				longestLine = str.length();
			
			
			if(str.length() < shortestLine)
				shortestLine = str.length();
			System.out.println("Longest Line: " + longestLine);
			System.out.println("Shortest Line: " + shortestLine);
		} catch (FileNotFoundException e) 
		{
			e.getMessage();
		}
		
		

	}

}
