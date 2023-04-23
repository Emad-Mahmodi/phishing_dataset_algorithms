package EditDistance;

public class MetricLCS {

    private final LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    /**
     * Distance metric based on Longest Common Subsequence, computed as
     * 1 - |LCS(s1, s2)| / max(|s1|, |s2|).
     *
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The computed distance metric value.
     * @throws NullPointerException if s1 or s2 is null.
     */
    public final double distance(final String s1, final String s2) {
        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        int mLen = Math.max(s1.length(), s2.length());
        if (mLen == 0) {
            return 0;
        }
        return 1.0
            - (1.0 * lcs.length(s1, s2))
            / mLen;
    }
	public static void main(String[] args) {

		MetricLCS lcs = new MetricLCS();
		 System.out.println(lcs.distance("of", "bankofamerica"));
	}

}
