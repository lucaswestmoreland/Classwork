import java.util.ArrayList;

public class MaxHeap {

	private ArrayList<Process> heap;
	private int largest;

	public MaxHeap() 
	{

		heap = new ArrayList<Process>();

	}

	public void maxHeapifyDown(int idx) 
	{

		int left = leftChild(idx);
		int right = rightChild(idx);

		if (left < heap.size() && heap.get(left).compareTo(heap.get(idx)) > 0) 
		{
			largest = left;
		} 
		else 
		{
			largest = idx;
		}

		if (right < heap.size() && heap.get(right).compareTo(heap.get(largest)) > 0) 
		{
			largest = right;
		}

		if (largest != idx) 
		{
			swap(idx, largest);
			maxHeapifyDown(largest);
		}
	}

	public void maxHeapifyUp(int idx) 
	{

		while (idx > 0 && heap.get(parent(idx)).compareTo(heap.get(idx)) < 0) {
			swap(parent(idx), idx);
			idx = parent(idx);

		}

	}

	public void buildMaxHeap(ArrayList<Process> h) 
	{

		for (int i = (heap.size() / 2); i >= 0; i--) 
		{
			maxHeapifyDown(i);
		}
	}

	public Process heapExtractMax() 
	{

		if (heap.size() < 1) {
			System.out.println("Heap is empty. ERROR");
		}

		Process returnProcess = heap.get(0);

		swap(0, heap.size() - 1);
		heap.remove(heap.get(heap.size() - 1));
		maxHeapifyDown(0);

		return returnProcess;
	}


	public int getSize() 
	{
		return heap.size();
	}

	public int parent(int location) 
	{
		int value = ((location - 1) / 2);
		return value;
	}

	public int leftChild(int location) 
	{
		int value = ((2 * location) + 1);
		return value;
	}

	public int rightChild(int location) 
	{
		int value = ((2 * location) + 2);
		return value;
	}

	public void swap(int location1, int location2) 
	{

		Process tmp;

		tmp = heap.get(location1);
		heap.set(location1, heap.get(location2));
		heap.set(location2, tmp);
	}

	public void insert(Process e) 
	{

		heap.add(e);
		maxHeapifyUp(heap.indexOf(e));
	}


	public void increasePriority(int incTime, int maxLevel) 
	{

		int value;
		int timeNotProccessed;

		for (int i = 0; i < heap.size(); i++) 
		{
			value = heap.get(i).getPriority();
			timeNotProccessed = heap.get(i).getTimeNotProcessed();
			if (value < maxLevel && (incTime == timeNotProccessed)) 
			{
				heap.get(i).increasePriorityLevel();
				heap.get(i).resetTimeNotProcessed();
				maxHeapifyUp(i);
			} 
			else 
			{
				heap.get(i).increaseTimeNotProcessed();
			}
		}
	}

}