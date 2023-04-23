package PhishWho;

import java.net.URI;
import java.net.URISyntaxException;

public class DomainName {

	public static String MyName(String URL) throws URISyntaxException {
		if(URL.contains(".")){
			URI uri = new URI(URL);
		    String domain = uri.getHost();
		    return domain.startsWith("www.") ? domain.substring(4) : domain;
		}
		else {
			return null;
		}
		 
	}

}
