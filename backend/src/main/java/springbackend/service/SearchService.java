package springbackend.service;

import springbackend.model.SearchRequest;
import springbackend.model.Service;

import java.util.*;

/**
 * Service for site search.
 */
public interface SearchService {
    /**
     *
     *
     *
     */
    SearchRequest getEditedSearchRequest(SearchRequest sourceSearchRequest);

    /**
     * Search for exact occurrences.
     *
     * @param
     */
    TreeSet<Service> getResultServiceSet(SearchRequest searchRequest);

    /**
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
    String getStringByOppositeKeybordLayout(String sourceString);

    /**
     * @return
     */
    TreeSet<String> crateDictionary();

    /**
     * 
     *
     * @param testString
     * @return
     */
    boolean isStringSuitableForDictionary(String testString);

    /**
     * Calculatings a metric by Levenshtein's formula:
     * D(i,j) = {
     * 0, if {i = 0, j = 0}
     * i, if {j = 0, i > 0}
     * j, if {i = 0, j > 0}
     * min {
     * D(i, j - 1) + 1,
     * D(i - 1, j) + 1,
     * D(i - 1, j - 1) + m(Str1[i], Str2[j] (m = 0 if {a = b}, otherwise m = 1)
     * } if {j > 0, i > 0}
     * }
     *
     * @param userString - first comparison line
     * @param dictString - second comparison line
     * @return value of metric.
     */
    int getPrefixDistance(CharSequence userString, CharSequence dictString, int maxDistance);

    /**
     * Gets Levenshtein's distance for d(S1, S2) - one cell of the matrix.
     *
     * @param i - index (arr[i][j])
     * @param j - index (arr[i][j])
     * @param userString - first comparison line
     * @param dictString - second comparison line
     * @return distance for one cell of the matrix.
     */
    int D(int i, int j, CharSequence userString, CharSequence dictString);

    /**
     * Gets minimum from three integer number.
     */
    int getMinimum(int first, int second, int third);
}