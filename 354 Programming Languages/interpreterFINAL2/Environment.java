// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.
import java.util.Map;
import java.util.HashMap;


public class Environment {

	Map<String,Double> map = new HashMap<String, Double>();	//I ended up using a HashMap as opposed to your suggestion of using a HashSet. Still seems to work.

	/*
	 * Puts the variable and the value into the environment
	 *
	 * @param String variable
	 * @param double value
	 * @return double val
	 */
	public double put(String var, double val){
		map.put(var,val);
		return val; 
	}
	
	
	/*
		* Returns a value and its position
		*
		* @param int position
		* @param String variable
		* @return double 
		* @throws EvalException 
		*/ 
    public double get(int pos, String var) throws EvalException{ 
    	return map.get(var);
	}
    
}
