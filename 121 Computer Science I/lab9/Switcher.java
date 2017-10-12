


public class Switcher {
	
	public static boolean switchThem(boolean[] one, boolean[] two)
	{
		if (one.length != two.length)
		{
			return false;
		}
		
		else
		{
			boolean[] temp = one;
			one = two;
			two = temp;
			return true;
			
		}
		
	}

}
