package mc.utils;
 
/**
 * COPIED from http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;
 
public class HttpLibrary {
 
	public static String USER_AGENT = "Mozilla/5.0";
 
	private static Logger logger = Logger.getLogger(HttpLibrary.class.getName());
	
	// HTTP POST request
	public static void sendPost(String url, String urlParameters) throws Exception 
	{
 
		logger.info("Http Post URL : " + url);
		logger.info("Http Post parameters : " + urlParameters);
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
  
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		logger.info("\nSending 'POST' request to URL : " + url);
		logger.info("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		logger.info(response.toString());
	}
 
}