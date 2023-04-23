package netPC;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ReadNetworkFile {


	public static String ContentWeb(String inputurl)
	{
		String response = null;
		try {
			String st=inputurl;
		    URL url = new URL(st);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		    // Hack to force HttpURLConnection to run the request
		    // Otherwise getErrorStream always returns null
		    connection.getResponseCode();
		    InputStream stream = connection.getErrorStream();
		    if (stream == null) {
		        stream = connection.getInputStream();
		    }
		    // This is a try with resources, Java 7+ only
		    // If you use Java 6 or less, use a finally block instead
		    try (Scanner scanner = new Scanner(stream)) {
		        scanner.useDelimiter("\\Z");
		        response = scanner.next();
		        System.out.println(response);
		    }
		} catch (MalformedURLException e) {
		    // Replace this with your exception handling
		    e.printStackTrace();
		} catch (IOException e) {
		    // Replace this with your exception handling
//		    e.printStackTrace();
			response="NULL";
		}
		return response;
	}
}