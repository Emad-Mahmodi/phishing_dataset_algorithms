package EditDistance;

import java.util.ArrayList;

public class test2 {
	public static void main(String[] args) {
	    // get list
		long start = System.currentTimeMillis();
		long end = start + 60*10; // 60 seconds * 1000 ms/sec
		int i = 0 ;
		while (System.currentTimeMillis() < end)
		{ 
			i++;
			if(i==40000)break;
		    System.out.println(i);
		}
	    
	}
}
