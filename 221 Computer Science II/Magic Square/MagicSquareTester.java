import java.io.File;


public class MagicSquareTester {

	public static String check = "-check", create = "-create", size;


	public static void main(String[] args) {


		if (args.length == 3)
		{
			if(args[0].equals(create))
			{
				
				System.out.println("Creating matrix...");
				File file = new File(args[1]);
				MagicSquare square = new MagicSquare(file,Integer.parseInt(args[2]));
				square.createMagicSquare();

			}
			else
			{
				System.out.println("Invalid entry. [-check] [filename] "
						+ "OR [-create] [filename] [size]");
				System.exit(1);
			}

		}

		else if(args.length == 2)
		{

		if(args[0].equals(check))
			{
				File file = new File(args[1]);
				MagicSquare checkSquare = new MagicSquare(file);
				checkSquare.printMagicSquare(checkSquare.checkMatrix(file));		
			}
		}
		else
		{
			System.out.println("Invalid entry. [-check] [filename] "
					+ "OR [-create] [filename] [size]");
			System.exit(1);
		}

		//Implement more if statements for each scenario

	}

}
