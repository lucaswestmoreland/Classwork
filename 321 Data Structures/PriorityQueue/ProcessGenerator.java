import java.util.Random;

public class ProcessGenerator {

	Random rand = new Random();
	private double probability;


	public ProcessGenerator(double probability) 
	{

		this.probability = probability;
	}

	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) 
	{
		int priorityLevel;
		int processTime;

		processTime = rand.nextInt(maxProcessTime);
		priorityLevel = rand.nextInt(maxLevel);
		return new Process(currentTime, processTime, priorityLevel);

	}

	public boolean query() 
	{

		if (probability > rand.nextDouble()) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}


}