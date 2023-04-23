package PhishWho;

public class testing {

	public static void main(String[] args) throws Exception {
		GoogleSearch go=new GoogleSearch();
		String s="usil";
		int num=10;
		go.MySearch(s,num);
		System.out.println(go.MySearch(s,num));
	}

}
