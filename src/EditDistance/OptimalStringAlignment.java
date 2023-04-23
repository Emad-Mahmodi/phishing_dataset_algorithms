package EditDistance;

public class OptimalStringAlignment {

    public static double distance(final String s1, final String s2) {
        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        int n = s1.length(), m = s2.length();

        if (n == 0) {
            return m;
        }

        if (m == 0) {
            return n;
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] d = new int[n + 2][m + 2];

        //initialize top row and leftmost column
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        //fill the distance matrix
        int cost;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                //if s1[i - 1] = s2[j - 1] then cost = 0, else cost = 1
                cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                d[i][j] = min(
                        d[i - 1][j - 1] + cost, // substitution
                        d[i][j - 1] + 1,        // insertion
                        d[i - 1][j] + 1         // deletion
                );

                //transposition check
                if (i > 1 && j > 1 
                        && s1.charAt(i - 1) == s2.charAt(j - 2) 
                        && s1.charAt(i - 2) == s2.charAt(j - 1)
                    ){
                    d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
                }
            }
        }

        return d[n][m];
    }
    private static int min(
            final int a, final int b, final int c) {
        return Math.min(a, Math.min(b, c));
    }
    public static void main(String args[])
	{
		String str1 = "CA";
		String str2 = "ABC";
		System.out.println(distance(str1 , str2));
	}
}
