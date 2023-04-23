package CANTINNA;

import org.apache.commons.net.whois.WhoisClient;

public class WhoisIt {
	public static final String WHOIS_SERVER = "whois.internic.net";
	public static final int WHOIS_PORT = 43;

	public static void main(String[] args) throws Exception {

	    String nameToQuery = "um.ac.ir";

	    WhoisClient whoisClient = new WhoisClient();
	    whoisClient.connect(WHOIS_SERVER, WHOIS_PORT);
	    String results = whoisClient.query(nameToQuery);

	    System.out.println(results);
	}
	}

