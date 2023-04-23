package PhishWho.Wrong;

import java.util.*;
import java.util.stream.Collectors;
public class ChechDouble {
public static String a;

	public static String check(String M) {
		a="";
        String[] words = M.split(" ");
        List<String> wordsList = Arrays.asList(M.split(" "));
        ArrayList<String> wordsList2 = new ArrayList<String>();
        wordsList.stream().map(s -> s.toLowerCase())
                          .distinct()
                          .forEach( s -> aaa(s));
       
        System.out.println(a);
        return a;
	}
	public static void aaa(String s){
		a=a.concat(s);
		a=a.concat(" ");
	}


}
