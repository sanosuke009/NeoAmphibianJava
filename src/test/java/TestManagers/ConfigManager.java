package TestManagers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Utilities.FileUtilities;

public class ConfigManager extends ReportManager{
	
	public ConfigManager(String path)
	{
		try {
			in = new FileInputStream(FileUtilities.abs(path));
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConfigManager()
	{
		try {
			in = new FileInputStream(FileUtilities.abs("Configurations/config.properties"));
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String getConfig(String key)
	{
		try {
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
