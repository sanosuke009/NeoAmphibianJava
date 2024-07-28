package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.reporters.Files;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> readJson(String path)
	{
		Map<String,String> map = new HashMap<>();
		try
		{
			Gson gson = new Gson();
			File jf = new File(FileUtilities.abs(path));
			InputStream jf_inputstream = new FileInputStream(jf);
			String jsonString = Files.readFile(jf_inputstream);
			map = gson.fromJson(jsonString, Map.class);
		}
		catch(JsonSyntaxException | IOException e)
		{
			e.printStackTrace();
		}
		return map;
	}
	

}
