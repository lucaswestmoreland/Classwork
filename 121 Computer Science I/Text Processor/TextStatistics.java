import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Lucas Westmoreland
 *
 */
public class TextStatistics implements TextStatisticsInterface {

	private DecimalFormat fmt = new DecimalFormat("0.00");
	private String letterPlaceHolder="abcdefghijklmnopqrstuvwxyz";
	private File file;
	 
	private Scanner fileScan;
	private int charCount;
	private int wordCount = 0;
	private int lineCount;
	private int[] letterCount = new int[26];
	private int[] wordLengthCount = new int[24];
	private double averageWordLength;
	private double sum;

	/**
	 * creates constructor for Text Statistics object and find out all the
	 * statistics for the File parameter passed in
	 * @param File
	 */
	public TextStatistics(File file) {

		this.file = file;

		try {
			fileScan = new Scanner(file);
			//counts number of lines
			while (fileScan.hasNextLine()) 
			{
				lineCount++;
				String inputLine = fileScan.nextLine ();
				inputLine= inputLine.toLowerCase();
				
				//counts number of Words and new line character

				
				//counts characters
				charCount=charCount+inputLine.length()+1;
				StringTokenizer tokenizer = new StringTokenizer(inputLine,
						" ,.;:'\"&!?-_\n\t12345678910[]{}()@#$%^*/+-");
				wordCount = wordCount + tokenizer.countTokens();
					
				//counts word length frequency
				while(tokenizer.hasMoreTokens()) 
				{
					String tempWord = tokenizer.nextToken();
					int length = tempWord.length();
					wordLengthCount[length]++;
					//counts frequency of letters
					char current;   // the current character being processed
				      // Count the number of each letter occurrence
					
				      for (int ch = 0; ch < tempWord.length(); ch++)
				      {
				    	 
				         current = tempWord.charAt(ch);
				         if (current >= 'a' && current <= 'z')
				               letterCount[current - 'a']++;
				         

		
						}
				}

				

			

			}

		}
		catch (FileNotFoundException e) {
			e.getMessage();
		}

	} 

	/**
	 * Prints the statistics for the file
	 * @return String
	 */
	public String toString() {
		String result="Statistics for "+ file+"\n";
		result+="==========================================================\n";
		result+=lineCount+" Lines \n";
		result+=getWordCount()+" Words\n";
		result+=getCharCount()+" Characters "+"\n";
		result+="------------------------------\n";
		for(int i=0;i<13;i++){
			result+=(letterPlaceHolder.charAt(i)+" "+"="+" "+letterCount[i]+"\t\t"+letterPlaceHolder.charAt(i+13)+" "+"="+" "+letterCount[i+13]+"\n");
			
		}

		
		
		
		
		result+="------------------------------\n";
		result+=" length  frequency\n"+" ------  ---------\n";
		for(int i=0; i< wordLengthCount.length;i++)
		{
			if(wordLengthCount[i]!=0){
			result+="\n";
			result+=("      "+i+"\t"+"\t"+wordLengthCount[i]);
			}
		}
		result+="\nAverage word length = "+ fmt.format(getAverageWordLength())+"\n";
		
	
		return result;
	}

	@Override
	/**
	 * Returns the total amount of characters
	 * in the file
	 * @return int
	 */
	public int getCharCount() {

		return charCount;
	}

	@Override
	/**
	 * Returns the total amount of words
	 * in the file
	 * @return int
	 */
	public int getWordCount() {

		return wordCount;
	}

	@Override
	/**
	 * Returns the total amount of lines
	 * in the file
	 * @return int
	 */
	public int getLineCount() {

		return lineCount;
	}

	@Override
	/**
	 * Returns the amount of times each letter
	 * is used within the given file
	 * @return int[]
	 */
	public int[] getLetterCount() {
		
		return letterCount;
	}

	@Override
	/**
	 * Returns the frequency of each
	 * word length within the given file
	 * @return int[]
	 */
	public int[] getWordLengthCount() {

		return wordLengthCount;
	}

	@Override
	/**
	 * Returns the average Word Length of 
	 * the file
	 * @return double
	 */
	public double getAverageWordLength() {
		
		for (int i=0;i<wordLengthCount.length;i++) {
			sum= sum+(wordLengthCount[i]*i);
			averageWordLength= sum/wordCount;
			
		}
		return averageWordLength;
	}

}