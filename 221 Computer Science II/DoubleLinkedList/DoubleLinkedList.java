import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author lwestmoreland
 * 
 * This class contains numerous methods that are used in a Double Linked List. 
 * The Double Linked List class implements the DoubleLinkedListADT interface and extends the 
 * AbstractLinkedList class.
 *
 * 
 */
public class DoubleLinkedList<T> extends AbstractLinkedList<T> implements DoubleLinkedListADT<T> {

	
	
	
	/** 
	 * This method takes in an element and adds it to
	 *  the front of the list
	 *  @param element that is being added to the front of the list
	 */
	@Override	
	public void addToFront(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);

		if(size() == 0)
		{
			head = tail = newNode;
		}
		else
		{
			LinearNode<T> tempNode = head;
			head.setPrev(newNode);
			head = newNode;
			head.setNext(tempNode);
		}

		count++;
		modCount++;

		//update counts

	}

	/**
	 * This method takes in an element and adds it to the rear of the list
	 * @param element that is being added to the rear of the list
	 */
	@Override
	public void addToRear(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(tail == null)
		{
			head = tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		count++;
		modCount++;

	}

	/**
	 * This method takes in a target and an element and adds the element to the
	 * spot after the target.
	 * @param element that is being added
	 * @param target place that the element is being added after
	 */
	@Override
	public void addAfter(T element, T target) {

		if(contains(target) == false)
		{
			throw new ElementNotFoundException("DLL");
		}
				



		ListIterator<T> lit = listIterator();

		T current;

		while(lit.hasNext())
		{
			current = lit.next();

			if(current == target)
			{
				lit.add(element);
			}
		}


	}

	/**
	 * This method removes the first element from the list
	 * and resets the head to the next element of the list.
	 * @return retVal element removed
	 */
	@Override
	public T removeFirst() {
		if(isEmpty())
		{
			throw new EmptyCollectionException("LinkedList");
		}

		LinearNode<T> originalHead = head;
		LinearNode<T> newHead = head.getNext();




		head = newHead;

		count--;
		modCount++;

		return originalHead.getElement();
	}

	/**
	 * This method removes the last element of the list and replaces
	 * the tail to the element before the element removed.
	 * @return retVal element removed
	 */
	@Override
	public T removeLast() {

		if(isEmpty())
		{
			throw new EmptyCollectionException("DLL");
		}


		T retVal;

		if(size() == 1)
		{
			retVal = head.getElement();
			head = tail = null;
		}
		else
		{



			retVal = tail.getElement();
			LinearNode<T> tempNode = tail.getPrev();


			tempNode.setNext(null);
			tail = tempNode;
		}
		count--;
		modCount++;

		return retVal;



	}

	/**
	 * This method takes in an element and searches the list for
	 * the element. Once found, the method removes the element and
	 * adjusts the list accordingly.
	 * @param element that is being removed
	 * @return retVal element removed
	 */
	@Override
	public T remove(T element) {

		if(contains(element) == false)
		{
			throw new ElementNotFoundException("DLL");
		}

		if(isEmpty())
		{
			throw new EmptyCollectionException("DLL");
		}




		ListIterator<T> lit =  listIterator();
		boolean notFound = true;
		T retVal = null;
		while(lit.hasNext() && notFound)
		{

			retVal = lit.next();
			if(retVal.equals(element))
			{
				notFound = false;
				lit.remove();
			}
		}
		if(notFound)
		{
			throw new ElementNotFoundException("DLL");
		}


		if(count == 0)
		{
			head = tail = null;
		}

		return retVal;
	}

	/**
	 * This method returns the first element of the list
	 * @return Element at the head
	 */
	@Override
	public T first() {

		if(isEmpty())
		{
			throw new EmptyCollectionException("DLL");
		}
		return head.getElement();
	}

	/**
	 * This method returns the last element in the list
	 * @return Element at the tail
	 */
	@Override
	public T last() {

		if(isEmpty())
		{
			throw new EmptyCollectionException("DLL");
		}
		return tail.getElement();
	}

	/**
	 * This method takes in a target and checks the entire list to
	 * see if the list has that specific element.
	 * @param target that is being looked for
	 * @return True or false depending on if the target is found or not 
	 */
	@Override
	public boolean contains(T target) {
		boolean foundIt = false;
		if(isEmpty())
		{
			return false;
		}

		ListIterator<T> lit = listIterator();

		while(lit.hasNext() && !foundIt)
		{
			if(lit.next() == target)
			{
				foundIt = true;
			}
			else
			{
				foundIt = false;
			}
		}
		return foundIt;
	}

	/**
	 * This method checks to see if the list is empty.
	 * @return True or false depending on if it's empty
	 */
	@Override
	public boolean isEmpty() {

		return size() == 0;
	}

	/**
	 * This method returns the size of the element
	 * @return size of the element
	 */
	@Override
	public int size() {
		return count;
	}


	/**
	 * A call to this method creates a new 
	 * iterator for a DoubleLinkedList
	 */
	@Override
	public Iterator<T> iterator() {

		return new DLLIterator();
	}

	/**
	 * Takes in an index and an element and adds
	 * the element to the specific index given
	 * @param index where the element is being added
	 * @param element that is being added
	 * 
	 */
	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("DLL");
		}
		
		ListIterator<T> lit = listIterator(index);

		lit.add(element);

	}

	/**
	 * Takes in an index and an element and sets
	 * the element where the specific index is
	 * in the list
	 * @param index where the element is being set
	 * @param element that is being set in the list
	 */
	@Override
	public void set(int index, T element) {

		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("DLL");
		}

		if(isEmpty() && index == 0)
		{
			LinearNode<T> newNode = new LinearNode<T>(element);
			head = tail = newNode;
		}

		else if(index == 0)
		{
			head.setElement(element);
		}

		else
		{
			ListIterator<T> lit = listIterator(index);
			lit.next();
			lit.set(element);
		}

		modCount++;




	}

	/**
	 * Takes in an element and adds it to the tail
	 * of the list
	 * @param element that is being added to the end
	 */
	@Override
	public void add(T element) {

		addToRear(element);



	}

	/**
	 * Takes in an index and returns the element
	 * at that specific index
	 * @param index of the element that the method is returning
	 * @return the element at the index given
	 */
	@Override
	public T get(int index) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("DLL");
		}

		ListIterator<T> lit = listIterator(index);

		return lit.next();
	}

	/**
	 * Takes in an element and returns the index
	 * of that specific element
	 * @param element that the method is trying to 
	 * find the index of
	 * return int index
	 */
	@Override
	public int indexOf(T element) {

		int index = -1;
		if(contains(element) == false)
		{
			index = -1;
			return index;
		}
		if(isEmpty())
		{
			throw new EmptyCollectionException("DLL");
		}

		ListIterator<T> lit = listIterator();
		boolean notFound = true;
		while(lit.hasNext() && notFound)
		{
			if(lit.next().equals(element))
			{
				notFound = false;
				index = lit.previousIndex();
			}
		}
		return index;
	}

	
	/**
	 * Takes in a specific index and removes the 
	 * element at that location
	 * @param index of the element being removed
	 * @return element that was removed
	 */
	@Override
	public T remove(int index) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("DLL");
		}


		ListIterator<T> lit = listIterator(index);
		T retVal = lit.next();
		lit.remove(); 
		return retVal;

	}

	/**
	 * This method returns a DoubleLinkedList iterator 
	 * from a ListIterator
	 * @return DoubleLinkedList iterator starting at the 
	 * first element in the list
	 */
	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	/**
	 * This method takes in a starting index and returns
	 * a DoubleLinkedList iterator starting at that index
	 * @param index that the iterator starts at
	 * @return DoubleLinkedList iterator at certain location
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}
	/**
	 * XXX ListIterator Implementation
	 * 
	 * This implementation of a list iterator adds all of the ListIterator methods from the API
	 * to the normal iterator class. This class is necessary for the complete use of a functioning 
	 * Double Linked List.
	 * 
	 * @author lwestmoreland
	 *
	 */
	private class DLLIterator implements ListIterator<T> {

		private int nextIndex;
		private LinearNode<T> nextNode;
		private LinearNode<T> lastReturned;
		private int iterModCount;
		private boolean canRemove = false;
		private boolean wasNext = false;
		private boolean calledNext = false;
		private boolean calledPrev = false;




		/**
		 * Creates a DLL iterator and starts it
		 * at the first element of the list
		 */
		public DLLIterator()
		{
			this(0);
		}

		/**
		 * Takes in a starting index and creates a DLL
		 * iterator at the given index of the list
		 * @param startingIndex
		 */
		public DLLIterator (int startingIndex)
		{


			if(startingIndex < 0 || startingIndex > size())
			{
				throw new IndexOutOfBoundsException("ListIterator");
			}
			nextIndex = 0;
			nextNode = head;
			iterModCount = modCount;
			canRemove = false;




			while(nextIndex < startingIndex)
			{
				next();
				canRemove = false;


			}


		}


		/**
		 * Takes in an element and adds it to the 
		 * spot that the list iterator is pointing at
		 * @param element that is being added
		 */
		@Override
		public void add(T element) {



			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLLIterator");
			}

			if(isEmpty())
			{
				LinearNode<T> newNode = new LinearNode<T>(element);
				head = tail = newNode;
			}

			if(calledNext == true)
			{


				LinearNode<T> newNode = new LinearNode<T>(element);

				if(nextNode == null)
				{
					tail.setNext(newNode);
					newNode.setPrev(tail);
					tail = newNode;
				}
				else if(nextNode == tail)
				{
					nextNode.getPrev().setNext(newNode);
					newNode.setPrev(nextNode.getPrev());

					nextNode.setPrev(newNode);
					newNode.setNext(nextNode);
				}
				else
				{
					nextNode.getPrev().setNext(newNode);
					newNode.setPrev(nextNode.getPrev());
					nextNode.setPrev(newNode);
					newNode.setNext(nextNode);
				}


			}

			else if(calledPrev == true)
			{
				LinearNode<T> newNode = new LinearNode<T>(element);
				if(nextNode == null)
				{
					head.setPrev(newNode);
					newNode.setNext(head);
					head = head.getPrev();
				}
				
				else if (size() == 1)
				{
					head.setPrev(newNode);
					newNode.setNext(head);
					head = newNode;
				}

				else
				{
					nextNode.getPrev().setNext(newNode);
					newNode.setPrev(nextNode.getPrev());
					nextNode.setPrev(newNode);
					newNode.setNext(nextNode);
				}




			}
			else if(nextNode == head)
			{
				LinearNode<T> newNode = new LinearNode<T>(element);
				head.setPrev(newNode);
				newNode.setNext(head);
				head = newNode;
			}

			count++;
			modCount++;
			nextIndex++;
			iterModCount++;
			calledNext = false;
			calledPrev = false;
			canRemove = false;






		}

		/**
		 * Checks the location of the iterator and determines
		 * if there is an element next to the current location
		 * @return boolean value depending on if there is a next value
		 */
		@Override
		public boolean hasNext() {
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator");
			}
			return nextNode != null;
		}

		/**
		 * Checks the location of the iterator and determines
		 * if there is an element previous to the current location
		 * @return boolean value depending on if there is a previous value
		 */
		@Override
		public boolean hasPrevious() {
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator");
			}
			return nextNode != head;
		}

		/**
		 * Moves the iterator from its current location to the 
		 * next element in the list
		 * @return the element that the iterator is pointing at
		 * after its movement
		 */
		@Override
		public T next() {

			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL");
			}
			if(!hasNext())
			{
				throw new NoSuchElementException("ListIterator");
			}

			T retVal = nextNode.getElement();
			lastReturned = nextNode;

			nextNode = nextNode.getNext();//WHY CANT I DO THIS



			nextIndex++;
			canRemove = true;
			wasNext = true;
			calledPrev = false;
			calledNext = true;
			return retVal;
		}

		/**
		 * Returns the index of the element that the 
		 * iterator is pointing at
		 * @return int index that the nextNode is located at
		 */
		@Override
		public int nextIndex() {

			return nextIndex;
		}

		/**
		 * Moves the iterator from its current location to the 
		 * previous element in the list
		 * @return the element that the iterator is pointing at
		 * after its movement
		 */
		@Override
		public T previous() {
			if(!hasPrevious())
			{
				throw new NoSuchElementException("ListIterator");
			}
			T retVal;

			if(nextNode == null)
			{
				retVal = tail.getElement();
				lastReturned = tail;
				nextNode = tail;
			}
			else{
				retVal = nextNode.getPrev().getElement();
				lastReturned = nextNode.getPrev();
				nextNode = nextNode.getPrev();
			}
			nextIndex++;
			canRemove = true;
			wasNext = false;
			calledPrev = true;
			calledNext = false;
			return retVal;
		}

		/**
		 * Returns the index of the element before the
		 * one that the iterator is pointing at
		 * @return int index that the element before the nextNode is located at
		 */
		@Override
		public int previousIndex() {
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator");
			}
			return nextIndex-1;
		}

		/**
		 * Removes the element that the iterator is currently
		 * located at. 
		 * (cannot be called unless next() or previous() is called prior)
		 */
		@Override
		public void remove() {
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator");
			}
			if(!canRemove)
			{
				throw new IllegalStateException("ListIterator");
			}

			if(size() == 1)
			{
				head = tail = null;
			}



			else if(calledNext == true)
			{


				if(hasNext() == false)
				{
					tail = tail.getPrev();
					tail.setNext(null);
				}
				if(hasNext())
				{
					if(lastReturned == tail)
					{
						tail = lastReturned.getPrev();
					}
					if(lastReturned == head)
					{
						head = head.getNext();
						head.setPrev(null);
					}

					else 
					{
						lastReturned.getPrev().setNext(lastReturned.getNext());
						lastReturned.getNext().setPrev(lastReturned.getPrev());
					}

				}

				else if(hasNext())
				{

				}
			}





			else if(calledPrev == true)
			{
				LinearNode<T> prevNode = new LinearNode<T>(previous());
				LinearNode<T> tempNode = head;
				while(!tempNode.getElement().equals(prevNode.getElement()))
				{
					tempNode = tempNode.getNext();
				}
				if(tempNode.equals(tail))
				{
					tail = tempNode.getPrev();
					tail.setNext(null);

				}
				else
				{
					tempNode.getPrev().setNext(tempNode.getNext());
					tempNode.getNext().setPrev(tempNode.getPrev());
				}
			}
			count--;
			modCount++;
			iterModCount++;
			nextIndex--;
			canRemove = false;
		}

		/**
		 * Takes in an element and sets it to the spot
		 * in the list that the iterator is pointing at
		 * @param element that is being set
		 */
		@Override
		public void set(T element) {
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator");
			}


			if(canRemove == false)
			{
				throw new IllegalStateException("DLLIterator");
			}

			if(isEmpty())
			{
				throw new EmptyCollectionException("DLLIterator");
			}



			if(wasNext == true)
			{
				if(nextNode == head)
				{
					head.setElement(element);

				}

				else if(nextNode == null)
				{
					tail.setElement(element);
				}

				else
				{
					nextNode.setElement(element); //in the middle of list
				}

			}
			if(wasNext == false)
			{
				if(nextNode == null)
				{
					head.setElement(element);

				}

				else if(nextNode == tail)
				{
					tail.setElement(element);
				}

				else
				{
					nextNode.setElement(element);
				}
			}



			canRemove = false;
			modCount++;
			iterModCount++;
		}

	}

}
