package OurWork2_DataSet;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.*;
import java.util.*;
import java.security.cert.*;

//--- Checks if a certificate in local file is trusted based on supplied keystore file  ----

class IsCertTrusted {

 
    public static void main(String[] args) {

        /* Generate a RSA signature */

        if (args.length != 2) {
            System.out.println("Usage: java IsCertTrusted <certfile>  <keystorefile>");
	    return;
            }

	String CERTFILE = args[0] ;
	String KEYSTORE = args[1] ;
	if( !(new File(CERTFILE)).exists() || !(new File(KEYSTORE)).exists())
	{
		System.out.println("Either keystore or cert file not found!") ;
		System.exit(0) ;
	}

	  
	try
	{
	//------ Get the certificates issuer DN ------------------
	InputStream inStream = new FileInputStream(CERTFILE);
	CertificateFactory cf = CertificateFactory.getInstance("X.509");
	X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);
	inStream.close();
	String subjectdn	= cert.getSubjectDN().getName();
	String issuerdn		= cert.getIssuerDN().getName() ;
	System.out.println();
	System.out.println("\nSubjectName:\r\n" + subjectdn) ;
	System.out.println("\nIssuerName:\r\n" + issuerdn) ;

	//----- If this is a self-signed certificate, can verify signature immediately ---
	if(subjectdn.equals(issuerdn))
	{
		try
		{
		System.out.println();
		System.out.println("This is a self-signed certificate. Verifying signature ...") ;
		cert.verify(cert.getPublicKey()) ;
		System.out.println("Signature verified") ;
		}
		catch(Exception exc)
		{
		 System.out.println("Failed to verify self-signed certificate");
		}				
	 return;
	}


	// -- This is an issued certificate (not self-signed)
	// -- Check specified keystore for issuer DN; if present, check it's signature on certificate ----
	System.out.println();
	System.out.println("Searching in keystore for the issuer's certificate ...") ;
	System.out.print("Password to keystore for integrity check: ");
	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	//System.out.println();
	char[] pswd = stdin.readLine().toCharArray() ;
	KeyStore keystore = KeyStore.getInstance("JKS");
	FileInputStream fis = new FileInputStream(KEYSTORE) ;
	keystore.load(fis, pswd);
	fis.close();

	Enumeration en = keystore.aliases();
	String ALIAS = "" ;

	X509Certificate signingcert = null;

	while (en.hasMoreElements())
	{
		X509Certificate storecert = null;
		String ali = (String)en.nextElement() ;
		if(keystore.isCertificateEntry(ali))
		 {
			storecert = (X509Certificate)keystore.getCertificate(ali);
			if( (storecert.getIssuerDN().getName()).equals(issuerdn))
			{
			 try{
				System.out.println("Found matching issuer DN cert in keystore:\r\nChecking signature on cert ...") ;
				cert.verify(storecert.getPublicKey()) ;
				System.out.println("Signature verified on certificate") ;
				signingcert = storecert;
				break;
			  }
			 catch(Exception exc){
				System.out.println("Failed to verify signature on certificate with matching cert DN");
			  }				
			}			
		}
		else
		 if(keystore.isKeyEntry(ali))
			System.out.println(ali + "   **** key entry ****");
	}


	if(signingcert ==null)
		System.out.println("!! FAILED to find a signing certificate in keystore which matches or authenticates the certificate file");
        }
	catch (Exception e)
	{
            System.err.println("Caught exception " + e.toString());
        }

    }

}
