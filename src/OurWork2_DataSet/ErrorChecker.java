package OurWork2_DataSet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;

public class ErrorChecker {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ErrorChecker tester = new ErrorChecker();
        try {
            tester.testConnectionTo("https://www.fgtsconsultar.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ErrorChecker() {
        super();
    }

    public void testConnectionTo(String aURL) throws Exception {
        URL destinationURL = new URL(aURL);
        HttpsURLConnection conn = (HttpsURLConnection) destinationURL
                .openConnection();
        conn.connect();
        Certificate[] certs = conn.getServerCertificates();
        for (Certificate cert : certs) {
            System.out.println("Certificate is: " + cert);
            if(cert instanceof X509Certificate) {
                try {
                    ( (X509Certificate) cert).checkValidity();
                    System.out.println("Certificate is active for current date");
                } catch(CertificateExpiredException cee) {
                    System.out.println("Certificate is expired");
                }
            }
        }
    }
}
