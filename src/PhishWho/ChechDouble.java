package PhishWho;

import java.util.*;
import java.util.stream.Collectors;
public class ChechDouble {
public static String a;

	public static String check(String M) {
		/*String[] words = M.split(" ");
        List<String> wordsList = Arrays.asList(M.split(" "));
        ArrayList<String> wordsList2 = new ArrayList<String>();
        wordsList.stream().map(s -> s.toLowerCase())
                          .distinct()
                          .forEach( s -> aaa(s));
       
        System.out.println(a);
        return a;*/
		   a="";
	       List<String> ww = Arrays.asList(M.split(" "));
	       ArrayList<String> wordsList = new ArrayList<String>();
	       for(int i=0 ; i<ww.size() ; i++){
	    	   wordsList.add(ww.get(i));
	       }
	        ArrayList<String> Flag_wordsList = new ArrayList<String>();
	        ArrayList<String> True_wordsList = new ArrayList<String>();

	        String word_i="";
	        String word_j="";
	        int flag;
	        for (int i = 0; i < wordsList.size(); i++) {
	        	word_i=wordsList.get(i);
	        	flag=0;
				for (int j = wordsList.size()-1 ; j > i; j--) {
					word_j=wordsList.get(j);
					if (word_i.toLowerCase().equals(word_j.toLowerCase())) {
						boolean w=Character.isUpperCase(word_i.charAt(0));
						if ( Character.isUpperCase(word_i.charAt(0)) && flag==0 ) {
							Flag_wordsList.add("0");
							wordsList.remove(j);
							flag=1;
						}else if( Character.isUpperCase(word_j.charAt(0)) && flag==0 ){
							Flag_wordsList.add("0");
							wordsList.remove(j);
							flag=1;
						}else{
							wordsList.remove(j);
						}
					}
				}
				if(flag==0 && Character.isUpperCase(word_i.charAt(0))){
					Flag_wordsList.add("0");
				}else if (flag==0 && !Character.isUpperCase(word_i.charAt(0))) {
					Flag_wordsList.add("1");
				}
			}
	        for(int i=0 ; i<wordsList.size() ; i++){
	        	String str=wordsList.get(i);
	        	if(Flag_wordsList.get(i)=="0"){
	        		True_wordsList.add(str.substring(0, 1).toUpperCase() + str.substring(1));
	        	}else if(Flag_wordsList.get(i)=="1")
	        	{
	        		True_wordsList.add(str);
				}
	        }
	        for(int i=0 ; i<True_wordsList.size() ; i++){
	        	aaa(True_wordsList.get(i));
	        }
        return a;
	}
	public static void aaa(String s){
		a=a.concat(s);
		a=a.concat(" ");
	}


}
