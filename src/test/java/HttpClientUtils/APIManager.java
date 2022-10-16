package HttpClientUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class APIManager {
	

	private CloseableHttpClient client;
	private CloseableHttpResponse response;
	private HttpPost httpPost;
	private HttpPatch httpPatch;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private int statusCode;
	private String statusText;
	private String responsejson;
	//private JsonObject jobj;
	private JsonElement jelm;
	
	public APIManager()
	{
		this.client = HttpClients.createDefault();  
	}

	public void httpGET(String SAMPLE_URL)
	{
		try {
			this.response = client.execute(new HttpGet(SAMPLE_URL));
			this.statusCode = response.getStatusLine().getStatusCode();
			this.statusText = response.getStatusLine().getReasonPhrase();
			this.responsejson = EntityUtils.toString(this.response.getEntity());
			this.jelm = JsonParser.parseString(this.responsejson);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void httpPOST(String SAMPLE_URL)
	{
		try {
			this.httpPost = new HttpPost(SAMPLE_URL);
		    this.params.add(new BasicNameValuePair("username", "John"));
		    this.params.add(new BasicNameValuePair("password", "pass"));
		    this.httpPost.setEntity(new UrlEncodedFormEntity(this.params));

		    CloseableHttpResponse response = this.client.execute(httpPost);
			
			
			response = this.client.execute(new HttpPost(SAMPLE_URL));
			this.statusCode = response.getStatusLine().getStatusCode();
			this.statusText = response.getStatusLine().getReasonPhrase();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void httpPOST(String SAMPLE_URL, String json)
	{
		try {
			this.httpPost = new HttpPost(SAMPLE_URL);
			StringEntity entity = new StringEntity(json);
			this.httpPost.setEntity(entity);
			this.httpPost.setHeader("Accept", "application/json");
			this.httpPost.setHeader("Content-type", "application/json");
		    this.response = client.execute(this.httpPost);
			this.statusCode = this.response.getStatusLine().getStatusCode();
			this.statusText = this.response.getStatusLine().getReasonPhrase();
			this.responsejson = EntityUtils.toString(this.response.getEntity());
			this.jelm = JsonParser.parseString(this.responsejson);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void httpPATCH(String SAMPLE_URL, String json)
	{
		try {
			this.httpPatch = new HttpPatch(SAMPLE_URL);
			StringEntity entity = new StringEntity(json);
			this.httpPatch.setEntity(entity);
			this.httpPatch.setHeader("Accept", "application/json");
			this.httpPatch.setHeader("Content-type", "application/json");
		    this.response = client.execute(this.httpPatch);
			this.statusCode = this.response.getStatusLine().getStatusCode();
			this.statusText = this.response.getStatusLine().getReasonPhrase();
			this.responsejson = EntityUtils.toString(this.response.getEntity());
			this.jelm = JsonParser.parseString(this.responsejson);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getStatusCode()
	{
		return String.valueOf(this.statusCode);
	}
	
	public String getStatusText()
	{
		return this.statusText;
	}
	
	public String getResponseJSON()
	{
		return this.responsejson;
	}
	
	public boolean compareJsonResponse(String json)
	{
		boolean res = false;
		JsonElement jelm1 = JsonParser.parseString(json);
		res = this.jelm.equals(jelm1);
		return res;
	}

}
