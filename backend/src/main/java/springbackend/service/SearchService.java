package springbackend.service;

import springbackend.model.SearchRequest;
import springbackend.model.Service;

import java.util.Set;
import java.util.TreeSet;

/**
 * Service for site search.
 */
public interface SearchService {
    /**
     * Search for exact occurrences.
     *
     * @param
     */
    Set<Service> searchOccurrences(SearchRequest searchRequest);

    /**
     *
     *
     * @return
     */
    TreeSet<String> crateDictionary();

    /**
     * Calculating a metric by Levenshtein's formula:
     * D(i,j) = {
     *     0, if {i = 0, j = 0}
     *     i, if {j = 0, i > 0}
     *     j, if {i = 0, j > 0}
     *     min {
     *         D(i, j - 1) + 1,
     *         D(i - 1, j) + 1,
     *         D(i - 1, j - 1) + m(Str1[i],Str2[j] (m = 0 if {a = b}, otherwise m = 1)
     *     } if {j > 0, i > 0}
     * }
     *
     * @param first  - s
     * @param second - sa
     * @return
     */
    int getPrefixDistance(CharSequence first, CharSequence second);

    /**
     *
     * @param i
     * @param j
     * @param first
     * @param second
     * @return
     */
    int D(int i, int j, CharSequence first, CharSequence second);

    /**
     *
     * @param first
     * @param second
     * @param third
     * @return
     */
    int minimum(int first, int second, int third);
}
