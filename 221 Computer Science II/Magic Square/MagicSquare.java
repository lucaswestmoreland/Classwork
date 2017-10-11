import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program creates and checks different files to see
 * if they are magic squares.
 * 
 * @author lwestmoreland
 *
 */
public class MagicSquare {

	private int n;
	private int[][] matrix; 
	private int[] rowSum;
	private int diagonalSum;
	private int rDiagonalSum;
	private boolean isMagic = true;
	private File file;
	private int exp; 
	private int row;
	private int col;
	private int oldRow;
	private int oldCol;


	/**
	 * Constructor for the -create argument.
	 * Asks for fileName and size
	 * @param file
	 * @param n
	 */
	public MagicSquare(File file, int n)
	{
		this.file = file;
		this.n = n;

	}

	/**
	 * Constructor for the -check argument
	 * Asks only for existing fileName
	 * @param file
	 */
	public MagicSquare(File file)
	{
		this.file = file;
	}


	/**
	 * This method uses the PrintWriter
	 * class to write the values in the matrix
	 * to a file.
	 */
	private void writeMatrix()
	{
		PrintWriter outFile;
		try {
			outFile = new PrintWriter(new FileWriter(file));

			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					outFile.printf("%8s", matrix[i][j]);
				}
				outFile.println();
			}


			outFile.close();
			//add more in between

		} 



		catch (IOException e) {

			e.printStackTrace();
		}




	}

	/**
	 * This method creates the magic square
	 * using the algorithms given by the professor
	 * as well as using the writeMatrix() method
	 * 
	 */
	public void createMagicSquare()
	{
		matrix = new int[n][n];
		row = n - 1;
		col = n / 2;


		for (int i = 1; i <= n*n; i++) // Runs through the array and assigns values
		{
			matrix[row][col] = i;
			oldRow = row;
			oldCol = col;
			row++;
			col++;


			if(row == n) //resets the row back to 0 once it gets to the end
			{
				row = 0;
			}

			if(col == n) //resets the col back to 0 once it gets to the end
			{
				col = 0;
			}

			if(matrix[row][col] != 0)
			{
				row = oldRow;
				col = oldCol;
				row--;
			}


			//implement the writeMatrix method here
			// need to figure out how to concert matrix numbers into a file

		}
		writeMatrix();
	}

	/**
	 * This private method is used to read the
	 * matrix from a file and assign those values
	 * into an array.
	 * @param file
	 */
	private void readMatrix(File file) //Assign file to an array
	{

		Scanner fileScan;
		try {
			fileScan = new Scanner(file);

			if(fileScan.hasNextInt())
			{
				n = fileScan.nextInt();
			}
			else
			{
				System.out.println("This file does not contain valid integers.");
				System.exit(1);
			}
			matrix = new int[n][n];




			for(int i = 0; i < n; i++)
			{

				for(int j = 0; j < n; j++)
				{
					if(fileScan.hasNextInt())
					{
						matrix[i][j] = fileScan.nextInt();
					}
				}
			}


		}
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}


	}

	/**
	 * This method is used when we know if the 
	 * square is perfect or not. Depending on
	 * the status of the square, this method 
	 * prints the proper statement.
	 *
	 * @param isMagic
	 */
	public void printMagicSquare(boolean isMagic)
	{
		this.isMagic = isMagic;
		if(isMagic == true)
		{
			System.out.println("The matrix...\n");
			for(int i = 0; i < matrix[0].length; i++)
			{
				for(int j = 0; j < matrix.length; j++)
				{

					System.out.printf("%4d", matrix[i][j]);

				}
				System.out.println();
			}
			System.out.println("\nIs a Magic Square.");
		}

		else
		{
			System.out.println("The matrix...\n");
			for(int i = 0; i < matrix[0].length; i++)
			{
				for(int j = 0; j < matrix.length; j++)
				{

					System.out.printf("%4d", matrix[i][j]);

				}
				System.out.println();
			}
			System.out.println("\nIs not a Magic Square.");
		}

	}

	/**
	 * This method goes through a series
	 * of checks to determine if the squares
	 * given are perfect squares.
	 * @param file
	 * @return
	 */
	public boolean checkMatrix(File file)
	{
		readMatrix(file);

		exp = ((n*((n*n) + 1)) / 2);
		//Scanner fileScan = new Scanner(filename);

		rowSum = new int[n];

		for (int i = 0; i < matrix.length; i++) //checks the columns
		{ 
			int sum = 0;
			for (int j = 0; j < matrix[i].length; j++)
			{                
				sum += matrix[i][j];
				rowSum[j] += matrix[i][j];
			}

			if(sum != exp)
			{
				isMagic = false;

				return isMagic;
			}
		}  
		for(int k = 0; k < rowSum.length; k++) //checks the rows not this one
		{
			if(rowSum[k] != exp)
			{
				isMagic = false;

				return isMagic;
			}
		}

		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i == j)
				{
					diagonalSum += matrix[i][j];
				}
				if(i + j == n - 1)
				{
					rDiagonalSum += matrix[i][j];
				}
			}


		}
		if(diagonalSum != exp || rDiagonalSum != exp)
		{
			isMagic = false;
			return isMagic;
		}


		isMagic = true;
		return isMagic;


	}
}








