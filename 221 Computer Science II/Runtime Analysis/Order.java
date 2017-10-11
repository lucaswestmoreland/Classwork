/**
 * Takes an array and reorders it so elements are in ascending order.
 * @author mvail
 */
public class Order {

	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static long order(int[] array) {
		long statements = 2;
        for (int next = 1; next < array.length; next++) { // 2 + (n - 1) * 7 + 4(n - 1)
            int val = array[next];//1
            int index = next;//1
            statements += 2;
            statements += 2;//while check
            while (index > 0 && val < array[index - 1]) {
            	statements += 2;//while loop iteration
                array[index] = array[index - 1];
                index--;
                statements += 2;
                //4
            }
            statements += 1;
            array[index] = val;
            
            statements += 2; //for loop iteration
        }
        return statements; // T(n) = 2 + (n-1)(4(n-1) + 7)
        				   // T(n) = 2 + (n-1)(4n + 3)
        				   // T(n) = 4n^2 - n - 1
        				   // O(n^2)
	}
}