package test;
import com.google.common.net.InternetDomainName;

import java.net.URL;

public class GetDomain {

  public static void main(final String... args) throws Exception {
    final String urlString = "http://login.google.com";
    final URL url = new URL(urlString);
    final String host = url.getHost();
    final InternetDomainName name = InternetDomainName.from(host).topPrivateDomain();

    
    System.out.println(urlString);
    System.out.println(host);
    System.out.println(name);
  }
}