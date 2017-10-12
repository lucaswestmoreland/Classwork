import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ParseForCaps {
	public static void main(String[] args)
	{
				
				Scanner kbd = new Scanner(System.in);
				
				System.out.print("Enter a file name: ");
				String fileName = kbd.nextLine().trim();


				File file = new File(fileName);
				
				
				try {
					Scanner fileScan = new Scanner(file);

					
					System.out.println("\nContents of \"" + fileName + "\":\n");
					
					
					while (fileScan.hasNextLine()) {
						
						String line = fileScan.nextLine();
						
					
						
						Scanner lineScan = new Scanner(line);
						
				

						while (lineScan.hasNext()) {
							
							String token = lineScan.next();
							for(int i = 0; i < token.length(); i++)
							{
								if(Character.isUpperCase(token.charAt(i)))
									System.out.println(token.charAt(i));
							}
						}
						lineScan.close();
					}
					fileScan.close();
				} catch (FileNotFoundException e) {

					System.out.println("File \"" + fileName + "\" could not be opened.");
					System.out.println(e.getMessage());
					System.exit(1);
				}
	}

}
