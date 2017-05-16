package springbackend.service;

import javafx.util.Pair;
import springbackend.model.SearchRequest;
import springbackend.model.Service;

import java.util.*;

/**
 * Service for site search.
 */
public interface SearchService {
    /**
     * Search for exact occurrences.
     *
     * @param
     */
    Set<Service> getExactOccurrences(SearchRequest searchRequest);

    /**
     *
     * @return
     */
    String getAlternativeSearchLine(Map<String, HashMap<String, Integer>> wordsWithDistance,
                                    SearchRequest searchRequest);

    /**
     *
     */
    Map<String, HashMap<String, Integer>> getWordsWithMinimumDistance(SearchRequest searchRequest);

    /**
     * @return
     */
    TreeSet<String> crateDictionary();

    /**
     * @param testString
     * @return
     */
    boolean testDictString(String testString);

    /**
     * Calculating a metric by Levenshtein's formula:
     * D(i,j) = {
     * 0, if {i = 0, j = 0}
     * i, if {j = 0, i > 0}
     * j, if {i = 0, j > 0}
     * min {
     * D(i, j - 1) + 1,
     * D(i - 1, j) + 1,
     * D(i - 1, j - 1) + m(Str1[i],Str2[j] (m = 0 if {a = b}, otherwise m = 1)
     * } if {j > 0, i > 0}
     * }
     *
     * @param first  - s
     * @param second - sa
     * @return
     */
    int getPrefixDistance(CharSequence first, CharSequence second, int maxDistance);

    /**
     * @param i
     * @param j
     * @param first
     * @param second
     * @return
     */
    int D(int i, int j, CharSequence first, CharSequence second);

    /**
     * @param first
     * @param second
     * @param third
     * @return
     */
    int getMinimum(int first, int second, int third);
}