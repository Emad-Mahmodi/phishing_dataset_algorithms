package test;


import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.whois.WhoisClient;

public class WhoisTest {

	public static void main(String[] args) {

		WhoisTest obj = new WhoisTest();
		System.out.println(obj.getWhois("http://www.cedaspy.com.br/wp-admin/-/9a7159c70b9723f5e81e37b2ea65daff/"));
		System.out.println("Done");

	}

	public String getWhois(String domainName) {

		StringBuilder result = new StringBuilder("");

		WhoisClient whois = new WhoisClient();
		try {

			//default is internic.net
			whois.connect(WhoisClient.DEFAULT_HOST);
			String whoisData1 = whois.query("=" + domainName);
			result.append(whoisData1);
			whois.disconnect();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();

	}

}
