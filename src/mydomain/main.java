package mydomain;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import PhishWho.DomainName;

public class main {

	public static void main(String S) throws IOException, URISyntaxException {
		    FileReader fr = new FileReader("public_suffix_list.dat.txt");
		    TopLevelDomainChecker checker = new TopLevelDomainChecker();
		    TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		    parser.parse(fr);
			DomainName dm3=new DomainName();
			String MainDomain=dm3.MyName(S);
			String sld;
			
		    sld = ".".concat(checker.extractSLD(MainDomain));
		    String zz="";
		    zz=MainDomain.substring(0,MainDomain.indexOf(sld));
		    if(zz.contains("."))
		    	zz=MainDomain.substring(zz.lastIndexOf(".")+1,zz.length());
	}

}
