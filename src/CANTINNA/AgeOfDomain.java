package CANTINNA;

import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.whois.WhoisClient;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class AgeOfDomain {
 
	public static void main(String[] args) throws ParseException {
 
		AgeOfDomain obj = new AgeOfDomain();
		System.out.println(obj.crunchifyWhois("manesht.ir"));//only use SLD and TLD
		System.out.println("\nTest Finished..");
 
	}
 
	public String crunchifyWhois(String domainName) throws ParseException {
 
		StringBuilder whoisResult = new StringBuilder("");
 
		WhoisClient crunchifyWhois = new WhoisClient();
		try {
			// The WhoisClient class implements the client side of the Internet
			// Whois Protocol defined in RFC 954. To query a host you create a
			// WhoisClient instance, connect to the host, query the host, and
			// finally disconnect from the host. If the whois service you want
			// to query is on a non-standard port, connect to the host at that
			// port.
			crunchifyWhois.connect(WhoisClient.DEFAULT_HOST);
			String whoisData = crunchifyWhois.query("=" + domainName);
			whoisResult.append(whoisData);
			crunchifyWhois.disconnect();
 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = whoisResult.toString();
		int myindex1 = s.indexOf("Updated Date:");
		int myindex2 = s.indexOf("Creation Date:");
		int myindex3 = s.indexOf("Expiration Date:");
		
		/*SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy"); 
		Date dateobj = new Date();
		Date dateCurrent = formatter1.parse(formatter1.format(dateobj));
		System.out.println(formatter1.format(dateobj));
		TimeUnit timeUnit = TimeUnit.SECONDS ;
		
		String Updated_Date = s.substring(myindex1+14, myindex1+25);
		Date date1=formatter1.parse(Updated_Date); 
		System.out.println("Updated_Date==>"+Updated_Date+" ==> "+date1);
		System.out.println(getDateDiff(date1, dateCurrent));
		
		String Creation_Date =	s.substring(myindex2+15, myindex2+26);
		Date date2=formatter1.parse(Creation_Date); 
		System.out.println("Creation_Date==>"+Creation_Date+" ==> "+date2);
		System.out.println(getDateDiff(date2, dateCurrent));
		
		String Expiration_Date= s.substring(myindex3+17, myindex3+28);
		Date date3=formatter1.parse(Expiration_Date); 
		System.out.println("Expiration_Date==>"+Expiration_Date+" ==> "+date3);
		System.out.println(getDateDiff(date3, dateCurrent));*/
		
		return s;
	}
	
	public static int getDateDiff(Date date1, Date date2) {
		int diffInDays = (int)( (date1.getTime() - date2.getTime()) 
                / (1000 * 60 * 60 * 24) );
		return diffInDays;
	}
}
