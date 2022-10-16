package TestManagers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import BaseClasses.BaseC;
import Utilities.FileUtilities;

public class ConfigManager extends BaseC{
	
	public ConfigManager(String path)
	{
		try {
			in = new FileInputStream(FileUtilities.abs(path));
			prop.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConfigManager()
	{
		try {
			in = new FileInputStream(FileUtilities.abs("Configurations/config.properties"));
			prop.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized String getConfig(String key)
	{
		try {
			return prop.getProperty(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
