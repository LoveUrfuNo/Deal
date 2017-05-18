package springbackend.service.implementation;

import javafx.collections.transformation.SortedList;
import org.springframework.beans.factory.annotation.Autowired;
import springbackend.model.SearchRequest;
import springbackend.model.Service;
import springbackend.service.SearchService;
import springbackend.service.ServiceForService;

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

    /**
     *
     */
    interface Array {
        ArrayList<String> getArrayFromTexts(String string1, String string2);
    }

    /**
     *
     */
    interface ExactOccerences {
        Boolean isWordIncludingInText(String text);            //TODO: fixed
    }

    /**
     *
     */
    interface OppositeWord {
        String getOppositeWord(String sourceWord);
    }

    @Autowired
    private ServiceForService serviceForService;

    @Autowired
    private SearchService searchService;

    private static final String regexForSplit = "[[\\p{P}][\\t\\n\\r\\s]+=№]";

    private static final String regexForReplace = "[^а-я\\w-][\\s]{2,}";

    @Override
    public TreeSet<Service> getResultServiceSet(SearchRequest searchRequest) {
        TreeSet<Service> searchResults = new TreeSet<>(Comparator.comparing(Service::getServiceCost));
        Set<Service> allServiceSet = this.serviceForService.findAll();

        String searchLine = searchRequest.getSearchLine().replaceAll(regexForReplace, "");

        Array arrayList = (string1, string2) -> {
            ArrayList<String> result = new ArrayList<>();

            result.addAll(Arrays.stream(string1.split(regexForSplit))
                    .map(t -> t.replaceAll(regexForReplace, ""))
                    .filter(word -> !word.isEmpty()).distinct()
                    .collect(Collectors.toList()));
            result.addAll(Arrays.stream(string2.split(regexForSplit))
                    .map(t -> t.replaceAll(regexForReplace, ""))
                    .filter(word -> !word.isEmpty()).distinct()
                    .collect(Collectors.toList()));

            return result;
        };

        String[] searchLineWords = searchLine.split(regexForSplit);
        ExactOccerences bool = (string) -> Arrays.stream(searchLineWords)
                .allMatch(searchWord -> arrayList.getArrayFromTexts(string, "")
                        .contains(searchWord));

        searchResults.addAll(allServiceSet.stream()
                .filter(service ->
                        bool.isWordIncludingInText(service.getNameOfService())
                                ||
                                bool.isWordIncludingInText(service.getDescription()))          //TODO: rename bool
                .collect(Collectors.toSet()));

        //if      //TODO: add in "did_you_meant_it" too

        searchResults.addAll(allServiceSet.stream()
                .filter(service ->
                        arrayList.getArrayFromTexts(service.getNameOfService(), service.getDescription())
                                .stream().anyMatch(word ->
                                Arrays.stream(searchLineWords)
                                        .collect(Collectors.toList()).contains(word.toLowerCase())))
                .collect(Collectors.toSet()));

        return searchResults;
    }

    @Override
    public String getAlternativeSearchLine(Map<String, HashMap<String, Integer>> wordsWithDistance,
                                           SearchRequest searchRequest) {
        StringBuilder result = new StringBuilder();
        String[] wordsFromRequest = searchRequest.getSearchLine().split(regexForSplit);

        Map<String, Integer> minDistanceMap = new HashMap<>(); //string - userword, Integer - mindestance

        Arrays.stream(wordsFromRequest).forEach(userWord
                -> minDistanceMap.put(userWord, wordsWithDistance.get(userWord)
                .values().stream().min(Comparator.naturalOrder()).orElse(-1)));

        Arrays.stream(wordsFromRequest).forEach(userWord -> {
            result.append(wordsWithDistance.get(userWord)
                    .entrySet().stream().filter(pair ->
                            pair.getValue().equals(minDistanceMap.get(userWord)))
                    .findAny().get().getKey());
            result.append(" ");
        });

        return result.toString();
    }

    @Override
    public Map<String, HashMap<String, Integer>> getWordsWithMinimumDistance(SearchRequest searchRequest) {
        String searchLine = searchRequest.getSearchLine();

        /* Map with words from request and with pair consisting distance & word from dictionary. */
        Map<String, HashMap<String, Integer>> resultMap = new TreeMap<>(Comparator.naturalOrder());

        /* Set with words form all services in the base. */
        TreeSet<String> dictionary = this.searchService.crateDictionary();

        Distance distance = (word1, word2) -> this.searchService.getPrefixDistance(word1, word2, 4);

        String[] wordsFromSearchQuery = searchLine.split(regexForSplit);
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
    public String getStringByOppositeLayout(String sourceString) {
        String cyrillicLayout = "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
        String latinLayout = "qwertyuiop[]asdfghjkl;'zxcvbnm,./QWERTYUIOP[]ASDFGHJKL;'ZXCVBNM,./";


        OppositeWord oppositeWord = (sourceWord -> {
            int i = 0;
            StringBuilder newWord = new StringBuilder();
            for (char c : sourceWord.toCharArray()) {
                if (cyrillicLayout.indexOf(sourceWord.charAt(i++)) == -1) {
                    newWord.append(cyrillicLayout.charAt(latinLayout.indexOf(c)));
                } else {
                    newWord.append(latinLayout.charAt(cyrillicLayout.indexOf(c)));
                }
            }

            return newWord.toString();
        });

        StringBuilder newSearchLine = new StringBuilder();
        String[] arr = sourceString.replaceAll(regexForReplace, "").split(regexForSplit);
        Arrays.stream(arr).forEach(word -> newSearchLine.append(oppositeWord.getOppositeWord(word)).append(" "));

        return newSearchLine.toString();
    }

    @Override
    public TreeSet<String> crateDictionary() {
        TreeSet<String> result = new TreeSet<>(String::compareToIgnoreCase);

        Set<springbackend.model.Service> allServiceSet = this.serviceForService.findAll();
        allServiceSet.forEach(service -> {
            String[] texts = new String[]{service.getNameOfService(), service.getDescription()};
            Arrays.stream(texts).forEach(text -> result.addAll(Arrays.stream(text.toLowerCase().split(regexForSplit))
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
