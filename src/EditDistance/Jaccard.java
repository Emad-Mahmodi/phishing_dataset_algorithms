package EditDistance;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Jaccard extends ShingleBased {

    /**
     * The strings are first transformed into sets of k-shingles (sequences of k
     * characters), then Jaccard index is computed as |A inter B| / |A union B|.
     * The default value of k is 3.
     *
     * @param k
     */
    public Jaccard(final int k) {
        super(k);
    }

    /**
     * The strings are first transformed into sets of k-shingles (sequences of k
     * characters), then Jaccard index is computed as |A inter B| / |A union B|.
     * The default value of k is 3.
     */
    public Jaccard() {
        super();
    }

    /**
     * Compute Jaccard index: |A inter B| / |A union B|.
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The Jaccard index in the range [0, 1]
     * @throws NullPointerException if s1 or s2 is null.
     */
    public final double similarity(final String s1, final String s2) {
        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 1;
        }

        Map<String, Integer> profile1 = getProfile(s1);
        Map<String, Integer> profile2 = getProfile(s2);

        Set<String> union = new HashSet<String>();
        union.addAll(profile1.keySet());
        union.addAll(profile2.keySet());

        int inter = 0;

        for (String key : union) {
            if (profile1.containsKey(key) && profile2.containsKey(key)) {
                inter++;
            }
        }

        return 1.0 * inter / union.size();
    }


    /**
     * Distance is computed as 1 - similarity.
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return 1 - the Jaccard similarity.
     * @throws NullPointerException if s1 or s2 is null.
     */
    public final double distance(final String s1, final String s2) {
        return 1.0 - similarity(s1, s2);
    }
    
    
	public static void main(String[] args) {
		
		Jaccard jac = new Jaccard();
		System.out.println(jac.similarity("bank", "bankofamerica"));

	}

}
