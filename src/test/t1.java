package test;

public class t1 {

	public static void main(String[] args) {
		String temp = "asdsdsd.sdds.sdsds.sdd";
		String words[] = temp.split("\\.");
		for (int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
		}
	}

}
