package maven.itbc.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Locators {
	
	//Singleton class that is used for storing page locators in a hashmap 

	public static HashMap<String, String> map;
	private static Locators instance = null;

	private Locators() {
		

		map = new HashMap<String, String>();

		File locFile = new File("src\\main\\resources\\WPLocators.txt");
		try {

			BufferedReader br = new BufferedReader(new FileReader(locFile));

			String line;
			String[] arrtxt;
			while ((line = br.readLine()) != null) {
				arrtxt = line.split("%");
				map.put(arrtxt[0], arrtxt[1]);

			}
		br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public static Locators getInstance() {
		
		if (instance == null)
			instance = new Locators();
		
		return instance;
		
	}
	

}
