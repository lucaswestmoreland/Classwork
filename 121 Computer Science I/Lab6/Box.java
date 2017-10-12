import java.text.DecimalFormat;


public class Box {
	
	private double width;
	private double height;
	private double depth;
	
	private boolean isFull;
	
	DecimalFormat fmt = new DecimalFormat("##.##");
	/** Initializes the variables width, height, and depth.
	 * Sets full to false. Constructor.
	 * 
	 * @param width
	 * @param height
	 * @param depth
	 */
	public Box(double width, double height, double depth)
	{
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.isFull = false;

	}
	/**
	 * This returns the width
	 * @return width
	 */
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * This returns the height
	 * @return height
	 */
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * This returns the depth
	 * @return depth
	 */
	public double getDepth()
	{
		return depth;
	}
	
	/**
	 * This returns if it's full.
	 * @return isFull
	 */
	public boolean getFull()
	{
		return isFull;
	}
	/**
	 * This sets the width.
	 * @param width
	 */
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	/**
	 * This sets the height.
	 * @param height
	 */
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	/**
	 * This sets the depth.
	 * @param depth
	 */
	public void setDepth(double depth)
	{
		this.depth = depth;
	}
	
	/**
	 * This sets if it's full 
	 * @param full
	 */
	public void setFull(boolean full)
	{
		isFull = full;
	}
	
	/**
	 * Calculates volume then returns value.
	 * @return volume
	 */
	public double volume()
	{
		return (depth * width * height);
	}
	
	/**
	 * Calculates surface area then returns value.
	 * @return surface area
	 */
	public double surfaceArea()
	{
		return (2 * width * height) + (2 * height * depth)
				+ (2 * depth * width);
	}
	
	/**
	 * Returns String
	 */
	public String toString()
	{
		String myBox = "Width = " + fmt.format(getWidth()) + ". Height = " + fmt.format(getHeight()) + ". Depth = " 
				+ fmt.format(getDepth()) + ". Volume = " + fmt.format(volume()) + ". Surface Area = " + fmt.format(surfaceArea())
				+ ". Box full? = " + isFull;
		return myBox;
	}
}