public class PQueue {

	private MaxHeap pQueue;

	public PQueue() 
	{
		pQueue = new MaxHeap();
	}

	public void update(int timeToIncrementPriority, int max) 
	{
		pQueue.increasePriority(timeToIncrementPriority, max);

	}

	public boolean isEmpty() 
	{

		return pQueue.getSize() == 0;
	}

	public void enPQueue(Process p) 
	{
		pQueue.insert(p);

	}

	public Process dePQueue() 
	{
		return pQueue.heapExtractMax();

	}





}

