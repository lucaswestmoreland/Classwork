import java.io.*;
import java.text.*;
import java.util.*;

public class HashTest {

	static int inputType = 1;
	static double loadFactor = 1;
	static int debugLevel = 0;
	static long tableSize = 0;
	static String dataType = null;
	static Prime prime = new Prime();
	static int linearOverflow = 0;
	static int doubleOverflow = 0;

	public static void main(String[] args) {

		if(args.length == 2) {
			try {
				inputType = Integer.parseInt(args[0]);
				loadFactor = Double.parseDouble(args[1]);
				debugLevel = 0;
			} catch (Exception e) {
				System.err.println("Syntax:\njava HashTest <input type> <load factor> [<debug level>]\n" 
						+ e.getMessage());
				System.exit(1);
			}			
		}
		else
		{
			try {
				inputType = Integer.parseInt(args[0]);
				loadFactor = Double.parseDouble(args[1]);
				debugLevel = Integer.parseInt(args[2]);
			} catch (Exception e) {
				System.err.println("Syntax:\njava HashTest <input type> <load factor> [<debug level>]\n" 
						+ e.getMessage());
				System.exit(1);

			}
		}

		tableSize = Prime.findPrime(95500, 96000);
		System.out.println("A good table size is found: " + tableSize);

		HashTable linearTable = new HashTable((int) tableSize, 0, loadFactor);
		HashTable doubleTable = new HashTable((int) tableSize, 1, loadFactor);

		switch (inputType) {
		case 1:
			dataType = "random number generator";
			Random rng = new Random();
			while (linearOverflow != -1 && doubleOverflow != -1) {
				long random = rng.nextLong();
				if (random < 0)
					random = Math.abs(random);
				HashObject<Long> randomHashObject1 = new HashObject<Long>(new Long(random));
				HashObject<Long> randomHashObject2 = new HashObject<Long>(new Long(random));

				Long key1 = randomHashObject1.getKey();
				Long key2 = randomHashObject2.getKey();

				if (!key1.equals(key2))
				{
					System.out.println(key2 + " " + key1);
				}

				linearOverflow = linearTable.insert(randomHashObject1, key1);					
				doubleOverflow = doubleTable.insert(randomHashObject2, key2);


			}
			break;
		case 2:
			dataType = "current time in milliseconds";
			while (linearOverflow != -1 && doubleOverflow != -1) {
				long currentTime = System.currentTimeMillis();

				HashObject<Long> timeHashObject1 = new HashObject<Long>(new Long(currentTime));
				HashObject<Long> timeHashObject2 = new HashObject<Long>(new Long(currentTime));

				Long key1 = timeHashObject1.getKey();
				Long key2 = timeHashObject2.getKey();

				linearOverflow = linearTable.insert(timeHashObject1, key1);
				doubleOverflow = doubleTable.insert(timeHashObject2, key2);


			}
			break;
		case 3:
			dataType = "words from 'word-list'";
			Parse wordReader;
			try {
				wordReader = new Parse("word-list");
			} catch (IOException e1) {
				wordReader = null;
				System.err.println("Make sure 'word-list' is in the directory.");
				System.exit(1);

			}
			while (linearOverflow != -1 && doubleOverflow != -1) {
				String word;
				try {
					word = wordReader.nextWord();

					if (!wordReader.hasWord()) {
						linearOverflow = -1;
						doubleOverflow = -1;
					}

				} catch (IOException e1) {
					word = null;
					System.err.println("Make sure 'word-list' is in the directory.");
					System.exit(1);

				}

				HashObject<String> stringHashObject1 = new HashObject<String>(word);
				HashObject<String> stringHashObject2 = new HashObject<String>(word);

				Long key1 = stringHashObject1.getKey();
				Long key2 = stringHashObject1.getKey();

				if (linearOverflow != -1)
					linearOverflow = linearTable.insert(stringHashObject1, key1);

				if (doubleOverflow != -1)
					doubleOverflow = doubleTable.insert(stringHashObject2, key2);
			}
			break;

		default:
			System.err.println("Input type should be 1, 2, or 3\n");
			System.exit(1);
			break;
		}

		System.out.println("Data source type: " + dataType);
		boolean debugTable = false;
		if (debugLevel == 2)
			debugTable = true;
		if (debugLevel > 0) {
			System.out.println(linearTable.toString(debugTable));
			System.out.println("\n" + doubleTable.toString(debugTable));
		}

		printResults(linearTable, doubleTable);

	}

	private static void printResults(HashTable linear, HashTable doubleHash) {
		DecimalFormat loadFormat = new DecimalFormat("#.##");
		StringBuilder str = new StringBuilder();

		str.append("\n");
		str.append("Using linear hashing....\n");
		str.append("Inserted " + linear.numInserted + ", of which " + linear.duplicates + " duplicates.\n");
		str.append("load factor = " + loadFormat.format(((double) linear.numInserted / (double) tableSize))
				+ ", Avg. no of probes " + linear.getAverage() + "\n");
		str.append("\n");
		str.append("Using double hashing....\n");
		str.append("Inserted " + doubleHash.numInserted + ", of which " + doubleHash.duplicates + " duplicates.\n");
		str.append("load factor = " + loadFormat.format(((double) doubleHash.numInserted / (double) tableSize))
				+ ", Avg. no of probes " + doubleHash.getAverage() + "\n");

		System.out.println(str.toString());
	}


}
