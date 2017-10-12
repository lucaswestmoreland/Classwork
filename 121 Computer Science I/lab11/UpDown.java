import javax.swing.JFrame;



public class UpDown{
	
	
	
	
	
public static void main(String[] args) {	

		
	      JFrame frame = new JFrame("UpDown");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      frame.getContentPane().add(new UpDownPanel());

	      frame.pack();
	      frame.setVisible(true);
		
		
		
		
		
		

	}

}
