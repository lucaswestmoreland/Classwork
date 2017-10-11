/**
 * Returns index of a value in an int[] or -1 if it isn't found.
 * @author mvail
 */
public class Find {

	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	public static int find(int[] array, int value) {
		int statements = 2;
		for (int i = 0; i < array.length; i++) { //2 + n
			statements += 1;//if statement
			if (array[i] == value) {//
				return statements;
			}
			statements += 2;//for loop
		}
		return statements; //t(n) = 2 + 3n; O(n);
						
	}
}