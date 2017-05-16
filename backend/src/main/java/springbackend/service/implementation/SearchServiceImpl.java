package springbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import springbackend.model.SearchRequest;
import springbackend.model.Service;
import springbackend.service.SearchService;
import springbackend.service.ServiceForService;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Implementation of {@link springbackend.service.SearchService} interface.
 */
@org.springframework.stereotype.Service
public class SearchServiceImpl implements SearchService {
    /**
     *
     */
    interface Distance {
        int getDistance(CharSequence word1, CharSequence word2);
    }

    @Autowired
    private ServiceForService serviceForService;

    @Autowired
    private SearchService searchService;

    @Override
    public Set<springbackend.model.Service> getExactOccurrences(SearchRequest searchRequest) {
        TreeSet<Service> allServiceSet = new TreeSet<>(
                (o1, o2) -> o1.getNameOfService().compareToIgnoreCase(o2.getNameOfService()));

        String searchLine = searchRequest.getSearchLine();

        allServiceSet.addAll(this.serviceForService.findAll());
        Set<Service> resultsSet = allServiceSet.stream()
                .filter(temp ->
                        Arrays.stream(temp.getNameOfService().split(" "))
                                .anyMatch(searchLine::equalsIgnoreCase)             //TODO: add search by many words (not by one as now) и убрать несколько вызовов гетсёерчлайн
                                ||
                                Arrays.stream(temp.getDescription().split(" "))
                                        .anyMatch(searchLine::equalsIgnoreCase))
                .collect(Collectors.toSet());

        return resultsSet;
    }

    @Override
    public String getAlternativeSearchLine(Map<String, HashMap<String, Integer>> wordsWithDistance,
                                           SearchRequest searchRequest) {
        StringBuilder result = new StringBuilder();
        String[] wordsFromRequest = searchRequest.getSearchLine().split(" ");

        Map<String, Integer> minDistanceMap = new HashMap<>(); //string - userword, Integer - mindestance

        Arrays.stream(wordsFromRequest).forEach(userWord
                -> minDistanceMap.put(userWord, wordsWithDistance.get(userWord)
                .values().stream().min(Comparator.naturalOrder()).orElse(-1)));
        
        return null;
    }

    @Override
    public Map<String, HashMap<String, Integer>> getWordsWithMinimumDistance(SearchRequest searchRequest) {
        String searchLine = searchRequest.getSearchLine();

        /* Map with words from request and with pair consisting distance & word from dictionary. */
        Map<String, HashMap<String, Integer>> resultMap = new TreeMap<>(Comparator.naturalOrder());

        /* Set with words form all services in the base */
        TreeSet<String> dictionary = this.searchService.crateDictionary();

        Distance distance = (word1, word2) -> this.searchService.getPrefixDistance(word1, word2, 4);

        String[] wordsFromSearchQuery = searchLine.split(" ");
        Arrays.stream(wordsFromSearchQuery)
                .forEach(userWord -> {
                    HashMap<String, Integer> map = new HashMap<>();
                    dictionary.forEach(dictWord ->
                            map.put(dictWord, distance.getDistance(userWord, dictWord)));

                    resultMap.put(userWord, map);
                });

        return resultMap;
    }

    @Override
    public TreeSet<String> crateDictionary() {
        TreeSet<String> result = new TreeSet<>(String::compareToIgnoreCase);

        Set<springbackend.model.Service> allServiceSet = this.serviceForService.findAll();
        allServiceSet.forEach(service -> {
            String[] texts = new String[]{service.getNameOfService(), service.getDescription()};
            Arrays.stream(texts).forEach(text -> result.addAll(Arrays.stream(text.toLowerCase().split(" "))
                    .filter(word -> this.searchService.testDictString(word)).collect(Collectors.toSet())));
        });

        return result;
    }

    @Override
    public boolean testDictString(String testString) {
        Pattern p = Pattern.compile("^[а-яa-z]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    @Override
    public int getPrefixDistance(CharSequence userString, CharSequence dictString, int maxDistance) {
        int userStringLength = userString.length();
        int dictStringLength = dictString.length();

        if (userStringLength >= 20)
            return -1;

        if (maxDistance < 0)
            maxDistance = dictStringLength;

        if (dictStringLength == 0) {
            return 0;
        } else if (userStringLength == 0) {
            return dictStringLength;
        } else if (userStringLength < dictStringLength - maxDistance) {
            return maxDistance + 1;
        } else if (dictStringLength >= userStringLength
                && userString.equals(dictString.subSequence(0, userStringLength))) {
            return 0;
        }

        int[][] arr = new int[userStringLength + 1][dictStringLength + 1];
        for (int i = 0; i <= userStringLength; i++) {
            arr[i][0] = i;
        }
        for (int j = 0; j <= dictStringLength; j++) {
            arr[0][j] = j;
        }

        for (int i = 1; i <= userStringLength; i++) {
            for (int j = 1; j <= dictStringLength; j++) {
                if (Math.abs(i - j) < 2 * (maxDistance + 1))
                    arr[i][j] = D(i - 1, j - 1, userString, dictString);     //TODO: try with stream

                if (i == j && arr[i][j] > maxDistance)
                    return arr[i][j];
            }
        }

        return arr[userStringLength][dictStringLength];
    }

    @Override
    public int D(int i, int j, CharSequence userString, CharSequence dictString) {
        if (i == 0 && j == 0)
            return 0;
        else if (j == 0 && i > 0)
            return i;
        else if (i == 0 && j > 0)
            return j;
        else {
            return getMinimum(D(i, j - 1, userString, dictString) + 1,
                    D(i - 1, j, userString, dictString) + 1,
                    D(i - 1, j - 1, userString, dictString)
                            + (userString.charAt(i) == dictString.charAt(j) ? 0 : 1)
            );
        }
    }

    @Override
    public int getMinimum(int first, int second, int third) {
        if (first == second && first == third)
            return first;
        else if (first <= second && first <= third)
            return first;
        else if (second <= first && second <= third)
            return second;
        else
            return third;
    }
}
