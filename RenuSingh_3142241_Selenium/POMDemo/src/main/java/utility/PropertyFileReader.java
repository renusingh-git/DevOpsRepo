package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

	public static String getValue = "";

	public String getData(String val) {
//		File file = new File("C:\\Users\\renu2143\\Downloads\\PageFactory\\POMDemo\\src\\main\\resources\\config.properties");
		
//		FileInputStream fis = null;
		Properties prop = null;
		try {
//			fis = new FileInputStream(file);
			prop = new Properties();
			InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
			
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getValue = prop.getProperty(val);
		return getValue;
	}
}
