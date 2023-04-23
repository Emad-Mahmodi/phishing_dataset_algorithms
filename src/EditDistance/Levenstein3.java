package EditDistance;

import java.util.ArrayList;

import org.jboss.netty.util.EstimatableObjectWrapper;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

public class Levenstein3 {
	public static void main(String args[])
	{
		
		String str1 = "ali";
		String str2 = "BankofAmerica";
		System.out.println((double)minDistance(str1 , str2)/str2.length());
	}
	
	public static int minDistance(String word1, String word2) {
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
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
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


		for (int i = len1 ; i>=0 ; i--){
			
			if (i>0) {
				System.out.print(word1.charAt(i - 1) + "\t");
			}else if(i==0) {
				System.out.print( "\t");
			}
			for (int j = 0; j <= len2; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");

		for (int i = 0 ; i<= len2 ; i++){
			
			if (i==0) {
				System.out.print("\t\t");
				continue;
			}
			char c1 = word2.charAt(i-1);
		System.out.print(c1+"\t");
	}
		System.out.println();




		if(word2.contains(word1)){
			int index = word2.indexOf(word1);
			int end = word1.length();
			int start = index-1;
			//int myend = end - index +1;
			for (int j2 = end+1; j2>=0 ; j2--) {
				int j = 0;
				int[] matris = new int[j2];
				boolean allEqual;
			    start++;
			    int mystart = start;
			    
				for (int i = 0; i < matris.length; i++) {
					matris[i] = dp[j][mystart];
					if (i == matris.length - 1) {
						 allEqual =false;
						 allEqual = Sets.newHashSet(Ints.asList(matris)).size() == 1;
						 System.out.println(allEqual);
					}
					j++;
					mystart++;
				} 
			}
		}
		return dp[len1][len2];
	}

}
