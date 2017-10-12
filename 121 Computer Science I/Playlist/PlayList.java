import java.util.*;
import java.text.*;
/**
 * This class will read in input from the user asking for 3 different songs.
 * It will sort the songs based on time from shortest to longest. 
 * It also prints the song closest to 4 minutes and tells the average playtime of all 
 * the songs.
 * @author Lucas Westmoreland
 *
 */

public class PlayList {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Title of the First Song: ");
		String title = scan.nextLine();
		
		System.out.println("Enter the Artist of the First Song: ");
		String artist = scan.nextLine();
		
		System.out.println("Enter the Playtime of the First song (mm:ss): "); //need to convert to secs
		String playTimeMixed = scan.nextLine();
		
		int playTimeInd = playTimeMixed.indexOf(':');
		String minutesString = playTimeMixed.substring(0 , playTimeInd);
		int minutes = Integer.parseInt(minutesString);
		String secondsString = playTimeMixed.substring(playTimeInd+1, 4);
		int seconds = Integer.parseInt(secondsString);
		int playTime = (minutes * 60) + seconds; 
		
		System.out.println("Enter the Filename of the first song: " );
		String fileName = scan.nextLine();
		
		Song song = new Song(title, artist, playTime, fileName);
		
			
		System.out.println("Enter the Title of the Second Song: ");
		String title2 = scan.nextLine();
		
		System.out.println("Enter the Artist of the Second Song: ");
		String artist2 = scan.nextLine();
		
		System.out.println("Enter the Playtime of the Second song (mm:ss): "); //need to convert to secs
		String playTimeMixed2 = scan.nextLine();
		
		int playTimeInd2 = playTimeMixed2.indexOf(':');
		String minutesString2 = playTimeMixed2.substring(0 , playTimeInd2);
		int minutes2 = Integer.parseInt(minutesString2);
		String secondsString2 = playTimeMixed2.substring(playTimeInd2+1, 4);
		int seconds2 = Integer.parseInt(secondsString2);
		int playTime2 = (minutes2 * 60) + seconds2; 
		
		System.out.println("Enter the Filename of the Second song: " );
		String fileName2 = scan.nextLine();
		
		Song song2 = new Song(title2, artist2, playTime2, fileName2);
		

		System.out.println("Enter the Title of the Third Song: ");
		String title3 = scan.nextLine();
		
		System.out.println("Enter the Artist of the Third Song: ");
		String artist3 = scan.nextLine();
		
		System.out.println("Enter the Playtime of the Third song (mm:ss): "); //need to convert to secs
		String playTimeMixed3 = scan.nextLine();
		
		int playTimeInd3 = playTimeMixed3.indexOf(':');
		String minutesString3 = playTimeMixed3.substring(0 , playTimeInd3);
		int minutes3 = Integer.parseInt(minutesString3);
		String secondsString3 = playTimeMixed3.substring(playTimeInd3+1, 4);
		int seconds3 = Integer.parseInt(secondsString3);
		int playTime3 = (minutes3 * 60) + seconds3; 
		
		System.out.println("Enter the Filename of the Third song: " );
		String fileName3 = scan.nextLine();
		
		Song song3 = new Song(title3, artist3, playTime3, fileName3);
		
		double averageTime = (playTime + playTime2 + playTime3) / 3;
		
		DecimalFormat fmt1 = new DecimalFormat("####.##");
		
		System.out.println("Average Playtime: " + fmt1.format(averageTime) + " seconds.");
		
		System.out.print("The song Title closest to 4 minutes is: ");
		
		int diff1 = playTime - 240;
		int diff2 = playTime2 - 240;
		int diff3 = playTime3 - 240;
		
		if(Math.abs(diff1) < Math.abs(diff2) && Math.abs(diff1) < Math.abs(diff3))
		{
			System.out.print(title);
		}
		else if(Math.abs(diff2) < Math.abs(diff1) && Math.abs(diff2) < Math.abs(diff3))
		{
			System.out.print(title2);
		}
		else
		{
			System.out.print(title3);
		}
		System.out.print("\r");
		
		System.out.println("==============================================================================");
		
		System.out.println("Title                Artist               FileName                    Playtime");
		
		System.out.println("==============================================================================");
		if (playTime < playTime2 && playTime < playTime3 && playTime2 < playTime3)
		{
			System.out.println(song);
			System.out.println(song2);
			System.out.println(song3);
		}
		else if (playTime2 < playTime3 && playTime2 < playTime && playTime < playTime3)
		{
			System.out.println(song2);
			System.out.println(song);
			System.out.println(song3);
		}
		else if (playTime3 < playTime && playTime3 < playTime2 && playTime2 < playTime)
		{
			System.out.println(song3);
			System.out.println(song2);
			System.out.println(song);
			
		}
		else if (playTime < playTime2 && playTime < playTime3 && playTime3 < playTime2)
		{
			System.out.println(song);
			System.out.println(song3);
			System.out.println(song2);
		}
		else if (playTime2 < playTime3 && playTime2 < playTime && playTime3 < playTime)
		{
			System.out.println(song2);
			System.out.println(song3);
			System.out.println(song);
		}
		else if (playTime3 < playTime && playTime3 < playTime2 && playTime < playTime2)
		{
			System.out.println(song3);
			System.out.println(song);
			System.out.println(song2);
			
		}
			
		else 
		{
			System.out.println(song);
			System.out.println(song2);
			System.out.println(song3);
		}
		scan.close();
	}

}
