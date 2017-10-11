/**
 * SingleLinkedList represents a LinearNode-based implementation of both an unordered and indexed list.
 *
 * @author Java Foundations, 
 * @version 4.0
 */
public class SingleLinkedList<T> extends AbstractLinkedList<T> implements IndexedUnorderedListADT<T>
{	


	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToFront(T element)
	{
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(head == null)
		{
			head = tail = newNode;
		}
		else if (tail == head)
		{
			newNode.setNext(head);
			head = newNode;
		}

		else
		{

			newNode.setNext(head);
			head = newNode;
		}

		count++;
		modCount++;

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToRear(T element)
	{
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(tail == null)
		{
			head = newNode;
		}
		else
		{
			tail.setNext(newNode);
		}
		tail = newNode;
		count++;
		modCount++;
	}	

	/**
	 * Adds the specified element to this list after the given target.
	 *
	 * @param  element the element to be added to this list
	 * @param  target the target element to be added after
	 * @throws ElementNotFoundException if the target is not found
	 */
	public void addAfter(T element, T target)
	{
		LinearNode<T> current = head;
		while(current != null && !current.getElement().equals(target))
		{
			current = current.getNext();
		}
		if(current == null)
		{
			throw new ElementNotFoundException("Linked List");
		}
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		if(current == tail)
		{
			tail = newNode;
		}
		count++;
		modCount++;
	}

	/**  
	 * Inserts the specified element at the specified index. 
	 * 
	 * @param index   the index into the array to which the element is to be
	 *                inserted.
	 * @param element the element to be inserted into the array   
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void add(int index, T element) {
		LinearNode<T> current = head;
		if(index > size() || index < 0)
		{
			throw new IndexOutOfBoundsException("Linked List");
		}
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(size() == 0)
		{
			head = tail = newNode;
		}

		else if(index == 0)
		{
			newNode.setNext(head);
			head = newNode;
		}
		else
		{
			int i = 0;
			while(i < index-1 && current != null)
			{
				current = current.getNext();
				i++;
			}


			if(index == size())
			{
				tail.setNext(newNode);
				tail = newNode;
			}

			else
			{
				newNode.setNext(current.getNext());
				current.setNext(newNode);
			}

		}
		count++;
		modCount++;
	}



	/**  
	 * Adds the specified element to the rear of this list. 
	 *
	 * @param element  the element to be added to the rear of the list    
	 */
	public void add(T element) {
		addToRear(element);
	}

	/**  
	 * Sets the element at the specified index. 
	 *
	 * @param index   the index into the array to which the element is to be set
	 * @param element the element to be set into the list
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void set(int index, T element) {

		if(index > size() || index < 0)
		{
			throw new IndexOutOfBoundsException("Linked List");
		}
		if(index == size())
		{
			count++;
		}
		LinearNode<T> current = head;
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(size() == 0)
		{

			head = tail = newNode;
		}
		else if(index == 0)
		{
			newNode.setNext(head);
			head = newNode;
		}
		else
		{
			int i = 0;
			while(current != null && i != index)
			{
				current = current.getNext();
				i++;
			}
			current = newNode;
		}






		modCount++;


	}

	/**  
	 * Returns a reference to the element at the specified index. 
	 *
	 * @param index  the index to which the reference is to be retrieved from
	 * @return the element at the specified index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T get(int index) {
		if(index < 0 || index > count - 1)
		{
			throw new IndexOutOfBoundsException("LinkedList");
		}
		LinearNode<T> current = head;

		if(index == 0)
		{
			return head.getElement();
		}

		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		return current.getElement();
	}

	/**  
	 * Returns the index of the specified element. 
	 *
	 * @param element  the element for the index is to be retrieved
	 * @return the integer index for this element or -1 if element is not in the list
	 */
	public int indexOf(T element) {
		int index = -1;

		LinearNode<T> current = head;
		int idx = 0;
		while(current != null && !current.getElement().equals(element))
		{
			current = current.getNext();
			idx++;
		}
		if(current != null)
		{
			index = idx;
		}


		return index;
	}

	/**  
	 * Returns the element at the specified element. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T remove(int index) {
		{
			if(index < 0 || index > count - 1)
			{
				throw new IndexOutOfBoundsException("LinkedList");
			}


			LinearNode<T> previous = null;
			LinearNode<T> current = head;
			T retVal = head.getElement();

			if (size() == 1)  // only one element in the list
			{
				head = tail = null;
				count--;
				modCount++;
				return retVal;
			}
			for(int i = 0; i < index; i++)
			{
				previous = current;
				current = current.getNext();
				retVal = current.getElement();
			}







			if (current.equals(head))  // target is at the head
			{
				retVal = head.getElement();
				head = current.getNext();
			}
			else if (current.equals(tail))  // target is at the tail
			{
				retVal = current.getElement();
				tail = previous;
				tail.setNext(null);
			}
			else  // target is in the middle
				previous.setNext(current.getNext());

			count--;
			modCount++;

			return retVal;
		}
	}
}
