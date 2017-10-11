import java.util.*;

/**
 * Class for searching and sorting DoubleLinkedLists 
 * using either natural order or a Comparator.
 *
 * @author spanter, mvail
 */
public class SearchAndSort
{


	/**
	 * Sorts a list that implements the DoubleLinkedListADT using the
	 * natural ordering of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The data type in the list must extend Comparable
	 * @param list
	 *            The list that will be sorted
	 * @see DoubleLinkedListADT 
	 */
	public static <T extends Comparable<T>> void sort(DoubleLinkedListADT<T> list) {

		if(list.size() > 1)
		{




			int partition = list.size() / 2;

			DoubleLinkedListADT<T> leftList = new WrappedDLL<>();
			DoubleLinkedListADT<T> rightList = new WrappedDLL<>();

			while(list.size() > 0)
			{
				if(list.size() > partition)
				{
					leftList.add(list.removeFirst());
				}
				else
				{
					rightList.add(list.removeFirst());
				}
			}



			sort(leftList);
			sort(rightList);

			merge(leftList, rightList, list);

		}



	}


	/**
	 * Sorts a DoubleLinkedListADT with the provided Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of list to sort
	 * @param list
	 *            The list to sort
	 * @param c
	 *            The Comparator to use
	 * @see DoubleLinkedListADT
	 */
	public static <T> void sort(DoubleLinkedListADT<T> list, Comparator<T> c) {

		if(list.size() > 1)
		{




			int partition = list.size() / 2;

			DoubleLinkedListADT<T> leftList = new WrappedDLL<>();
			DoubleLinkedListADT<T> rightList = new WrappedDLL<>();

			while(list.size() > 0)
			{
				if(list.size() > partition)
				{
					leftList.add(list.removeFirst());
				}
				else
				{
					rightList.add(list.removeFirst());
				}
			}



			sort(leftList, c);
			sort(rightList, c);

			merge(leftList, rightList, list, c);

		}



	}


	/**
	 * Recursively sorts the list using a mergeSort algorithm. 
	 * @param leftList
	 * @param rightList
	 * @param wholeList
	 */
	private static <T extends Comparable<T>> void merge(DoubleLinkedListADT<T> leftList, DoubleLinkedListADT<T> rightList,
			DoubleLinkedListADT<T> wholeList )
	{

		while(leftList.size() > 0 && rightList.size() > 0)
		{
			if(leftList.first().compareTo(rightList.first()) == 1)
			{
				wholeList.add(rightList.removeFirst());
			}

			else 
			{
				wholeList.add(leftList.removeFirst());
			}

		}

		while(rightList.size() > 0)
		{
			wholeList.add(rightList.removeFirst());
		}

		while(leftList.size() > 0)
		{
			wholeList.add(leftList.removeFirst());
		}



	}


	/**
	 * Recursively sorts the list using a mergeSort algorithm and uses a comparator
	 * to compare each item in the lists.
	 * 
	 * @param leftList
	 * @param rightList
	 * @param wholeList
	 * @param c the comparator used
	 */
	private static <T> void merge(DoubleLinkedListADT<T> leftList, DoubleLinkedListADT<T> rightList,
			DoubleLinkedListADT<T> wholeList, Comparator<T> c )
	{

		while(leftList.size() > 0 && rightList.size() > 0)
		{
			if(c.compare(leftList.first(), rightList.first()) == 1)
			{
				wholeList.add(rightList.removeFirst());
			}

			else 
			{
				wholeList.add(leftList.removeFirst());
			}

		}

		while(rightList.size() > 0)
		{
			wholeList.add(rightList.removeFirst());
		}

		while(leftList.size() > 0)
		{
			wholeList.add(leftList.removeFirst());
		}



	}





	/**
	 * Finds the smallest element in a list according to the natural ordering of 
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findSmallest(DoubleLinkedListADT<T> list) {


		if(list.isEmpty())
		{
			return null;
		}

		if(list.size() == 1)
		{
			return list.first();
		}


		T first = list.removeFirst();
		T smallest = findSmallest(list);
		list.addToFront(first);

		if(first.compareTo(smallest) == 1)
		{
			return smallest;
		}

		else if(first.compareTo(smallest) == -1)
		{
			return first;
		}

		return first;
	}

	/**
	 * Finds the smallest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findSmallest(DoubleLinkedListADT<T> list, Comparator<T> c) {


		if(list.isEmpty())
		{
			return null;
		}

		if(list.size() == 1)
		{
			return list.first();
		}


		T first = list.removeFirst();
		T smallest = findSmallest(list, c);
		list.addToFront(first);

		if(c.compare(first, smallest) == 1)
		{
			return smallest;
		}

		else if(c.compare(first, smallest) == -1)
		{
			return first;
		}

		return first;
	}



	/**
	 * Finds the largest element in a list according to the natural ordering of
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findLargest(DoubleLinkedListADT<T> list) {

		if(list.isEmpty())
		{
			return null;
		}

		if(list.size() == 1)
		{
			return list.first();
		}


		T first = list.removeFirst();
		T largest = findLargest(list);
		list.addToFront(first);

		if(first.compareTo(largest) == 1)
		{
			return first;
		}

		else if(first.compareTo(largest) == -1)
		{
			return largest;
		}

		return first;


	}

	/**
	 * Finds the largest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findLargest(DoubleLinkedListADT<T> list, Comparator<T> c) {

		if(list.isEmpty())
		{
			return null;
		}

		if(list.size() == 1)
		{
			return list.first();
		}


		T first = list.removeFirst();
		T largest = findLargest(list, c);
		list.addToFront(first);

		if(c.compare(first, largest) == 1)
		{
			return first;
		}

		else if(c.compare(first, largest) == -1)
		{
			return largest;
		}

		return first;
	}
}
