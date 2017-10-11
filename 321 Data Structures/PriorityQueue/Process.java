public class Process implements Comparable<Process> {

	private int priorityLevel, timeNotProcessed, time, proccessTime;

	public Process(int currentTime, int maxProccessTime, int level) {
		time = currentTime;
		proccessTime = maxProccessTime;
		priorityLevel = level;
		timeNotProcessed = 1;

	}

	@Override
	public int compareTo(Process o) 
	{
		if (this.priorityLevel == o.priorityLevel) 
		{
			if (this.time < o.time) 
			{
				return 1;
			} 
			else 
			{
				return -1;
			}
		} 
		else 
		{
			if (this.priorityLevel > o.priorityLevel) 
			{
				return 1;
			} 
			else 
			{
				return -1;
			}
		}

	}

	public boolean finish() 
	{
		if (proccessTime == 0) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}


	public int getPriority() 
	{
		return priorityLevel;
	}

	public int getTimeRemaining() 
	{
		return proccessTime;
	}

	public void reduceTimeRemaining() 
	{
		proccessTime--;

	}

	public int getTimeNotProcessed() 
	{
		return timeNotProcessed;
	}

	public void increasePriorityLevel() 
	{
		priorityLevel++;
	}

	public void resetTimeNotProcessed() 
	{
		timeNotProcessed = 1;

	}

	public int getArrivalTime() 
	{
		return time;
	}







	public void increaseTimeNotProcessed() 
	{
		timeNotProcessed++;
	}

}
