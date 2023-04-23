package test;

import java.io.FileInputStream;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSecuredConnection2 {

  public static void main(String args[]) throws Exception {
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    List mylist = new ArrayList();
    FileInputStream in = new FileInputStream(args[0]);
    Certificate c = cf.generateCertificate(in);
    mylist.add(c);

    CertPath cp = cf.generateCertPath(mylist);

    Certificate trust = cf.generateCertificate(in);
    TrustAnchor anchor = new TrustAnchor((X509Certificate) trust, null);
    PKIXParameters params = new PKIXParameters(Collections.singleton(anchor));
    params.setRevocationEnabled(false);
    CertPathValidator cpv = CertPathValidator.getInstance("PKIX");
    PKIXCertPathValidatorResult result = (PKIXCertPathValidatorResult) cpv.validate(cp, params);
    System.out.println(result);
  }
}