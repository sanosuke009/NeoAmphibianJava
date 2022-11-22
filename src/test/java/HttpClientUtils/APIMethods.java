package HttpClientUtils;

import BaseClasses.DataInjection;

public class APIMethods {
	DataInjection di;
	public static boolean validateGet(DataInjection b)
	{
		boolean res = true;
		/*APIManager ap = new APIManager(); String url = b.get("URL");String sc = b.get("StatusCode");
		String sct = b.get("StatusText");String sj = b.get("StatusJSON");
		if(res) ap.httpGET(url);
		if(res) res = ap.getStatusCode().equals(sc);
		if(res) b.report("The status code for GET of "+url+" is "+sc+" which is expected.");
		else b.report("The status code for GET of "+url+" is "+ap.getStatusCode()+" which is NOT expected."
				+" The expected status code is "+sc);
		if(res) res = ap.getStatusText().equals(sct);
		if(res) b.report("The status Text for GET of "+url+" is "+sct+" which is expected.");
		else b.report("The status Text for GET of "+url+" is "+ap.getStatusText()+" which is NOT expected."
				+" The expected status Text is "+sct);
		if(res) res = ap.compareJsonResponse(sj);
		if(res) b.report("The JSON response for GET of "+url+" is "+sj+" which is expected.");
		else b.report("The JSON response for GET of "+url+" is "+ap.getResponseJSON()+" which is NOT expected."
				+" The expected status Text is "+sj);
		b.write("StatusJSON", ap.getResponseJSON());*/
		return res;
	}
	
	public static boolean validatePOST()
	{
		boolean res = true;
		/*APIManager ap = new APIManager(); String url = b.get("URL");String sc = b.get("StatusCode");
		String sct = b.get("StatusText");String sj = b.get("StatusJSON");String bj = b.get("BodyJSON");
		if(res) ap.httpPOST(url, bj);
		if(res) res = ap.getStatusCode().equals(sc);
		if(res) b.report("The status code for POST of "+url+" is "+sc+" which is expected.");
		else b.report("The status code for POST of "+url+" is "+ap.getStatusCode()+" which is NOT expected."
				+" The expected status code is "+sc);
		if(res) res = ap.getStatusText().equals(sct);
		if(res) b.report("The status Text for POST of "+url+" is "+sct+" which is expected.");
		else b.report("The status Text for POST of "+url+" is "+ap.getStatusText()+" which is NOT expected."
				+" The expected status Text is "+sct);
		if(res) res = ap.compareJsonResponse(sj);
		if(res) b.report("The JSON response for POST of "+url+" is "+sj+" which is expected.");
		else b.report("The JSON response for POST of "+url+" is "+ap.getResponseJSON()+" which is NOT expected."
				+" The expected status Text is "+sj);
		b.write("Attribute", ap.getResponseJSON());*/
		return res;
	}
	
	public static boolean validatePATCH()
	{
		boolean res = true;
		/*APIManager ap = new APIManager(); String url = b.get("URL");String sc = b.get("StatusCode");
		String sct = b.get("StatusText");String sj = b.get("StatusJSON");String bj = b.get("BodyJSON");
		if(res) ap.httpPATCH(url, bj);
		if(res) res = ap.getStatusCode().equals(sc);
		if(res) b.report("The status code for PATCH of "+url+" is "+sc+" which is expected.");
		else b.report("The status code for PATCH of "+url+" is "+ap.getStatusCode()+" which is NOT expected."
				+" The expected status code is "+sc);
		if(res) res = ap.getStatusText().equals(sct);
		if(res) b.report("The status Text for PATCH of "+url+" is "+sct+" which is expected.");
		else b.report("The status Text for PATCH of "+url+" is "+ap.getStatusText()+" which is NOT expected."
				+" The expected status Text is "+sct);
		if(res) res = ap.compareJsonResponse(sj);
		if(res) b.report("The JSON response for PATCH of "+url+" is "+sj+" which is expected.");
		else b.report("The JSON response for PATCH of "+url+" is "+ap.getResponseJSON()+" which is NOT expected."
				+" The expected status Text is "+sj);
		b.write("Attribute", ap.getResponseJSON());*/
		return res;
	}
}
