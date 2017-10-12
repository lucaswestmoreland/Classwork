import java.io.File;


public class ProcessText {
	public static void main(String [] args){
		
		File[] files = new File[args.length];
		
		 
		
		 if (args.length <1) {
	            System.err.println("Usage: java ProcessText file1 [file2 ...]");
	            System.exit(1);
	        }
		 


			for(int i=0;i<args.length ;i++){
				// The name of the file which we will read from
	            String filename = args[i];
	            // Prepare to read from the file, using a Scanner object
	            File file = new File(filename);	
	            files[i] = file;
	            
	           TextStatistics newStats= new TextStatistics(file) ;

	           System.out.println(newStats);	    
			}
			 		
	}
	
}