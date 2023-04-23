package CANTINNA;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.whois.WhoisClient;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyWhoisExample {
 
	public static void main(String[] args) {
 
		CrunchifyWhoisExample obj = new CrunchifyWhoisExample();
		System.out.println(obj.crunchifyWhois("crunchify.com"));
		System.out.println("\nTest Finished..");
 
	}
 
	public String crunchifyWhois(String domainName) {
 
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
 
		return whoisResult.toString();
	}
}
