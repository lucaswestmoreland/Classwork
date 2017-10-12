import java.text.DecimalFormat;
import java.util.Random;


public class BoxTest {
	static DecimalFormat fmt = new DecimalFormat("##.##");

	public static void main(String[] args) {
	Box myBox = new Box(4, 5, 2);
	
	System.out.println(myBox.toString());
	
	System.out.println(myBox.getWidth());
	System.out.println(myBox.getHeight());
	System.out.println(myBox.getDepth());
	
	System.out.println(myBox.volume());
	System.out.println(myBox.surfaceArea());
	
	myBox.setWidth(2);
	myBox.setHeight(3);
	myBox.setDepth(1);
	myBox.setFull(true);
	
	System.out.println(myBox.toString());
	
	System.out.println(myBox.getWidth());
	System.out.println(myBox.getHeight());
	System.out.println(myBox.getDepth());
	
	System.out.println(myBox.volume());
	System.out.println(myBox.surfaceArea());
	
	Random rand = new Random();
	
	Box bigBox = new Box(1,1,1);
	
	for(int i = 1; i  <= 5; i++)
	{
		double randWidth = rand.nextDouble()*50;
		double randHeight = rand.nextDouble()*50;
		double randDepth = rand.nextDouble()*50;
		boolean randBoolean = rand.nextBoolean();
		
		Box randBox = new Box(randWidth, randHeight, randDepth);
		randBox.setFull(randBoolean);
		
		System.out.println("Box #" + i + ")" + randBox.toString());
		
		if(randBox.volume() > bigBox.volume())
		{
			bigBox = randBox;
		}
	}
	 double bbVolume = bigBox.volume();
	 
	System.out.println("Biggest box = " + bigBox.toString());
	System.out.println("It's volume = " + fmt.format(bbVolume));
	
	}

}
