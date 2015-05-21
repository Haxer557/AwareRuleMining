package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private final Properties properties;
	public PropertyReader(String path) throws IOException {
		properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
		if(inputStream != null)
			properties.load(inputStream);
		else
			throw new FileNotFoundException("Nie znaleziono pliku o œcie¿ce "+path);
	}
	
	public String getString(String property) {
		return properties.getProperty(property);
	}
	
	public double getDouble(String property) {
		return Double.parseDouble(getString(property));
	}
	
	public long getLong(String property) {
		return Long.parseLong(getString(property));
	}
	
	public int getInt(String property) {
		return Integer.parseInt(getString(property));
	}
}
