import java.util.*;

/**
 * LinkedList represents a linked implementation of a list.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public abstract class AbstractLinkedList<T> implements ListADT<T>
{
	protected int count;
	protected LinearNode<T> head, tail;
	protected int modCount;

	/**
	 * Creates an empty list.
	 */
	public AbstractLinkedList()
	{
		count = 0;
		head = tail = null;
		modCount = 0;
	}

	/**
	 * Removes the first element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return a reference to the first element of this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T removeFirst() throws EmptyCollectionException
	{

		if (isEmpty())
			throw new EmptyCollectionException("LinkedList");

		LinearNode<T> current = head;
		T retVal;
		retVal = current.getElement();
		head = current.getNext();
		current = null;
		count--;
		modCount++;

		return retVal;


	}

	/**
	 * Removes the last element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return the last element in this list
	 * @throws EmptyCollectionException if the list is empty    
	 */
	public T removeLast() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("LinkedList");

		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current.getNext() != null)
		{
			previous = current;
			current = current.getNext();
		}

		if (size() == 1)  // only one element in the list
			head = tail = null;

		else   // target is at the tail
		{
			tail = previous;
			tail.setNext(null);
		}

		count--;
		modCount++;

		return current.getElement();
	}

	/**
	 * Removes the first instance of the specified element from this
	 * list and returns a reference to it. Throws an EmptyCollectionException 
	 * if the list is empty. Throws a ElementNotFoundException if the 
	 * specified element is not found in the list.
	 *
	 * @param  targetElement the element to be removed from the list
	 * @return a reference to the removed element
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public T remove(T targetElement) throws ElementNotFoundException 
	{
		if (isEmpty())
			throw new ElementNotFoundException("LinkedList");

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found)
			if (targetElement.equals(current.getElement()))
				found = true;
			else
			{
				previous = current;
				current = current.getNext();
			}

		if (!found)
			throw new ElementNotFoundException("LinkedList");

		if (size() == 1)  // only one element in the list
			head = tail = null;
		else if (current.equals(head))  // target is at the head 
			head = current.getNext();
		else if (current.equals(tail))  // target is at the tail
		{
			tail = previous;
			tail.setNext(null);
		}
		else  // target is in the middle
			previous.setNext(current.getNext());

		count--;
		modCount++;

		return current.getElement();
	}

	/**
	 * Returns the first element in this list without removing it. 
	 *
	 * @return the first element in this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T first() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("LinkedList");

		return head.getElement();
	}

	/**
	 * Returns the last element in this list without removing it. 
	 *
	 * @return the last element in this list  
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T last() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("LinkedList");


		LinearNode<T> current = head;

		while (current.getNext() != null)
		{

			current = current.getNext();
		}




		return current.getElement();

	}


	/**
	 * Returns true if the specified element is found in this list and 
	 * false otherwise.
	 *
	 * @param  targetElement the element that is sought in the list
	 * @return true if the element is found in this list
	 */
	public boolean contains(T targetElement) 
	{
		boolean found = false;
		if (isEmpty())
			found = false;

		LinearNode<T> current = head;

		while(current != null && !found)
		{
			if (targetElement.equals(current.getElement()))
				found = true;
			else
			{
				current = current.getNext();
			}
		}

		return found;

	}

	/**
	 * Returns true if this list is empty and false otherwise.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		boolean isEmpty = false;
		if(size() <= 0)
		{
			isEmpty = true;
		}


		return isEmpty;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in the list
	 */
	public int size()
	{


		return count;

	}

	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of the list    
	 */
	public String toString()
	{
		{
			StringBuilder string = new StringBuilder();

			string.append("[");

			LinearNode<T> current = head;
			while(current != null)
			{
				string.append(current.getElement() + " ");
				current = current.getNext();
			}

			string.append("]");
			return string.toString();
		}
	}

	/**
	 * Returns an iterator for the elements in this list. 
	 *
	 * @return an iterator over the elements of the list
	 */
	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}

	/**
	 * LinkedIterator represents an iterator for a linked list of linear nodes.
	 */
	private class LinkedListIterator implements Iterator<T>
	{
		private int iteratorModCount;  // the "version" of the list at the time the Iterator was created
		private LinearNode<T> current;  // the current position
		private boolean canRemove = false;

		/**
		 * Sets up this iterator using the specified items.
		 *
		 * @param collection  the collection the iterator will move over
		 * @param size        the integer size of the collection
		 */
		public LinkedListIterator()
		{
			current = head;
			iteratorModCount = modCount;
		}

		/**
		 * Returns true if this iterator has at least one more element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasNext() throws ConcurrentModificationException
		{
			if (iteratorModCount != modCount) 
				throw new ConcurrentModificationException();

			return (current != null);
		}

		/**
		 * Returns the next element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iterator is empty
		 */
		public T next() throws ConcurrentModificationException
		{
			if (!hasNext())
				throw new NoSuchElementException();

			T result = current.getElement();
			current = current.getNext();
			canRemove = true;
			return result;
		}

		/**
		 * The remove operation is not supported.
		 * 
		 * @throws UnsupportedOperationException if the remove operation is called
		 */
		public void remove() throws UnsupportedOperationException
		{
			if(iteratorModCount != modCount) throw new NoSuchElementException();
			if(!canRemove) throw new IllegalStateException();

			LinearNode<T> prevPrev = head;

			if(prevPrev.getNext() == null)
			{
				head = tail = null;
			}
			else if(prevPrev.getNext() == current)
			{
				head = current;
			}
			else{
				while(prevPrev.getNext().getNext() != current)
				{
					prevPrev = prevPrev.getNext();
				}
				if(prevPrev.getNext() == tail)
				{
					tail = prevPrev;
				}
				prevPrev.setNext(current);
			}
			canRemove = false;
		}
	}
}




