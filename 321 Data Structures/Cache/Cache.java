import java.util.LinkedList;


public class Cache<T> {
	
	private LinkedList<T> cache;
	private int maxStorage;
	private int hits;
	
	/**
	 * Constructor that creates the cache
	 * @param size
	 */
	public Cache(int size) {
		
		cache = new LinkedList<T>();
		maxStorage = size;
	}
	
	/**
	 * This method takes in an object and locates it in the cache
	 * @param object
	 * @return
	 */
	public T getObject(T object) {
		
		if(cache.contains(object) == false) //if not in the cache
		{
			System.out.println("Element not found.");
			return null;
		}
		else
		{
			cache.get(cache.indexOf(object));
			return object;
			
		}
		 
	}
	/**
	 * This method adds an object to the cache. Depending on if the capacity has
	 * been reached, the method will manipulate the cache accordingly.
	 * @param object
	 */
	public void addObject(T object)
	{
		if(cache.size() == maxStorage) //if full
		{
			if(cache.contains(object)) //if full and already in cache
			{
				cache.remove(object);
				cache.addFirst(object);
				hits++;
				
			}
			else //if full but not in cache
			{
				cache.removeLast();
				cache.addFirst(object);
			}
		}
		
		else if(cache.size() < maxStorage) //if there is still room in the cache
		{
			if(cache.contains(object)) //if object is already in cache
			{
				cache.remove(object);
				cache.addFirst(object);
				hits++;
			}
			else //if object is not already in cache
			{
				cache.addFirst(object);
			}
		}
	}
	
	/**
	 * This method removes the object from the cache
	 * @param element
	 */
	public void removeObject(T object) {
		if(cache.contains(object) == false) //if not found in cache
		{
			System.out.println("Object not found.");
		}
		
		else
		{
			cache.remove(cache.indexOf(object));
		}
		
	}
	
	/**
	 * This method takes in a cache and clears it.
	 * @param Cache
	 */
	private void clearCache(T Cache) {
		
		cache.clear();
		
	}
	
	/**
	 * This method returns the total number of hits. This becomes useful when 
	 * micro-managing the hits that occur within each cache.
	 * @return hits
	 */
	public int numHits() {
		return hits;
	}


}
