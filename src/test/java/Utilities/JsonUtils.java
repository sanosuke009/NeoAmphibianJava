package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.reporters.Files;

import com.google.gson.Gson;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> readJson(String path)
	{
		Map<String,String> map = new HashMap<String, String>();
		try
		{
			Gson gson = new Gson();
			File jf = new File(FileUtilities.abs(path));
			InputStream jf_inputstream = new FileInputStream(jf);
			String jsonString = new String(Files.readFile(jf_inputstream));
			map = gson.fromJson(jsonString, Map.class);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	

}
