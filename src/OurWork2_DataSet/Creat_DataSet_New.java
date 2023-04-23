package OurWork2_DataSet;

import java.net.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import PhishWho.DomainName;
import mydomain.TopLevelDomainChecker;
import mydomain.TopLevelDomainParser;

import java.awt.Label;
import java.io.*;

public class Creat_DataSet_New {
	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	public static ArrayList<String> SLD_List = new ArrayList<String>();
	public static ArrayList<String> AllWord_OK = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();
	public static ArrayList<String> TopWord_TFDF = new ArrayList<String>();
	public static ArrayList<String> AllLinks = new ArrayList<String>();
	public static ArrayList<String> Final_Sord_word2 = new ArrayList<String>();
	public static int flag;
	public static ArrayList<String> AllWord_Frequent_word = new ArrayList<String>();
	public static String Copyright_word;
	public static int found;

	public static void main(String URL, String URL2, WebDriver driver, String Phish_id, int Doc_number, String label)
			throws Exception {
		// fr parser for SLD n function SLDs and domain must be in main
		// algorithm and call that in here
		String domain_name = "";// domain(driver);
		String TargetURL = "";
		if (URL.equals(URL2)) {
			TargetURL = driver.getCurrentUrl();
			domain_name = domain(driver);
		} else {
			TargetURL = URL;
			domain_name = domain2(URL);
		}

		Delete();
		Copyright_word = " ";
		flag = 1;
		found = 0;
		URL aURL = new URL(URL);

		driver.get(URL2);

		Set_Resource(driver, URL);
		AllWord_Frequent_word = T_Plain(driver);

		
		if (found == 0) {
			AllWord_OK = CheckWord(AllWord_Frequent_word);
			double[] F14_F15_F16_F17 = new double[4];

			int[] F45_F46_F47_F48_F49_50 = new int[6];
			// String domain_name=domain(driver);

			// ____________________________________________________________________
			// feature _____________________________________________________

			// int f11_number_OnClick = OnClickWin(driver);

			// int F26_Validate_Portnumber = aURL.getPort();
			/*
			 * double[] F26_Identity_U_K_Booth_in_URL = new double[4]; int
			 * number_of_top_word_in_TFIDF = 10; int DocNumber = 98;
			 * F26_Identity_U_K_Booth_in_URL = IdentityFrequent_U_InURL(href ,
			 * domain_name , DocNumber , number_of_top_word_in_TFIDF );
			 */

			F14_F15_F16_F17 = Page_resource_access_protocol_feature_set();
			F45_F46_F47_F48_F49_50 = suspecios_Links(AllLinks);
			// ----------------------------------------------------------------------------------

			/*
			 * ArrayList<String> Result_word = new ArrayList<String>();
			 * 
			 * if (label.equals("1")) { TopWord_TFDF =
			 * WordRankByTFIDF(AllWord_OK, Doc_number, 100 AllWord_OK.size());
			 * Result_word = Propose_System(TopWord_TFDF, AllLinks, SLD_List);
			 * Result_word = Propose_System(AllWord_OK, AllLinks, SLD_List);
			 * }else{ Result_word = Propose_System(AllWord_OK, AllLinks,
			 * SLD_List); } double[] F26_Identity_U_K_Booth_in_URL = new
			 * double[11]; int sizeofindex=0;
			 * if(F26_Identity_U_K_Booth_in_URL.length > Result_word.size()){
			 * sizeofindex = Result_word.size(); }else{ sizeofindex =
			 * F26_Identity_U_K_Booth_in_URL.length; } for (int i = 0; i <
			 * sizeofindex ; i++) { F26_Identity_U_K_Booth_in_URL[i] =
			 * Double.valueOf(Result_word.get(i)); }
			 */
			ArrayList<String> TopTFIDFWords = new ArrayList<String>();
			TopTFIDFWords = WordRankByTFIDF(AllWord_OK, Doc_number, 20, label);
			// -----------------------------------------------------------------------------------
			// using Phishwho for feature ------------------
			int phishwho_result = -1;
			// PhishWho_algorithm ph = new PhishWho_algorithm();
			// phishwho_result = ph.main(URL, URL2, Phish_id, Doc_number, label
			// ,
			// AllWord_Frequent_word , AllLinks );

			// ----------------------------------------------------------------------------------------------

			// ------------------------------------------------------------------------------TTTTTTTTTTTTTTTTTtttttest---------------------------

			// ------------------------------------------------------------------------------------------------------------------------------------

			Filesave(allFeatures(Doc_number, TargetURL, label, domain_name, driver, aURL, F14_F15_F16_F17,
					F45_F46_F47_F48_F49_50, TopTFIDFWords, phishwho_result), label);
		} else {

		}

	}

	public static URLNode allFeatures(int Doc_number, String url, String label, String Domain, WebDriver driver,
			URL aURL, double[] F14_F15_F16_F17, int[] F45_F46_F47_F48_F49_50, ArrayList<String> TopTFIDFWords,
			int phishwho_result) throws IOException, URISyntaxException {
		URLNode node = new URLNode();//
		node.setUrl(remove_additional_character_from_URL(url, Doc_number));
		node.setF1(NumberOf_iframe(driver)); // int
		node.setF2(NumberOf_frame(driver)); // int
		node.setF3(NumberOf_form(driver)); // int
		node.setF4(NumberOf_input(driver)); // int
		node.setF5(HavingObject(driver));// int
		node.setF6(CodebseAttrInObject(driver)); // int
		node.setF7(HavingApplet(driver)); // int
		node.setF8(CodebseAttrInApplet(driver)); // int
		node.setF9(HavingLink(driver)); // int
		node.setF10(HrefAttrInLink(driver)); // int
		node.setF11(ActionAttrInform(driver)); // int
		node.setF12(HavingScript(driver));// int
		node.setF13(haveAtSign(url));// int
		node.setF14(haveUnderLine(url));// int
		node.setF15(havedash(url));// int
		node.setF16(haveQuestionsign(url));// int
		node.setF17(haveEqualsign(url));// int
		node.setF18(haveSpecialSymbol(url));// int /// forget %
		node.setF19(haveCodedURL(url));// int
		node.setF20(IP_address(url));// int
		node.setF21(MLDLength(Domain));// int
		node.setF22(countOccurrences(".", url)); // URL.split("\\.",-1).length-1;
		node.setF23(aURL.getPath().length());// int
		node.setF24(Type_of_TLD(url));// string
		node.setF25(aURL.getHost().length());// int
		node.setF26(aURL.getFile().length());// int
		node.setF27(Out_of_position_top_level_domain(url));// int
		node.setF28(Embedded_domain(url));// int
		node.setF29(SSL_certificate(aURL.getProtocol())); // int
		node.setF30(Levenshtein_distance_Normalize(Domain, href)); // double
		node.setF31(Levenshtein_distance_Normalize(Domain, Script)); // double
		node.setF32(Levenshtein_distance_Normalize(Domain, Css)); // double
		node.setF33(Levenshtein_distance_Normalize(Domain, src)); // double
		node.setF34(Number_of_AllLinks(AllLinks));
		int Nu_SLD = Number_of_SLD(AllLinks);
		node.setF35(Nu_SLD);// int
		node.setF36(Number_of_AllLinks(AllLinks) - Nu_SLD);// int
		node.setF37(Number_of_uniq_Links(SLD_List));// int
		node.setF38(Normalized_Frequent_Link(Number_of_AllLinks(AllLinks)));
		node.setF39(F14_F15_F16_F17[0]);// double
		node.setF40(F14_F15_F16_F17[1]);// double
		node.setF41(F14_F15_F16_F17[2]);// double
		node.setF42(F14_F15_F16_F17[3]);// double
		node.setF43(Number_TFIDF_Word_Occures_in_Main_SLD(Domain, TopTFIDFWords));
		// node.setF44(0);//node.setF44(phishwho_result);
		node.setF44(havePersen(url));

		node.setF45(F45_F46_F47_F48_F49_50[0]);// int
		node.setF46(F45_F46_F47_F48_F49_50[1]);// int
		node.setF47(F45_F46_F47_F48_F49_50[2]);// int
		node.setF48(F45_F46_F47_F48_F49_50[3]);// int
		node.setF49(F45_F46_F47_F48_F49_50[4]);// int
		node.setF50(F45_F46_F47_F48_F49_50[5]);// int
		node.setF51(having_input_pass(driver)); // int

		node.setF52(0);// node.setF52(GoogleSearch_response(TopTFIDFWords,
						// "null", 6, Domain)); // int
		node.setF53(0);// node.setF53(GoogleSearch_response(TopTFIDFWords,
						// Domain, 6, Domain));

		node.setF54(Sensetive_Words(url)); // int
		node.setF55(bad_action_field(driver, url, Domain)); // Login_Form_identity
		node.setF56(Nonmatching_URLs(SLD_List, Domain)); // int
		node.setF57(Out_of_position_brand_name(url, SLD_List, Domain)); // int
		String my_URL = url.substring(url.indexOf("//") + 2, url.length());
		my_URL = my_URL.substring(0, my_URL.indexOf("/"));

		node.setF58(0);// node.setF58(GoogleSearch_response_2String_input(my_URL,
						// Copyright_word, 6, Domain));
		node.setF59(0);// node.setF59(GoogleSearch_response_2String_input(Domain,
						// Copyright_word, 6, Domain));

		node.setF60(Login_Form_identity(driver, url, Domain, TopTFIDFWords, 20, SLD_List)); // Domain_name_identity
		node.setF61(Domain_name_identity(my_URL, SLD_List, Domain, TopTFIDFWords, 20)); // int
		node.setF62(Domain_name_in_the_path_of_the_URL(SLD_List, url));
		node.setF63(Nil_anchors(driver, my_URL, AllLinks));
		node.setF64(ID_foreign_anchors(driver, my_URL, Domain, TopTFIDFWords, 20));
		node.setF65(foreign_anchors(driver, my_URL, Domain, AllLinks));
		node.setF66(ID_foreign_request(driver, my_URL, Domain, TopTFIDFWords, 20));
		node.setF67(foreign_request(driver, my_URL, Domain, AllLinks));//
		node.setF68(Using_forms_with_Submit_button(driver));
		node.setF69(Sub_domain_in_URL(my_URL, Domain)); // Segments_of_URLs
		node.setF70(Segments_of_URLs(my_URL));
		node.setF71(Numerical_Primary_Domain(Domain));// Numerical_Primary_Domain
		node.setF72(tidf_keywords_contain_in_path_portion_of_URL(TopTFIDFWords, 20, my_URL));
		node.setF73(number_of_terms_in_the_host_name_of_the_URL(my_URL));

		node.setF74(NumberOf_redirect(driver));
		node.setF75(OnMouseOver(driver));

		node.setLabel(label);

		return node;

	}

	public static void Filesave(URLNode node, String label) {
		try {

			String path = "";
			if (label.equals("1")) {
				path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\legit2.txt";
			} else {
				path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\phish2.txt";
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

			StringBuffer str = new StringBuffer();

			str.append(node.getUrl());
			str.append(',');

			str.append(node.getF1());
			str.append(',');
			str.append(node.getF2());
			str.append(',');
			str.append(node.getF3());
			str.append(',');
			str.append(node.getF4());
			str.append(',');
			str.append(node.getF5());
			str.append(',');
			str.append(node.getF6());
			str.append(',');
			str.append(node.getF7());
			str.append(',');
			str.append(node.getF8());
			str.append(',');
			str.append(node.getF9());
			str.append(',');
			str.append(node.getF10());
			str.append(',');
			str.append(node.getF11());
			str.append(',');
			str.append(node.getF12());
			str.append(',');
			str.append(node.getF13());
			str.append(',');
			str.append(node.getF14());
			str.append(',');
			str.append(node.getF15());
			str.append(',');
			str.append(node.getF16());
			str.append(',');
			str.append(node.getF17());
			str.append(',');
			str.append(node.getF18());
			str.append(',');
			str.append(node.getF19());
			str.append(',');
			str.append(node.getF20());
			str.append(',');
			str.append(node.getF21());
			str.append(',');
			str.append(node.getF22());
			str.append(',');
			str.append(node.getF23());
			str.append(',');
			str.append(node.getF24());
			str.append(',');
			str.append(node.getF25());
			str.append(',');
			str.append(node.getF26());
			str.append(',');
			str.append(node.getF27());
			str.append(',');
			str.append(node.getF28());
			str.append(',');
			str.append(node.getF29());
			str.append(',');
			str.append(node.getF30());
			str.append(',');
			str.append(node.getF31());
			str.append(',');
			str.append(node.getF32());
			str.append(',');
			str.append(node.getF33());
			str.append(',');
			str.append(node.getF34());
			str.append(',');
			str.append(node.getF35());
			str.append(',');
			str.append(node.getF36());
			str.append(',');
			str.append(node.getF37());
			str.append(',');
			str.append(node.getF38());
			str.append(',');
			str.append(node.getF39());
			str.append(',');
			str.append(node.getF40());
			str.append(',');
			str.append(node.getF41());
			str.append(',');
			str.append(node.getF42());
			str.append(',');
			str.append(node.getF43());
			str.append(',');
			str.append(node.getF44());
			str.append(',');
			str.append(node.getF45());
			str.append(',');
			str.append(node.getF46());
			str.append(',');
			str.append(node.getF47());
			str.append(',');
			str.append(node.getF48());
			str.append(',');
			str.append(node.getF49());
			str.append(',');
			str.append(node.getF50());
			str.append(',');
			str.append(node.getF51());
			str.append(',');
			str.append(node.getF52());
			str.append(',');
			str.append(node.getF53());
			str.append(',');
			str.append(node.getF54());
			str.append(',');
			str.append(node.getF55());
			str.append(',');
			str.append(node.getF56());
			str.append(',');
			str.append(node.getF57());
			str.append(',');
			str.append(node.getF58());
			str.append(',');
			str.append(node.getF59());
			str.append(',');
			str.append(node.getF59());
			str.append(',');
			str.append(node.getF60());
			str.append(',');
			str.append(node.getF61());
			str.append(',');
			str.append(node.getF62());
			str.append(',');
			str.append(node.getF63());
			str.append(',');
			str.append(node.getF64());
			str.append(',');
			str.append(node.getF65());
			str.append(',');
			str.append(node.getF66());
			str.append(',');
			str.append(node.getF67());
			str.append(',');
			str.append(node.getF68());
			str.append(',');
			str.append(node.getF69());
			str.append(',');
			str.append(node.getF70());
			str.append(',');
			str.append(node.getF71());
			str.append(',');
			str.append(node.getF72());
			str.append(',');
			str.append(node.getF73());
			str.append(',');
			str.append(node.getF74());
			str.append(',');
			str.append(node.getF75());

			str.append(',');
			str.append(node.getLabel());

			System.out.println(str);
			out.println(str);

			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}

	public static String remove_additional_character_from_URL(String URL, int Doc_number) {
		String result = "";

		if (URL.contains("%")) {
			URL = URL.replace('%', ';');
		}
		if (URL.contains(",")) {
			URL = URL.replace(',', ';');
		}

		URL = String.valueOf(Doc_number).concat("-").concat(URL);
		return result;
	}

	public static int Embedded_domain(String URL) throws MalformedURLException {
		int result = 0;
		URL aURL = new URL(URL);

		String path = aURL.getPath();

		String words[] = path.split("\\.");

		if (words.length >= 3) {
			for (int i = 0; i < words.length; i++) {
				String Segment_word = words[i];
				if (Segment_word.length() < 2) {
					return 1;
				}
			}
		}

		return result;
	}

	public static int tidf_keywords_contain_in_path_portion_of_URL(ArrayList<String> TFIDF, int TopTFIDF, String URL)
			throws MalformedURLException {
		int result = 0;
		try {
			URL aURL = new URL(URL);
			String path_Portion = aURL.getPath();
			for (int i = 0; i < TopTFIDF; i++) {
				if (path_Portion.contains(TFIDF.get(i))) {
					result = 1;
					return result;
				} else {
					result = 0;
				}

			}

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int Numerical_Primary_Domain(String domain) {
		int result = 0;
		try {
			if (domain.matches("^-?\\d+$")) {
				result = 1;
			}

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}// Numerical Primary Domain

	public static int Sub_domain_in_URL(String URL, String domain) {
		int result = 0;
		try {
			String SubURL = URL.substring(URL.indexOf("//") + 2, URL.indexOf(domain) + domain.length());
			result = countOccurrences(SubURL, ".");

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int number_of_terms_in_the_host_name_of_the_URL(String URL) throws MalformedURLException {
		URL aURL = new URL(URL);
		int result = 0;
		try {
			String SubURL = aURL.getHost();
			result = countOccurrences(SubURL, ".");

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int Segments_of_URLs(String URL) {
		int result = 0;
		try {
			int num1 = 0;
			num1 = countOccurrences(URL, ".");
			int num2 = 0;
			num2 = countOccurrences(URL, "/");

			result = num1 + num2;
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int Login_Form_identity(WebDriver driver, String url, String domian, ArrayList<String> TFIDF_Key,
			int size_of_TFIDF, ArrayList<String> SLDs) {
		int result = 0;
		try {
			List<WebElement> action = driver.findElements(By.xpath(".//form"));
			ArrayList<String> URLs = new ArrayList<String>(); // or

			for (WebElement my : action) {
				if (my.getAttribute("action") == null || my.getAttribute("action").isEmpty()) {
					result = 0;
					continue;
				} else {
					URLs.add(my.getAttribute("action"));
				}
			}
			ArrayList<String> clean_URL = new ArrayList<String>();
			clean_URL = Check_localhost(URLs, url);

			ArrayList<String> SLDs_URLs_action_field = new ArrayList<String>();
			SLDs_URLs_action_field = SLDs(clean_URL);

			ArrayList<String> AllSLD_OK = new ArrayList<String>();
			AllSLD_OK = CheckWord(SLDs);

			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, SLDs);
			// SLD_OK(0,1,2,...) ; freq[]=(0,1,2,...)
			int index_of_frequent_SLD = Stimate_Max_freq(freq);
			String frequent_SLD = AllSLD_OK.get(index_of_frequent_SLD);

			int IU_Foreign_domain = 0;
			// check frequent URL is a forien domain
			if (frequent_SLD.equals(domian)) {
				IU_Foreign_domain = -1;
			} else {
				IU_Foreign_domain = 1;
			}
			// check action field is a forien domain
			int[] action_fild_foreign_domain = new int[SLDs_URLs_action_field.size()];

			for (int i = 0; i < SLDs_URLs_action_field.size(); i++) {
				if (SLDs_URLs_action_field.get(i).equals(domian)) {
					action_fild_foreign_domain[i] = -1;
				} else {
					action_fild_foreign_domain[i] = 1;
				}
			}

			int[] my_Ri = new int[SLDs_URLs_action_field.size()];
			for (int i = 0; i < SLDs_URLs_action_field.size(); i++) {
				if (action_fild_foreign_domain[i] == -1 && IU_Foreign_domain == -1) {
					my_Ri[i] = -1;
				} else if (action_fild_foreign_domain[i] == -1 && IU_Foreign_domain == 1) {
					for (int j = 0; j < size_of_TFIDF; j++) {
						if (TFIDF_Key.get(j).contains(SLDs_URLs_action_field.get(i))) {
							my_Ri[i] = 0;
						} else {
							my_Ri[i] = 1;
							return 1;
						}

					}
				} else if (action_fild_foreign_domain[i] == 1 && IU_Foreign_domain == 1) {
					my_Ri[i] = 1;
					return 1;
				} else if (action_fild_foreign_domain[i] == 1 && IU_Foreign_domain == -1) {
					my_Ri[i] = 1;
					return 1;
				}
			}

			for (int i = 0; i < my_Ri.length; i++) {
				if (my_Ri[i] == 0) {
					result = 0;
					break;
				} else {
					result = -1;
				}
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int bad_action_field(WebDriver driver, String url, String domian) {
		int result = 0;
		try {
			List<WebElement> action = driver.findElements(By.xpath(".//form"));
			ArrayList<String> URLs = new ArrayList<String>(); // or

			for (WebElement my : action) {
				if (my.getAttribute("action") == null || my.getAttribute("action").isEmpty()) {
					result = 0;
					continue;
				} else {
					URLs.add(my.getAttribute("action"));
				}
			}
			ArrayList<String> clean_URL = new ArrayList<String>();
			clean_URL = Check_localhost(URLs, url);
			for (int j = 0; j < clean_URL.size(); j++) {
				if (domian.toLowerCase().equals(clean_URL.get(j))) {
					continue;
				} else {
					result = -1;
					break;
				}
			}

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static int GoogleSearch_response(ArrayList<String> identity_key, String input_word, int top_google_response,
			String domain) throws IOException, URISyntaxException {
		// ---------------------------------------Use
		// GoogleSearch-----------------------------
		int detection = -1;
		try {
			String CondidKey = "";
			ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
			ArrayList<String> Google_response_SLDs = new ArrayList<String>();
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < top_google_response; i++) {
				str.append(identity_key.get(i).concat("+"));
			}
			if (input_word.toLowerCase().equals("null")) {
			} else {
				str.append(input_word.concat("+"));
			}
			CondidKey = str.toString();
			/*
			 * GoogleSearch_Selenium gs2=new GoogleSearch_Selenium(); int
			 * number=20; UrlListOfGoogleSearch=gs2.main(CondidKey , number ,
			 * driver2); if(UrlListOfGoogleSearch.size()==0){
			 * UrlListOfGoogleSearch.add("null"); }
			 */
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.google.com/search?num=30&q=" + CondidKey);
			List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
			// this are all the links you like to visit
			for (WebElement webElement : findElements) {
				System.out.println(webElement.getAttribute("href"));
				UrlListOfGoogleSearch.add(webElement.getAttribute("href"));
			}

			Google_response_SLDs = SLDs(UrlListOfGoogleSearch);

			detection = My_detection(domain, Google_response_SLDs);

			driver.close();
			return detection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return detection;
		}
	}

	public static int GoogleSearch_response_2String_input(String String_input1, String String_input2,
			int top_google_response, String domain) throws IOException, URISyntaxException {
		// ---------------------------------------Use
		// GoogleSearch-----------------------------
		int detection = -1;
		try {
			/*
			 * if (String_input1 == " " || String_input2 == " ") { return 0; }
			 */

			String CondidKey = "";
			ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
			ArrayList<String> Google_response_SLDs = new ArrayList<String>();

			CondidKey = String_input1.concat(String_input2);
			/*
			 * GoogleSearch_Selenium gs2=new GoogleSearch_Selenium(); int
			 * number=20; UrlListOfGoogleSearch=gs2.main(CondidKey , number ,
			 * driver2); if(UrlListOfGoogleSearch.size()==0){
			 * UrlListOfGoogleSearch.add("null"); }
			 */
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.google.com/search?num=30&q=" + CondidKey);
			List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
			// this are all the links you like to visit
			for (WebElement webElement : findElements) {
				System.out.println(webElement.getAttribute("href"));
				UrlListOfGoogleSearch.add(webElement.getAttribute("href"));
			}

			Google_response_SLDs = SLDs(UrlListOfGoogleSearch);

			detection = My_detection(domain, Google_response_SLDs);

			driver.close();
			return detection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return detection;
		}
	}

	public static int My_detection(String domain, ArrayList<String> sld) {
		int detection = 0;
		try {
			for (int i = 0; i < sld.size(); i++) {
				if (domain.toLowerCase().equals(sld.get(i).toLowerCase())) {
					detection = i + 1;
					break;
				} else {
					detection = 0;
					continue;
				}
			}
			return detection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return detection;
		}
	}

	public static int IP_address(String URL) {
		int is_IP_Address = 0;
		try {
			URL = URL.substring(URL.indexOf("//") + 2, URL.length());
			URL = URL.substring(0, URL.indexOf("/"));
			URL = URL.replace(".", "");
			if (URL.matches("^-?\\d+$")) {
				is_IP_Address = 1;
			}

			return is_IP_Address;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return is_IP_Address;
		}
	}

	public static int SSL_certificate(String URL) {
		int SSL_certificate = 0;
		try {
			if (URL.toLowerCase().equals("https")) {
				SSL_certificate = 1;
			}
			return SSL_certificate;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return SSL_certificate;
		}
	}

	public static int SSL_certificate2(String URL) {
		int SSL_certificate = 0;
		try {
			URL = URL.substring(0, URL.indexOf("//"));
			if (URL.toLowerCase().contains("https")) {
				SSL_certificate = 1;
			}
			return SSL_certificate;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return SSL_certificate;
		}
	}

	public static int countOccurrences(String find, String string) {
		int count = 0;
		try {
			int indexOf = 0;

			while (indexOf > -1) {
				indexOf = string.indexOf(find, indexOf + 1);
				if (indexOf > -1)
					count++;
			}

			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return count;
		}
	}

	public static void Set_Resource(WebDriver driver) {
		// ---------------------------------------------------------------
		List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
		List<WebElement> src_links = driver.findElements(By.xpath(".//img"));
		List<WebElement> Script_links = driver.findElements(By.xpath(".//script"));
		List<WebElement> Css_links = driver.findElements(By.xpath(".//link"));
		// -------- href , src , Script(JavaScript files) , Link(style sheet
		// files) -------------------------

		for (WebElement href_link : href_links) {
			href.add(href_link.getAttribute("href"));
			System.out.println(href_link.getAttribute("href"));
		}

		for (WebElement src_link : src_links) {
			src.add(src_link.getAttribute("src"));
			System.out.println(src_link.getAttribute("src"));
		}

		for (WebElement Script_link : Script_links) {
			Script.add(Script_link.getAttribute("src"));
			System.out.println(Script_link.getAttribute("src"));
		}

		for (WebElement Css_link : Css_links) {
			Css.add(Css_link.getAttribute("href"));
			System.out.println(Css_link.getAttribute("href"));
		}

	}

	public static double[] Page_resource_access_protocol_feature_set() {
		double[] URLs = new double[4];

		int a = 0;
		for (String zz : href) {
			try {
				if (zz.substring(0, zz.indexOf("//")).contains("https")) {
					a++;
				}
			} catch (Exception e) {
				continue;
			}
		}
		if (a == 0) {
			URLs[0] = -1;
		} else {
			URLs[0] = (double) a / href.size();
		}
		// ------------------------------------------------------------
		int b = 0;
		for (String zz : src) {
			try {
				if (zz.substring(0, zz.indexOf("//")).contains("https")) {
					b++;
				}
			} catch (Exception e) {
				continue;
			}
		}
		if (b == 0) {
			URLs[1] = -1;
		} else {
			URLs[1] = (double) b / src.size();
		}
		// -------------------------------------------------------------
		int c = 0;
		for (String zz : Script) {
			try {
				if (zz.substring(0, zz.indexOf("//")).contains("https")) {
					c++;
				}
			} catch (Exception e) {
				continue;
			}
		}
		if (c == 0) {
			URLs[2] = -1;
		} else {
			URLs[2] = (double) c / Script.size();
		}
		// -------------------------------------------------------------
		int d = 0;
		for (String zz : Css) {
			try {
				if (zz.substring(0, zz.indexOf("//")).contains("https")) {
					d++;
				}
			} catch (Exception e) {
				continue;
			}
		}
		if (d == 0) {
			URLs[3] = -1;
		} else {
			URLs[3] = (double) d / Css.size();
		}

		// ------------------------------------------------------------------
		return URLs;
	}

	public static int[] suspecios_Links(ArrayList<String> Links) {
		int[] F45_F46_F47_F48_F49_50 = new int[6];
		int f46 = 0;
		int f47 = 0;
		int f48 = 0;
		int f49 = 0;
		int f50 = 0;
		int f51 = 0;

		for (int i = 0; i < Links.size(); i++) {
			if (haveAtSign(Links.get(i)) == 1) {
				f46 = f46 + 1;
			}
			if (haveUnderLine(Links.get(i)) == 1) {
				f47 = f47 + 1;
			}
			if (havedash(Links.get(i)) == 1) {
				f48 = f48 + 1;
			}
			if (haveQuestionsign(Links.get(i)) == 1) {
				f49 = f49 + 1;
			}
			if (haveEqualsign(Links.get(i)) == 1) {
				f50 = f50 + 1;
			}
			if (havePersen(Links.get(i)) == 1) {
				f51 = f51 + 1;
			}
		}
		F45_F46_F47_F48_F49_50[0] = f46;
		F45_F46_F47_F48_F49_50[1] = f47;
		F45_F46_F47_F48_F49_50[2] = f48;
		F45_F46_F47_F48_F49_50[3] = f49;
		F45_F46_F47_F48_F49_50[4] = f50;
		F45_F46_F47_F48_F49_50[5] = f51;
		// ------------------------------------------------------------------
		return F45_F46_F47_F48_F49_50;
	}

	public static String domain(WebDriver driver) throws IOException, URISyntaxException {
		String TargetURL = driver.getCurrentUrl();
		FileReader fr = new FileReader("public_suffix_list.dat.txt");
		TopLevelDomainChecker checker = new TopLevelDomainChecker();
		TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		parser.parse(fr);
		// ------------------ SLD Target-----------------
		DomainName dm5 = new DomainName();
		String MainDomain2 = dm5.MyName(TargetURL);
		String sld2;
		sld2 = ".".concat(checker.extractSLD(MainDomain2));
		String URLSLD = "";
		URLSLD = "";
		URLSLD = MainDomain2.substring(0, MainDomain2.indexOf(sld2));
		if (URLSLD.contains("."))
			URLSLD = MainDomain2.substring(URLSLD.lastIndexOf(".") + 1, URLSLD.length());

		return URLSLD;
	}

	public static String domain2(String TargetURL) throws IOException, URISyntaxException {
		FileReader fr = new FileReader("public_suffix_list.dat.txt");
		TopLevelDomainChecker checker = new TopLevelDomainChecker();
		TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		parser.parse(fr);
		// ------------------ SLD Target-----------------
		DomainName dm5 = new DomainName();
		String MainDomain2 = dm5.MyName(TargetURL);
		String sld2;
		sld2 = ".".concat(checker.extractSLD(MainDomain2));
		String URLSLD = "";
		URLSLD = "";
		URLSLD = MainDomain2.substring(0, MainDomain2.indexOf(sld2));
		if (URLSLD.contains("."))
			URLSLD = MainDomain2.substring(URLSLD.lastIndexOf(".") + 1, URLSLD.length());

		return URLSLD;
	}

	public static ArrayList<String> SLDs(ArrayList<String> Inpute) throws IOException, URISyntaxException {
		String TargetURL = "";
		ArrayList<String> SLD_List = new ArrayList<String>();
		FileReader fr = new FileReader("public_suffix_list.dat.txt");
		TopLevelDomainChecker checker = new TopLevelDomainChecker();
		TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		parser.parse(fr);
		for (int i = 0; i < Inpute.size(); i++) {
			TargetURL = Inpute.get(i);

			try {

				// ------------------ SLD Target-----------------
				DomainName dm5 = new DomainName();
				String MainDomain2 = dm5.MyName(TargetURL);
				String sld2;
				sld2 = ".".concat(checker.extractSLD(MainDomain2));
				String URLSLD = "";
				URLSLD = "";
				URLSLD = MainDomain2.substring(0, MainDomain2.indexOf(sld2));
				if (URLSLD.contains("."))
					URLSLD = MainDomain2.substring(URLSLD.lastIndexOf(".") + 1, URLSLD.length());
				SLD_List.add(URLSLD);
			} catch (Exception e) {
				continue;
			}
		}

		return SLD_List;
	}

	public static String Type_of_TLD(String URL) throws IOException {
		String TargetURL = URL;
		FileReader fr = new FileReader("public_suffix_list.dat.txt");
		TopLevelDomainChecker checker = new TopLevelDomainChecker();
		TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		parser.parse(fr);
		String sld2 = "";

		try {
			// ------------------ SLD Target-----------------
			DomainName dm5 = new DomainName();
			String MainDomain2 = dm5.MyName(TargetURL);
			sld2 = ".".concat(checker.extractSLD(MainDomain2));
		} catch (Exception e) {
		}

		return sld2;
	}

	public static double Levenshtein_distance_Normalize(String domain, ArrayList<String> resource)
			throws IOException, URISyntaxException {
		double result = 0;
		try {

			if (resource == null || resource.isEmpty()) {
				return -1;
			}
			ArrayList<String> SLD_List = new ArrayList<String>();
			SLD_List.addAll(SLDs(resource));
			double[] LD = new double[SLD_List.size()];

			for (int i = 0; i < SLD_List.size(); i++) {
				int max = 0;
				if (SLD_List.get(i).length() > domain.length()) {
					max = SLD_List.get(i).length();
				} else {
					max = domain.length();
				}
				LD[i] = (double) LD_distance(SLD_List.get(i), domain) / max;
			}

			for (int i = 0; i < LD.length; i++) {
				result = result + LD[i];
			}
			result = (double) result / LD.length;
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

	public static int Number_TFIDF_Word_Occures_in_Main_SLD(String Domain, ArrayList<String> TopTFIDFWords) {
		int number_occure = 0;
		try {
			for (int i = 0; i < TopTFIDFWords.size(); i++) {
				if (Domain.contains(TopTFIDFWords.get(i))) {
					number_occure = i + 1;
					return i + 1;
				} else {
					continue;
				}
			}
			return number_occure;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return number_occure;
		}
	}

	public static int LD_distance(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		// i == 0
		int[] costs = new int[b.length() + 1];
		for (int j = 0; j < costs.length; j++)
			costs[j] = j;
		for (int i = 1; i <= a.length(); i++) {
			// j == 0; nw = lev(i - 1, j)
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= b.length(); j++) {
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
						a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[b.length()];
	}

	public static int NumberOf_iframe(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> iframe = driver.findElements(By.xpath(".//iframe"));// iframe
			for (WebElement my : iframe) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int Using_forms_with_Submit_button(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> frame = driver.findElements(By.xpath(".//form/input"));// iframe
			for (WebElement my : frame) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int having_input_pass(WebDriver driver) {
		List<WebElement> input_pass = driver.findElements(By.xpath(".//input[@value='Password' or @type='password']"));
		List<WebElement> input_pass2 = driver
				.findElements(By.xpath(".//input[@value='credit card'or @type='password']"));
		List<WebElement> input_pass3 = driver
				.findElements(By.xpath(" //input[contains(@id, 'password'or @type='password')]"));
		List<WebElement> input_pass4 = driver
				.findElements(By.xpath(" //input[contains(@id, 'credit')or @type='password']"));

		int i = 0;
		for (WebElement my : input_pass) {
			i++;
			System.out.println(i + "  -  ");
		}
		for (WebElement my : input_pass2) {
			i++;
			System.out.println(i + "  -  ");
		}
		for (WebElement my : input_pass3) {
			i++;
			System.out.println(i + "  -  ");
		}
		for (WebElement my : input_pass4) {
			i++;
			System.out.println(i + "  -  ");
		}
		if (i > 0) {
			i = 1;
		} else {
			i = 0;
		}
		return i;
	}

	public static int NumberOf_redirect(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//div"));
			// -------- href , src , Script(JavaScript files) , Link(style sheet
			// files) -------------------------

			for (WebElement href_link : href_links) {
				if (href_link.getAttribute("onclick") == null || href_link.getAttribute("onclick").isEmpty()) {
					continue;
				} else {
					i++;
				}
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int NumberOf_frame(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> frame = driver.findElements(By.xpath(".//frame"));// iframe
			for (WebElement my : frame) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int NumberOf_script(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> frame = driver.findElements(By.xpath(".//script"));// iframe
			for (WebElement my : frame) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int OnMouseOver(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> mouses = driver.findElements(By.xpath(".//img[@onmouseover]"));// iframe
			for (WebElement my : mouses) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int NumberOf_form(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> form = driver.findElements(By.xpath(".//form"));// iframe

			for (WebElement my : form) {
				i++;
				System.out.println(i + "  -  ");
			}

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int NumberOf_input(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> input = driver.findElements(By.xpath(".//input"));// input[@id
			for (WebElement my : input) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int HavingObject(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> myobject = driver.findElements(By.xpath("//object"));// input[@id
			for (WebElement my : myobject) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int CodebseAttrInObject(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> codebase = driver.findElements(By.xpath(".//object[@codebase]"));// input[@id
			for (WebElement my : codebase) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int HavingApplet(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> myobject = driver.findElements(By.xpath(".//applet"));// input[@id
			for (WebElement my : myobject) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int CodebseAttrInApplet(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> codebase = driver.findElements(By.xpath(".//applet[@codebase]"));// input[@id
																								// or
																								// @name]

			for (WebElement my : codebase) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int HavingLink(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> mylink = driver.findElements(By.xpath(".//link"));// input[@id
																				// or
																				// @name]

			for (WebElement my : mylink) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int HrefAttrInLink(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> href = driver.findElements(By.xpath(".//link[@href]"));// input[@id
																					// or
																					// @name]
			for (WebElement my : href) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int ActionAttrInform(WebDriver driver) {
		int i = 0;

		try {
			List<WebElement> action = driver.findElements(By.xpath(".//form[@action]"));// input[@id
																						// or
																						// @name]
			for (WebElement my : action) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int HavingScript(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> action = driver.findElements(By.xpath(".//script"));

			for (WebElement my : action) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}

	public static int OnClickWin(WebDriver driver) {
		int i = 0;
		try {
			List<WebElement> action = driver.findElements(By.xpath(".//*[@onClick='window.open()']")); // "//div/@onClick='window.open()'"

			for (WebElement my : action) {
				i++;
				System.out.println(i + "  -  ");
			}
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return i;
		}
	}// haveAtSign

	public static int haveAtSign(String URL) {
		if (URL.contains("@")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int havePersen(String URL) {
		if (URL.contains("%")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int haveUnderLine(String URL) {
		if (URL.contains("_")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int havedash(String URL) {
		if (URL.contains("-")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int haveQuestionsign(String URL) {
		if (URL.contains("?")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int haveEqualsign(String URL) {
		if (URL.contains("=")) {
			return 1;
		} else {
			return 0;
		}
	}// haveSpecialSymbol

	public static int haveSpecialSymbol(String URL) {
		if (URL.contains("=") || URL.contains("?") || URL.contains("-") || URL.contains("_") || URL.contains("@")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int haveCodedURL(String URL) {
		try {
			UrlValidator urlValidator = new UrlValidator();
			if (urlValidator.isValid(URL)) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	public static int MLDLength(String Domain) {
		return Domain.length();
	}

	public static ArrayList<String> CheckWord(List<String> AllWord) {
		ArrayList<String> Word_filter = new ArrayList<String>();
		// ----------------------------convert array list into string
		// ------------
		String listString = "";
		for (String s : AllWord) {
			s = s.toLowerCase();
			listString += s + " ";
		}
		// -------------------------------- (remove Double word)
		// -----------------
		/*
		 * ChechDouble ch=new ChechDouble(); listString = ch.check(listString);
		 */

		// -------------------------------convert String into array
		// list--------------
		Word_filter.clear();
		Word_filter = getWords(deDup(listString));

		return Word_filter;
	}

	public static String deDup(String s) {
		return new LinkedHashSet<String>(Arrays.asList(s.split(" "))).toString();
	}

	public static ArrayList<String> getWords(String text) {
		ArrayList<String> words = new ArrayList<String>();
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(text);
		int lastIndex = breakIterator.first();
		while (BreakIterator.DONE != lastIndex) {
			int firstIndex = lastIndex;
			lastIndex = breakIterator.next();
			if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
				words.add(text.substring(firstIndex, lastIndex));
			}
		}

		return words;
	}

	public static double[] IdentityFrequent_U_InURL(ArrayList<String> resource, String domain, int DocNumber,
			int SizeOfTopWord, String label) throws Exception {

		double[] Identity_U_K_Both_in_URL_And_TFIDF_in_google = new double[4];
		// SLD_List.addAll(SLDs(resource));
		TopWord_TFDF = WordRankByTFIDF(AllWord_OK, DocNumber,
				SizeOfTopWord /* AllWord_OK.size() */ , label);

		ArrayList<String> AllSLD_OK = new ArrayList<String>();
		AllSLD_OK = CheckWord(SLD_List);
		double[] freq = new double[AllSLD_OK.size()];
		freq = Frequence_SLD_in_URL(AllSLD_OK, SLD_List);
		double Weight_ALL_SLD = 0;
		for (int i = 0; i < freq.length; i++) {
			Weight_ALL_SLD = Weight_ALL_SLD + freq[i];
		}

		System.out.println(Weight_ALL_SLD);
		ArrayList<String> Final_Sord_word = new ArrayList<String>();
		double[] Final_Sord_word_weight = new double[freq.length];
		double[][] data2 = new double[2][freq.length];
		data2 = Sort(freq, AllSLD_OK);
		int number = (int) data2[1][0];

		if (domain.equals(AllSLD_OK.get(number))) {
			Identity_U_K_Both_in_URL_And_TFIDF_in_google[0] = 0;
		} else {
			Identity_U_K_Both_in_URL_And_TFIDF_in_google[0] = 1;
		}
		// ---------------------------------------------------------------------------
		for (int i = 0; i < TopWord_TFDF.size(); i++) {
			if (domain.equals(TopWord_TFDF.get(i))) {
				Identity_U_K_Both_in_URL_And_TFIDF_in_google[1] = 0;
				break;
			} else {
				Identity_U_K_Both_in_URL_And_TFIDF_in_google[1] = 1;
			}
		}
		// ----------------------------------------------------------------------------
		if (domain.equals(AllSLD_OK.get(number))) {
			for (int i = 0; i < TopWord_TFDF.size(); i++) {
				if (domain.equals(TopWord_TFDF.get(i))) {
					Identity_U_K_Both_in_URL_And_TFIDF_in_google[2] = 0;
					break;
				} else {
					Identity_U_K_Both_in_URL_And_TFIDF_in_google[2] = 1;
				}
			}
		}

		// -------------------------------------------------------------------------------------------

		ArrayList<String> GoogleResponse = new ArrayList<String>();

		int flag = 0;
		GoogleResponse = SLDs(GoogleSearch(TopWord_TFDF, domain));
		for (int i = 0; i < GoogleResponse.size() && flag == 0; i++) {

			if (GoogleResponse.get(i).equals(domain)) {
				Identity_U_K_Both_in_URL_And_TFIDF_in_google[3] = i + 1;
				flag = 1;
				break;
			} else {
				Identity_U_K_Both_in_URL_And_TFIDF_in_google[3] = -1;
			}

		}

		return Identity_U_K_Both_in_URL_And_TFIDF_in_google;
	}

	public static double[][] Sort(double[] WordWeightInAllUrl, ArrayList<String> AllWord) {
		// ---------------------------------sort
		// [iteration][index]----------------------------
		double[] data1 = WordWeightInAllUrl;
		int size = data1.length;
		double[][] data2 = new double[2][size];
		for (int i19 = 0; i19 < data1.length; i19++) {
			data2[0][i19] = data1[i19];
			data2[1][i19] = i19;
		}
		// =========================================================================
		double temp;
		for (int i = 1; i < data2[0].length; i++)
			for (int j = 0; j < data2[0].length - i; j++) {
				if (data2[0][j] < data2[0][j + 1]) {
					temp = data2[0][j + 1];
					data2[0][j + 1] = data2[0][j];
					data2[0][j] = temp;
					temp = data2[1][j + 1];
					data2[1][j + 1] = data2[1][j];
					data2[1][j] = temp;
				}
			}
		System.out.println(data2);
		return data2;
	}

	public static double[] Frequence_SLD_in_URL(ArrayList<String> AllSLD_OK, ArrayList<String> AllSLD_Frequent_SLD) {
		double[] freq2 = new double[AllSLD_OK.size()];
		int Freq3Count = 0;

		for (int i = 0; i < AllSLD_OK.size(); i++) {
			for (int j = 0; j < AllSLD_Frequent_SLD.size(); j++) {
				if (AllSLD_OK.get(i).equals(AllSLD_Frequent_SLD.get(j))) {
					Freq3Count++;
				}
			}
			freq2[i] = Freq3Count;
			Freq3Count = 0;
		}
		return freq2;
	}

	public static ArrayList<String> WordRankByTFIDF(ArrayList<String> AllWord, int DocNumber, int SizeOfTopWord,
			String label) throws Exception {
		indexing(label, DocNumber);
		SearchFiles search = new SearchFiles();
		ArrayList<String> top_best_word = new ArrayList<String>();
		ArrayList<String> TopWord_TFDF_Plus_Scor = new ArrayList<String>();

		System.out.println();
		double[][] Scor_Each_Word = new double[2][AllWord.size()];// 0D for
																	// index ith
																	// word and
																	// 1D for
																	// index
																	// Score
		for (int i = 0; i < AllWord.size(); i++) {

			Scor_Each_Word[0][i] = i;
			System.out.println("Number Word : " + i);
			if (AllWord.get(i).matches("^-?\\d+$")) {
				Scor_Each_Word[1][i] = 0;
				continue;
			}
			double[][] NumbrDoc_Scor2 = search.main(AllWord.get(i));// oD for
																	// DocNumber
																	// and 1D
																	// for Score
			if (NumbrDoc_Scor2[0] == null || NumbrDoc_Scor2[0].length == 0) { // if
																				// word
																				// not
																				// score
																				// same
																				// as
																				// "the,of,a,..."
				Scor_Each_Word[1][i] = 0;
				continue;
			} else {
				int resultSize = NumbrDoc_Scor2[0].length;
				if (NumbrDoc_Scor2[0].length >= 100) {
					resultSize = 100;
				}
				for (int j = 0; j < resultSize; j++) {
					if (NumbrDoc_Scor2[0][j] == DocNumber) {
						Scor_Each_Word[1][i] = NumbrDoc_Scor2[1][j];
						System.out.println(
								"Number Score is : " + NumbrDoc_Scor2[0][j] + "Scor is : " + NumbrDoc_Scor2[1][j]);
						break;
					}
					Scor_Each_Word[1][i] = 0;
					System.out.println(
							j + " : Number Score is : " + NumbrDoc_Scor2[0][j] + "Scor is : " + NumbrDoc_Scor2[1][j]);
				}
			}
			System.out.println("String Word is : " + AllWord.get(i));

		}
		Scor_Each_Word = Sort_TFIDF_Score(Scor_Each_Word);

		if (SizeOfTopWord > Scor_Each_Word[0].length) {
			SizeOfTopWord = Scor_Each_Word[0].length;
		}
		for (int i = 0; i < SizeOfTopWord; i++) {
			if (Scor_Each_Word[1][i] != 0) {
				top_best_word.addAll(One_Word_that_host_plus_SLD(AllWord.get((int) Scor_Each_Word[0][i])));
				System.out.println(i + " : " + AllWord.get((int) Scor_Each_Word[0][i]) + " => " + Scor_Each_Word[1][i]);
				TopWord_TFDF_Plus_Scor.add(AllWord.get((int) Scor_Each_Word[0][i]));
				TopWord_TFDF_Plus_Scor.add(String.valueOf(Scor_Each_Word[1][i]));
			}
		}

		System.out.println();
		return top_best_word;
	}

	public static double[][] Sort_TFIDF_Score(double[][] data2) {

		// =========================================================================
		double temp;
		for (int i = 1; i < data2[0].length; i++)
			for (int j = 0; j < data2[0].length - i; j++) {
				if (data2[1][j] < data2[1][j + 1]) {
					temp = data2[1][j + 1];
					data2[1][j + 1] = data2[1][j];
					data2[1][j] = temp;
					temp = data2[0][j + 1];
					data2[0][j + 1] = data2[0][j];
					data2[0][j] = temp;
				}
			}
		System.out.println(data2);
		return data2;
	}

	public static void indexing(String label, int docNumber) {
		// ------------------------------- indexing file for tfidf
		// -----------------------------------

		String Allwords = "";
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < AllWord_Frequent_word.size(); i++) {
			str.append(AllWord_Frequent_word.get(i));
			str.append(' ');
		}

		Allwords = str.toString();
		IndexFiles index = new IndexFiles();
		String docsPath = "";
		if (label.equals("1")) {
			docsPath = "D:\\WEBSIT_TEXT_File\\MyNewData\\legit\\";
			Filesave(Allwords, docsPath, docNumber);
		} else {
			docsPath = "D:\\WEBSIT_TEXT_File\\MyNewData\\phish\\";
			Filesave(Allwords, docsPath, docNumber);
		}
		index.main(docsPath);
	}

	public static void Filesave(String Word, String docsPath, int docNumber) {
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter(docsPath + String.valueOf(docNumber) + ".txt", true)));
			out.println(Word);
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public static ArrayList<String> One_Word_that_host_plus_SLD(String Words) {
		ArrayList<String> result = new ArrayList<String>();
		if (Words.contains(".")) {
			String[] s2 = Words.split(Pattern.quote("."));// String[] s2 =
															// Words.get(i).split("\\.");
			for (int i2 = 0; i2 < s2.length; i2++) {
				result.add(s2[i2]);
			}
		} else {
			result.add(Words);
		}

		return result;
	}

	public static String Copyright_word(String body_String) {

		String copyright = " ";

		try {
			int index_copy_symbol = body_String.indexOf("©");

			String String_right = body_String.substring(index_copy_symbol + 1, body_String.length());//
			String String_Left = body_String.substring(0, index_copy_symbol - 1);

			String s1 = String_right.substring(1, String_right.length());
			String s2 = String_Left.substring(String_Left.lastIndexOf(" "));

			copyright = s2.concat(s1);
			return copyright;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return copyright;
		}
	}

	public static ArrayList<String> T_Plain(WebDriver driver) {
		ArrayList<String> Words = new ArrayList<String>();
		ArrayList<String> my_string_word = new ArrayList<String>();
		ArrayList<String> W_with_host_andSLD = new ArrayList<String>();
		try {
			List<WebElement> meta_link = driver.findElements(By.xpath(".//meta"));
			System.out.println();
			// ---------------------- Text(Title) -------------------------
			String Title_String = driver.getTitle();
			my_string_word = getWords(Title_String);
			Words.addAll(my_string_word);
			title.addAll(my_string_word);

			// ---------------------- Text(body) -------------------------

			String body_String = driver.findElement(By.tagName("body")).getText();
			
			if(body_String.toLowerCase().contains("object not found")||
			   body_String.toLowerCase().contains("error 403")	){
				found=1;
			}
			
			my_string_word.clear();
			my_string_word = getWords(body_String);
			Words.addAll(my_string_word);
			body.addAll(my_string_word);

			// ---------------------- Text -------------------------
			my_string_word.clear();
			for (WebElement webElement : meta_link) {
				String meta_text = webElement.getAttribute("content");
				my_string_word = getWords(meta_text);
				meta.addAll(my_string_word);
				Words.addAll(my_string_word);
				my_string_word.clear();
			}
			for (int i = Words.size() - 1; i >= 0; i--) {
				if (Words.get(i) == null || Words.get(i).isEmpty()) {
					Words.remove(i);
				}
			}
			// -------------------------------------------------------------------

			W_with_host_andSLD = Word_that_host_plus_SLD(Words);
			// ------------------------------------------------------------------------find
			// copyright --------------------s
			Copyright_word = Copyright_word(body_String);
			// ----------------------------------------------------------------------------------------------
			return W_with_host_andSLD;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return W_with_host_andSLD;
		}
	}

	public static ArrayList<String> Word_that_host_plus_SLD(ArrayList<String> Words) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).contains(".")) {
				String[] s2 = Words.get(i).split(Pattern.quote("."));// String[]
																		// s2 =
																		// Words.get(i).split("\\.");
				for (int i2 = 0; i2 < s2.length; i2++) {
					Words.add(s2[i2]);
				}
			}
		}
		return Words;
	}

	public static ArrayList<String> GoogleSearch(ArrayList<String> identity_key, String domain) {
		// ---------------------------------------Use
		// GoogleSearch-----------------------------
		String CondidKey = "";
		ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < identity_key.size(); i++) {
			if (identity_key.get(i).toLowerCase().equals(domain)) {
				CondidKey = identity_key.get(i);
				break;
			} else {
				str.append(identity_key.get(i).concat("+"));
			}
		}
		CondidKey = str.toString();
		/*
		 * GoogleSearch_Selenium gs2=new GoogleSearch_Selenium(); int number=20;
		 * UrlListOfGoogleSearch=gs2.main(CondidKey , number , driver2);
		 * if(UrlListOfGoogleSearch.size()==0){
		 * UrlListOfGoogleSearch.add("null"); }
		 */

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com/search?num=30&q=" + CondidKey);
		List<WebElement> findElements = driver.findElements(By.xpath(".//*[@id='rso']//h3/a"));
		// this are all the links you like to visit
		for (WebElement webElement : findElements) {
			System.out.println(webElement.getAttribute("href"));
			UrlListOfGoogleSearch.add(webElement.getAttribute("href"));
		}

		driver.quit();
		return UrlListOfGoogleSearch;
	}

	public static ArrayList<String> Check_localhost(ArrayList<String> URLs, String My_URL) {
		ArrayList<String> clean_URL = new ArrayList<String>();
		if (My_URL.lastIndexOf("/") == My_URL.length() - 1) {
			My_URL = My_URL.substring(0, My_URL.length() - 1);
		}
		System.out.println(URLs.size());
		for (int i = 0; i < URLs.size(); i++) {
			if (URLs.get(i).toLowerCase().contains("localhost/leg".toLowerCase())
					|| URLs.get(i).toLowerCase().contains("localhost/phish".toLowerCase())) {
				String s = URLs.get(i);
				String s3 = s.substring(s.indexOf("RAW-HTML") + 8, s.length());
				String s2 = My_URL.concat(s3);
				clean_URL.add(s2);
			} else if (URLs.get(i).toLowerCase().contains("localhost/".toLowerCase())) {
				String s = URLs.get(i);
				String s3 = s.substring(s.indexOf("localhost") + 10, s.length());
				String s2 = My_URL.concat(s3);
				clean_URL.add(s2);
			} else {
				clean_URL.add(URLs.get(i));
			}
		}

		return clean_URL;
	}

	public static void Delete() {
		src.clear();
		href.clear();
		Script.clear();
		Css.clear();
		AllLinks.clear();
		SLD_List.clear();
		AllWord_OK.clear();
		title.clear();
		body.clear();
		meta.clear();
		TopWord_TFDF.clear();
		Final_Sord_word2.clear();
		AllWord_Frequent_word.clear();

	}

	public static ArrayList<String> Propose_System(ArrayList<String> AllWord_OK, ArrayList<String> AllURL,
			ArrayList<String> AllSLD_Frequent_SLD) {

		ArrayList<String> Final_Sord_word = new ArrayList<String>();

		ArrayList<String> AllSLD_OK = new ArrayList<String>();
		AllSLD_OK = CheckWord(SLD_List);

		try {
			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, AllSLD_Frequent_SLD);
			double[][] distance_word_in_SLD = new double[AllSLD_OK.size()][AllWord_OK.size()];
			double Weight_ALL_SLD = 0;
			for (int i = 0; i < freq.length; i++) {
				Weight_ALL_SLD = Weight_ALL_SLD + freq[i];
			}

			for (int i = 0; i < AllSLD_OK.size(); i++) {
				for (int j = 0; j < AllWord_OK.size(); j++) {
					String s1 = AllSLD_OK.get(i);
					String s2 = AllWord_OK.get(j);
					flag = 1;

					if (s1.length() >= s2.length()) {
						int x3 = minDistance(s1, s2);
						double x1 = (double) flag * ((double) 1 / (x3 + 1));
						double x2 = (double) freq[i] / Weight_ALL_SLD;
						distance_word_in_SLD[i][j] = (double) (x2 * x1);
						// distance_word_in_SLD[i][j] =
						// (double)((double)freq[i]/Weight_ALL_SLD) *((double)1
						// / (minDistance(AllSLD_OK.get(i) ,
						// AllWord_OK.get(j))+1));
					} else {
						distance_word_in_SLD[i][j] = 0;
					}
				}
			}
			String[][] Sort_Word = new String[freq.length][10 /*
																 * AllWord_OK.
																 * size ()
																 */];
			double[][] Sort_Word_weight = new double[freq.length][10 /*
																		 * AllWord_OK
																		 * .size
																		 * ()
																		 */];
			double[][] data2 = new double[2][AllWord_OK.size()];
			ArrayList<String> allword_array = new ArrayList<String>();
			double[] weight_allword_array = new double[freq.length * 10];
			int counter = 0;
			for (int i = 0; i < freq.length; i++) {
				data2 = Sort(distance_word_in_SLD[i], AllWord_OK);
				for (int j = 0; j < 10; j++) {
					int z = (int) data2[1][j];
					double z2 = data2[0][j];
					Sort_Word[i][j] = AllWord_OK.get(z);
					allword_array.add(AllWord_OK.get(z));

					Sort_Word_weight[i][j] = z2;
					weight_allword_array[counter] = z2;
					counter++;
				}
			}
			Final_Sord_word = FinallSort(weight_allword_array, allword_array, freq.length * 10);
			return Final_Sord_word;
		} catch (Exception e) {
			return Final_Sord_word;
		}

	}

	public static ArrayList<String> FinallSort(double[] WordWeightInAllUrl, ArrayList<String> AllWord, int n) {
		double[][] data2 = new double[2][n];
		data2 = Sort(WordWeightInAllUrl, AllWord);

		ArrayList<String> Final_Sord_word = new ArrayList<String>();

		double[] Final_Sord_word_weight = new double[n];

		for (int j = 0; j < n; j++) {
			int z = (int) data2[1][j];
			double z2 = data2[0][j];
			Final_Sord_word.add(String.valueOf(z2));
			Final_Sord_word2.add(AllWord.get(z));
			Final_Sord_word_weight[j] = z2;

		}
		return Final_Sord_word;
	}

	public static int minDistance(String word2, String word1) {
		int len1 = word1.length();
		int len2 = word2.length();

		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}

		// iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);

				// if last two chars equal
				if (c1 == c2) {
					// update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 3;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}

		for (int i = 0; i <= len2; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();

		System.out.println("---------------------------------------------------------------------------------");

		for (int i = len1; i >= 0; i--) {
			for (int j = 0; j <= len2; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		if (word2.contains(word1)) {
			/*
			 * int index = word2.indexOf(word1); int end = word1.length(); int
			 * start = index-1; //int myend = end - index +1; for (int j2 =
			 * end+1; j2>=0 ; j2--) { int j = 0; int[] matris = new int[j2];
			 * boolean allEqual; start++; int mystart = start;
			 * 
			 * for (int i = 0; i < matris.length; i++) { matris[i] =
			 * dp[j][mystart]; if (i == matris.length - 1) { allEqual =false;
			 * allEqual = Sets.newHashSet(Ints.asList(matris)).size() == 1; if
			 * (allEqual==true) { flag=10; } System.out.println(allEqual); }
			 * j++; mystart++; } }
			 */

			flag = 10;
		}
		return dp[len1][len2];
	}

	public static int Number_of_AllLinks(ArrayList<String> Alllinks) {
		int number = AllLinks.size();
		return number;
	}

	public static int Number_of_SLD(ArrayList<String> Alllinks) throws IOException, URISyntaxException {
		ArrayList<String> SLD_List = new ArrayList<String>();
		SLD_List = SLDs(Alllinks);
		int number = SLD_List.size();
		Creat_DataSet_New.SLD_List.addAll(SLD_List);

		return number;
	}

	public static int Number_of_uniq_Links(ArrayList<String> Alllinks) {
		int number = CheckWord(Alllinks).size();
		return number;
	}

	public static int Sensetive_Words(String domain) {
		int result = 0;
		if (domain.contains("secure") || domain.contains("account") || domain.contains("webscr")
				|| domain.contains("login") || domain.contains("ebayisapi") || domain.contains("signin")
				|| domain.contains("banking") || domain.contains("confirm")) {
			result = 1;
		}
		return result;

	}

	public static int Out_of_position_top_level_domain(String TargetURL) throws IOException, URISyntaxException {

		int result = 0;
		FileReader fr = new FileReader("public_suffix_list.dat.txt");
		TopLevelDomainChecker checker = new TopLevelDomainChecker();
		TopLevelDomainParser parser = new TopLevelDomainParser(checker);
		parser.parse(fr);
		// ------------------ SLD Target-----------------
		DomainName dm5 = new DomainName();
		String MainDomain2 = dm5.MyName(TargetURL);
		String sld2;
		sld2 = ".".concat(checker.extractSLD(MainDomain2));
		int countTLd = 0;
		countTLd = countOccurrences(sld2, TargetURL);
		if (countTLd > 1) {
			result = 1;
		}
		return result;
	}

	public static double Normalized_Frequent_Link(int NumberOfAllLinks) {
		try {

			ArrayList<String> uniqLink = new ArrayList<String>();
			uniqLink = CheckWord(SLD_List);
			double[] freq = new double[uniqLink.size()];
			freq = Frequence_SLD_in_URL(uniqLink, SLD_List);

			int max = 0;
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] > max) {
					max = (int) freq[i];
				}
			}

			double number = (double) max / NumberOfAllLinks;
			System.out.println();
			return number;

		} catch (Exception e) {
			return -1;
		}
	}

	public static int Out_of_position_brand_name(String url, ArrayList<String> SLDs, String Domain) {
		try {
			ArrayList<String> AllSLD_OK = new ArrayList<String>();
			AllSLD_OK = CheckWord(SLDs);

			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, SLDs);
			// SLD_OK(0,1,2,...) ; freq[]=(0,1,2,...)
			int index_of_frequent_SLD = Stimate_Max_freq(freq);
			String frequent_SLD = AllSLD_OK.get(index_of_frequent_SLD);

			if (url.contains(frequent_SLD)) {
				url = url.replace(frequent_SLD, "");
				if (url.contains(frequent_SLD)) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	public static int Domain_name_identity(String url, ArrayList<String> SLDs, String Domain, ArrayList<String> TFIDF,
			int TopTFIDF) {
		int result = 0;
		try {
			ArrayList<String> AllSLD_OK = new ArrayList<String>();
			AllSLD_OK = CheckWord(SLDs);

			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, SLDs);
			// SLD_OK(0,1,2,...) ; freq[]=(0,1,2,...)
			int index_of_frequent_SLD = Stimate_Max_freq(freq);
			String frequent_SLD = AllSLD_OK.get(index_of_frequent_SLD);

			int tfidf_result = 0;
			for (int i = 0; i < TopTFIDF; i++) {
				if (TFIDF.get(i).contains(Domain)) {
					tfidf_result = 1;
					break;
				}
			}

			if (Domain.equals(frequent_SLD) && tfidf_result == 1) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	public static int Domain_name_in_the_path_of_the_URL(ArrayList<String> SLDs, String URL) {
		try {
			ArrayList<String> AllSLD_OK = new ArrayList<String>();
			AllSLD_OK = CheckWord(SLDs);

			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, SLDs);
			// SLD_OK(0,1,2,...) ; freq[]=(0,1,2,...)
			int index_of_frequent_SLD = Stimate_Max_freq(freq);
			String frequent_SLD = AllSLD_OK.get(index_of_frequent_SLD);
			URL aURL = new URL(URL);
			String path = aURL.getPath();
			if (frequent_SLD.equals(path)) {
				return -1;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	public static int Nonmatching_URLs(ArrayList<String> SLDs, String Domain) {
		try {
			ArrayList<String> AllSLD_OK = new ArrayList<String>();
			AllSLD_OK = CheckWord(SLDs);

			double[] freq = new double[AllSLD_OK.size()];
			freq = Frequence_SLD_in_URL(AllSLD_OK, SLDs);
			// SLD_OK(0,1,2,...) ; freq[]=(0,1,2,...)
			int index_of_frequent_SLD = Stimate_Max_freq(freq);
			String frequent_SLD = AllSLD_OK.get(index_of_frequent_SLD);

			if (frequent_SLD.equals(Domain)) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	public static int Stimate_Max_freq(double[] freq) {
		double max = 0;
		int index = 0;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > max) {
				max = freq[i];
				index = i;
			}
		}

		return index;
	}

	public static double Nil_anchors(WebDriver driver, String URL, ArrayList<String> AllLinks) {
		double result = 0;
		ArrayList<String> URLs = new ArrayList<String>();
		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
			int count = 0;
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					count++;
					continue;
				}
				URLs.add(link);
				System.out.println(href_link.getAttribute("href"));
			}
			int a_all = 1;
			a_all = AllLinks.size();
			result = count / a_all;

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static double foreign_anchors(WebDriver driver, String URL, String domain, ArrayList<String> AllLinks) {
		double result = 0;
		ArrayList<String> URLs = new ArrayList<String>();
		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
			List<WebElement> href_area = driver.findElements(By.xpath(".//area"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			for (WebElement href_link : href_area) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			ArrayList<String> clean_URLs = new ArrayList<String>();
			ArrayList<String> SLDs = new ArrayList<String>();
			clean_URLs = Check_localhost(AllLinks, URL);
			SLDs = SLDs(clean_URLs);

			int count = 0;
			for (int i = 0; i < SLDs.size(); i++) {
				if (SLDs.get(i).equals(domain)) {
					continue;
				} else {
					count++;
				}
			}

			result = count / SLDs.size();

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static double ID_foreign_anchors(WebDriver driver, String URL, String domain, ArrayList<String> TFIDF,
			int top_TFIDF) {
		double result = 0;
		ArrayList<String> URLs = new ArrayList<String>();
		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
			List<WebElement> href_area = driver.findElements(By.xpath(".//area"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			for (WebElement href_link : href_area) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			ArrayList<String> clean_URLs = new ArrayList<String>();
			ArrayList<String> SLDs = new ArrayList<String>();
			clean_URLs = Check_localhost(AllLinks, URL);
			SLDs = SLDs(clean_URLs);

			int count = 0;
			for (int i = 0; i < SLDs.size(); i++) {
				if (SLDs.get(i).equals(domain)) {
					continue;
				} else { // check forein domain
					for (int j = 0; j < top_TFIDF; j++) {
						if (TFIDF.get(j).contains(SLDs.get(i))) {
							count++;
						}
					}
				}

			}

			result = count / SLDs.size();

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static double foreign_request(WebDriver driver, String URL, String domain, ArrayList<String> AllLinks) {
		double result = 0;
		ArrayList<String> URLs = new ArrayList<String>();
		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//img"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//script"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//frame"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//iframe"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//input"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}

			href_links.clear();
			href_links = driver.findElements(By.xpath(".//object"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("codebase");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//applet"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("codebase");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//applet"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("code");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//body"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("background");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//link"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}

			ArrayList<String> clean_URLs = new ArrayList<String>();
			ArrayList<String> SLDs = new ArrayList<String>();
			clean_URLs = Check_localhost(AllLinks, URL);
			SLDs = SLDs(clean_URLs);

			int count = 0;
			for (int i = 0; i < SLDs.size(); i++) {
				if (SLDs.get(i).equals(domain)) {
					continue;
				} else {
					count++;
				}
			}

			result = count / SLDs.size();

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static double ID_foreign_request(WebDriver driver, String URL, String domain, ArrayList<String> TFIDF,
			int top_TFIDF) {
		double result = 0;
		ArrayList<String> URLs = new ArrayList<String>();
		try {
			List<WebElement> href_links = driver.findElements(By.xpath(".//img"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//script"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//frame"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//iframe"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//input"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("src");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}

			href_links.clear();
			href_links = driver.findElements(By.xpath(".//object"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("codebase");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//applet"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("codebase");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//applet"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("code");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//body"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("background");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}
			href_links.clear();
			href_links = driver.findElements(By.xpath(".//link"));
			for (WebElement href_link : href_links) {
				String link = href_link.getAttribute("href");
				if (link == null || link.isEmpty() || link.contains("#") || link.contains("javascript::void(0)")) {
					continue;
				}
				URLs.add(link);
			}

			ArrayList<String> clean_URLs = new ArrayList<String>();
			ArrayList<String> SLDs = new ArrayList<String>();
			clean_URLs = Check_localhost(AllLinks, URL);
			SLDs = SLDs(clean_URLs);

			int count = 0;
			for (int i = 0; i < SLDs.size(); i++) {
				if (SLDs.get(i).equals(domain)) {
					continue;
				} else { // check forein domain
					for (int j = 0; j < top_TFIDF; j++) {
						if (TFIDF.get(j).contains(SLDs.get(i))) {
							count++;
						}
					}
				}

			}

			result = count / SLDs.size();

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return result;
		}
	}

	public static void Set_Resource(WebDriver driver, String URL) {
		// ---------------------------------------------------------------
		List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
		List<WebElement> src_links = driver.findElements(By.xpath(".//img"));
		List<WebElement> Script_links = driver.findElements(By.xpath(".//script"));
		List<WebElement> Css_links = driver.findElements(By.xpath(".//link"));
		ArrayList<String> URLs = new ArrayList<String>();
		// -------- href , src , Script(JavaScript files) , Link(style sheet
		// files) -------------------------

		for (WebElement href_link : href_links) {
			if (href_link.getAttribute("href") == null || href_link.getAttribute("href").isEmpty()) {
				continue;
			}
			URLs.add(href_link.getAttribute("href"));
			System.out.println(href_link.getAttribute("href"));
		}
		href = Check_localhost(URLs, URL);
		URLs.clear();
		// ______________________________________________________________

		for (WebElement src_link : src_links) {
			if (src_link.getAttribute("src") == null || src_link.getAttribute("src").isEmpty()) {
				continue;
			}
			URLs.add(src_link.getAttribute("src"));
			System.out.println(src_link.getAttribute("src"));
		}
		src = Check_localhost(URLs, URL);
		URLs.clear();
		// ______________________________________________________________

		for (WebElement Script_link : Script_links) {
			if (Script_link.getAttribute("src") == null || Script_link.getAttribute("src").isEmpty()) {
				continue;
			}
			URLs.add(Script_link.getAttribute("src"));
			System.out.println(Script_link.getAttribute("src"));
		}
		Script = Check_localhost(URLs, URL);
		URLs.clear();
		// ______________________________________________________________

		for (WebElement Css_link : Css_links) {
			if (Css_link.getAttribute("href") == null || Css_link.getAttribute("href").isEmpty()) {
				continue;
			}
			URLs.add(Css_link.getAttribute("href"));
			System.out.println(Css_link.getAttribute("href"));
		}
		Css = Check_localhost(URLs, URL);
		URLs.clear();
		// ______________________________________________________________

		URLs.addAll(href);
		URLs.addAll(src);
		URLs.addAll(Script);
		URLs.addAll(Css);

		AllLinks = Check_localhost(URLs, URL);

	}

}
