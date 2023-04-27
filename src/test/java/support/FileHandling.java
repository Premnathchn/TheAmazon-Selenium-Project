package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>File to handle or retrieve the values from properties file
 * <h1>
 * 
 * @author Premnath Ayyadurai
 * @since 20 April 2023
 *
 */
public class FileHandling {

	/**
	 * This method helps to retrieve a test-data from 'config.properties' file
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(String key) throws Exception {
		String value = "";
		try {
			Properties objProperty = new Properties();
			File fileObj = new File("Resources/config.properties");

			InputStream inputStream = new FileInputStream(fileObj);
			objProperty.load(inputStream);

			value = (String) objProperty.get(key);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("facing issue while fetching property value for: "+key);
		}
		return value;
	}

}
