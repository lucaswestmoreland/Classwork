import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Parse {

	private String word;
	private String current;
	private StringTokenizer tokenizer;
	private BufferedReader bufferedReader;

	public Parse(String file) throws IOException {

		if (file == null) {
			throw new IOException("Invalid input, probably your file name.");

		} else {
			FileReader fileRead = new FileReader(new File(file));
			bufferedReader = new BufferedReader(fileRead);
		}
		nextLine();
	}

	private void nextLine() throws IOException {
		int n = 0;
		while (n == 0) {
			current = bufferedReader.readLine();
			tokenizer = current != null ? tokenizer = new StringTokenizer(current)
			: new StringTokenizer("");
			if (tokenizer.hasMoreTokens() || current == null)
				n = 1;
		}

		word = current != null ? tokenizer.nextToken() : "";
	}
	public String nextWord() throws IOException {
		String str = word;
		if (!tokenizer.hasMoreTokens()) {
			nextLine();            
		} else {
			word = tokenizer.nextToken();
		}
		return str;
	}

	public boolean hasWord() {
		return current != null;
	}

}