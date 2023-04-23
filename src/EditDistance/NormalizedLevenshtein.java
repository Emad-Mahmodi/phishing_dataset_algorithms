package EditDistance;

public class NormalizedLevenshtein {
    private final Levenshtein l = new Levenshtein();

    /**
     * Compute distance as Levenshtein(s1, s2) / max(|s1|, |s2|).
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The computed distance in the range [0, 1]
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

        return l.distance(s1, s2) / mLen;
    }

    /**
     * Return 1 - distance.
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return 1.0 - the computed distance
     * @throws NullPointerException if s1 or s2 is null.
     */
    public final double similarity(final String s1, final String s2) {
        return 1.0 - distance(s1, s2);
    }

	public static void main(String[] args) {
        NormalizedLevenshtein l = new NormalizedLevenshtein();

		System.out.println(l.distance("bankofam", "bankofamerica"));

	}

}
