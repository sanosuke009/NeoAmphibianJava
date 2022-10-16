package Utilities;

public class FileUtilities {
	
	public synchronized static String abs(String path)
	{
		try
		{
			return totalPath(System.getProperty("user.dir"), path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public synchronized static String totalPath(String path1, String path2)
	{
		try
		{
			return path1.endsWith("/")?path1+path2:path1+"/"+path2;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public synchronized static String totalAbsPath(String path1, String path2)
	{
		try
		{
			return abs(totalPath(path1, path2));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
